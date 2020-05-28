package edu.chinna.kadhira.concurrent;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.lang.Thread.currentThread;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Example {
			
	public static void test(){
		ExecutorService service = newFixedThreadPool(3);
		Supplier<String> factory = () -> "Hello World  "+currentThread();
		Function<String,String> trnsform = s -> s.toUpperCase();
		try {
			Future<String> task1 = service.submit(()-> factory.get());
			out.println(task1.get());
			service.awaitTermination(1,SECONDS);
			service.shutdown();	
			
			runAsync(() -> out.println("Hello CompletableFuture "+currentThread()));			
			
			supplyAsync( factory).
			thenApply(trnsform).
			thenAccept(out::println).
			thenRun(() -> out.println("Task completed ") );			
			
			SECONDS.sleep(1);
			
		} catch( InterruptedException | ExecutionException e ) {
			e.printStackTrace();
		}
		
	}
}