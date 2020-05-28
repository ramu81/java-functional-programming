package edu.chinna.kadhira.forkjoinpool;

import static java.lang.System.out;
import static java.util.stream.IntStream.range;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutionException ;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class PoolInducedDeadlock{
	private static ExecutorService service = null;
	public static boolean isPrime(int num) {
		return num>1 && 
					range(2,num).noneMatch(i -> num%i == 0);
	}
	
	public static long computeInRange(int lower,int upper) {
		return range(lower,upper)
			   .filter(PoolInducedDeadlock::isPrime)
			   .count();
	}
	
	public static long splitAndCompute(int lower,int upper){
		out.println(" start  "+lower+"   "+upper+"   "+Thread.currentThread().getName());
		int diff = upper-lower;
		try {
			if(diff <100) return computeInRange(lower,upper);
			else {
				int middle = lower +  diff/2 ;
				Future<Long> task1 = service.submit(()-> splitAndCompute(lower,middle));
				Future<Long> task2 = service.submit(()-> splitAndCompute(middle,upper));
				return task1.get() + task2.get();
			}
		} catch(InterruptedException | ExecutionException e ) {
			out.println(e.getMessage());
		}
	return 0;
	}
	
	public static void test(int highNum) {
		ExampleOne.test(highNum);
		long startTime = System.nanoTime();
		// make pool size to 10, it will create dead pool induced deadlocks or give more requests 
		service = newFixedThreadPool(10);
		out.println("*******************************");	
		out.println("   "+splitAndCompute(1,highNum));
		out.println("*******************************");
		try { service.awaitTermination(2,SECONDS); }
		catch(InterruptedException e ) { out.println(e.getMessage()); }
		service.shutdown();
		long endTime = System.nanoTime();
		out.println(" PoolInducedDeadlock  "+(endTime-startTime)/1.e9);
		
	}
}