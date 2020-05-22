package edu.chinna.kadhira;

import java.util.List;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.List.of;
import static edu.chinna.kadhira.TimeIt.reportTime;

class Func {
	public static void main(String[] args) {
		
		reportTime(() -> useless());
		out.println("       ");
		reportTime(() -> doubleIt(0));
		int doubleValue = reportTime(() -> doubleIt(1));
		out.println(" value : "+doubleValue);
		
		List<Integer> list = of(3,4,5);
		
		list.stream()
		.map(index -> reportTime(index, value -> doubleIt(value-1)))
		.forEach(val -> out.println(" value : "+val));
		
	}
	
	public static void useless(){
		try {
			SECONDS.sleep(1);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int doubleIt(int a){
		try {
			if ( a < 1 ) 
				throw new RuntimeException(" I am not accepting less than one values"); 
			SECONDS.sleep(1);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		return a*2;
	}
	
}