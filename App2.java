package _07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
	INSTANCE;

	private Semaphore semaphore = new Semaphore(3, true);

	private void download() {
		System.out.println("Downloading data from the website .... ");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadData() {
		try {
			semaphore.acquire();
			download();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}

public class App2 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 12; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}
}
