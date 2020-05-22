package edu.chinna.kadhira;

import static java.lang.System.out;
import static java.lang.System.nanoTime;

import java.util.function.Supplier;
import java.util.function.Function;

public class TimeIt {
	
	public static void reportTime(Runnable code){
		reportTime(null, unused -> { code.run(); return null;});
	}
	
	public static <T> T reportTime(Supplier<T> code){
		return reportTime(null, unused ->  code.get());
	}
	
	public static <T,R> R reportTime(T val,Function<T,R> code){
		long startTime = nanoTime();
		try {
			return code.apply(val);
		} catch(RuntimeException e) {
			out.println(" "+e.getMessage());
		} finally {
			long endTime = nanoTime();
			out.println(" Elapsed Time : " +(endTime-startTime)/1.e09);
		}
		return null;
	}
}