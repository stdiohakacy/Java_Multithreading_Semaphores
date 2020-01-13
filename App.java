package _07;

import java.util.concurrent.Semaphore;

public class App {
	static Semaphore semaphore = new Semaphore(4);

	static class ATMThread extends Thread {
		private String name = "";

		public ATMThread(String name) {
			this.name = name;
		}

		public void run() {
			try {
				System.out.println(name + " : acquiring lock...");
				System.out.println(name + " : available Semaphore permits now: " + semaphore.availablePermits());
				semaphore.acquire();
				System.out.println(name + " : got the permit!");
				try {
					for (int i = 1; i <= 5; i++) {
						System.out.println(name + " : is performing operation " + i + ", available Semaphore permits : "
								+ semaphore.availablePermits());
						Thread.sleep(3000);
					}
				} finally {
					System.out.println(name + " : releasing lock...");
					semaphore.release();
					System.out.println(name + " : available Semaphore permits now: " + semaphore.availablePermits());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Total available Semaphore permits : " 
				+ semaphore.availablePermits());
	
		ATMThread t1 = new ATMThread("A");
		ATMThread t2 = new ATMThread("B");
		ATMThread t3 = new ATMThread("C");
		ATMThread t4 = new ATMThread("D");
		ATMThread t5 = new ATMThread("E");
		ATMThread t6 = new ATMThread("F");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}
