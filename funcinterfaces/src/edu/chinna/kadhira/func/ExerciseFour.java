package edu.chinna.kadhira.func;

import java.io.File;

import static java.util.stream.Stream.of;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static edu.chinna.kadhira.func.ExerciseFive.usingCompare;

public class ExerciseFour {
	public static void usingSplit(){
		out.println(of(new File(".").list())
					.collect(joining(",")));		
		usingCompare();
	}
}