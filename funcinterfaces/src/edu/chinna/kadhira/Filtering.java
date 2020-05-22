package edu.chinna.kadhira;

import java.util.List;

import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static java.lang.System.out;

public class Filtering {
	public static void main(String[] args) {
		List<String> names = of("Ram","Vasantha","Sai","Laxman");
		List<String> threeLetterNames = names.stream()
											 .filter(name -> name.length() == 3)
											 .map(String::toUpperCase)
											 .collect(toList());
		out.println(threeLetterNames);
											 
	}
}