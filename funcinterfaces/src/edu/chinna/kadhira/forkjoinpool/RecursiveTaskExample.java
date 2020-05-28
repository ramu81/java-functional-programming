package edu.chinna.kadhira.forkjoinpool;

import static java.lang.System.out;
import static java.util.concurrent.ForkJoinPool.commonPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ExecutionException;
import edu.chinna.kadhira.concurrent.Example;

public class RecursiveTaskExample {
	
	public static void test() {
		ForkJoinPool pool = commonPool();
		ForkJoinTask<Integer> task = pool.submit( new Fibanacci(3));
		try{out.println(" Fibanacci : " +task.get());
		pool.awaitTermination(1,SECONDS);} 
		catch(InterruptedException | ExecutionException e) {out.println(e.getMessage());}
		pool.shutdown();
		Example.test();
	}
	
	
}

class Fibanacci extends RecursiveTask<Integer> {
	private final Integer val ;
	
	Fibanacci(Integer theVal) { this.val = theVal; }
	
	public Integer compute() {
		if(val <= 1) return val;
		
		Fibanacci part1 = new Fibanacci(val-1);
		part1.fork();
		Fibanacci part2 = new Fibanacci(val-2);
		part2.fork();
		return part1.join() + part2.join() ;
	}	
}