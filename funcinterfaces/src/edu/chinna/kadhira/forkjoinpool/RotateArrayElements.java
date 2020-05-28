package edu.chinna.kadhira.forkjoinpool;

import static java.util.Objects.nonNull;
import static java.lang.System.out;
import static java.util.stream.Stream.of;

public class  RotateArrayElements {
	public static void test(String[] elements,int numOfTimes) {
		if(nonNull(elements)) {
			int length = elements.length;
			out.println("***********RotateArrayElements*****************");	
			of(elements).forEach(out::println);
			numOfTimes %= length;
			if(numOfTimes >= 1 ) {
				for(int i=0; i<numOfTimes ; i++) moveOnePosition(elements);
			}
			out.println("************************************************");
			of(elements).forEach(out::println);
			out.println("***********RotateArrayElements*****************");	
		}
	}
	
	private static void moveOnePosition(String[] elements){
		String temp = elements[0];
		for( int i =1; i < elements.length ; i++) {
			String temp2 = elements[i];
			elements[i] = temp;
			temp=temp2;
		}
		elements[0]= temp;
	}
}