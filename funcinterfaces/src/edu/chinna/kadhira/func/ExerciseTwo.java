package edu.chinna.kadhira.func;

import java.util.List;
import static java.util.stream.Stream.iterate;
import static java.util.stream.Collectors.toList;

class ExerciseTwo { 
	public static List<Double> usingIterator(){
		return iterate(1, x-> x+1)
			   .filter(x -> x%2 == 0)
			   .limit(100)
			   .map(Math::sqrt)
			   .collect(toList());
	}
}