package edu.chinna.kadhira.forkjoinpool; 

import static java.lang.System.out;
import static java.lang.System.nanoTime;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.ForkJoinPool.commonPool;
import static java.util.concurrent.ForkJoinTask.adapt;
import static java.util.concurrent.TimeUnit.SECONDS;
import static edu.chinna.kadhira.forkjoinpool.PoolInducedDeadlock.computeInRange;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinExample {
	public static long splitAndCompute(int lower,int upper){
		out.println(" start  "+lower+"   "+upper+"   "+Thread.currentThread().getName());
		int diff = upper-lower;
		if(diff <100) return computeInRange(lower,upper);
		else {
			int middle = lower +  diff/2 ;
			ForkJoinTask<Long> task1 = adapt(()-> splitAndCompute(lower,middle));
			ForkJoinTask<Long> task2 = adapt(()-> splitAndCompute(middle,upper));
			task2.fork();
			task1.fork();
			return task1.join() + task2.join();
		}
	}
	
	public static void test(int highNum) {
		long startTime = nanoTime();
		ForkJoinPool pool = commonPool();
		Long res = pool.invoke(adapt(()-> splitAndCompute(1,highNum)));
		out.println("*******************************");	
		out.println("Fork Join Task "+res);	
		out.println("*******************************");	
		try{pool.awaitTermination(2,SECONDS);
		 pool.shutdown(); }
		catch(InterruptedException e) { out.println(e.getMessage());}
		long endTime = nanoTime();
		out.println(" ForkJoinExample  "+(endTime-startTime)/1.e9);
		String[] names = {"Ram","Vasantha","Sai","Laxman","Kondapalli"};
		RotateArrayElements.test(names,2);
		RecursiveTaskExample.test();
	}
}

