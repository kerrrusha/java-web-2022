package com.kerrrusha.kpi.java.web_developing.lab1;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class Main {
	public static void main(String[] args) {
		// "/C:/Users/kerrrusha/source/java/kpi/web-developing/1-multithreading/test_folder"
		File file = new PreparingTool().prepareFile();
		WordSwapper swapper = new WordSwapper(file);

		FutureTask<List<String>> task = new FutureTask<>(swapper);
		Executor executor = (runnable) -> new Thread(runnable).start();
		executor.execute(task);
		System.out.println(Thread.currentThread().getName() + " started task.");

		try {
			List<String> filesOperated = task.get();

			System.out.println("Files operated:");
			filesOperated.forEach(System.out::println);
		} catch (ExecutionException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
