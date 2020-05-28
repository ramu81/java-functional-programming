package edu.chinna.kadhira.forkjoinpool;

import static edu.chinna.kadhira.forkjoinpool.PoolInducedDeadlock.computeInRange;
import static java.util.concurrent.ForkJoinPool.commonPool;
import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Future;
import java.util.concurrent.ForkJoinPool;

public class ExampleOne  {
	private static ForkJoinPool service = null;
	public static void test(int highNum) {
		long startTime = System.nanoTime();
		service = commonPool();
		out.println("   "+splitAndCompute(1,highNum));
		try {
		service.awaitTermination(2,SECONDS);
		service.shutdown();
		long endTime = System.nanoTime();
		out.println(" ExampleOne  "+(endTime-startTime)/1.e9);
		ForkJoinExample.test(highNum);
		ExampleTwo.test();
		
		} catch(Exception e ) { out.println(e.getMessage());}
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
		} catch(Exception e ) {
			out.println(e.getMessage());
		}
	return 0;
	}
	
}