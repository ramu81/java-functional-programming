package edu.chinna.kadhira.forkjoinpool;

import static java.lang.System.out;
import static java.util.concurrent.ForkJoinPool.commonPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class ExampleTwo {
	private static void doSomthing() { 
		try {SECONDS.sleep(1);}
		catch(InterruptedException e) {}
		out.println(" ForkJoinTask :  Hello All"); }
	
	private static Supplier<Integer> get() { 
		try {SECONDS.sleep(1);}
		catch(InterruptedException e) {}
		return () -> 2; }
	
	public static void test() {
		out.println("*******************************");	
		ForkJoinPool pool = commonPool();
		pool.execute( ExampleTwo::doSomthing);
		Supplier<Integer> supp = pool.invoke( ForkJoinTask.adapt(ExampleTwo::get));
		out.println(" ForkJoinTask :  "+supp.get());
		out.println("*******************************");	
	}
}