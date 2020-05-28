package edu.chinna.kadhira;

import java.util.List;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static edu.chinna.kadhira.forkjoinpool.PoolInducedDeadlock.test;

/**
Evalution of method reference is Egar.
Lambda is a lazy evalution.
SAMS--Single Abstract Method Interface

*/
public class MethodRef {
	public static void using(List<Person> persons){
			out.println("*******************************");
			persons.stream()
				   .sorted(Person::comapareAge)
				   .forEach(out::println);
			out.println("*******************************");
			test(500);
	}
}