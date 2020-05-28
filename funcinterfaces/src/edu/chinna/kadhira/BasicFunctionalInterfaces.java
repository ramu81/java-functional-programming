package edu.chinna.kadhira;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.List;

import static java.lang.System.out;
import static java.util.Objects.nonNull;
import static java.util.List.of;

public class BasicFunctionalInterfaces {
	public static void helloWorld() {
		
		List<String> names = of("Ram","Vasu","Sai");
		
		Supplier<String> hello = () -> "Hello world,I hope you are doing well.";
		
		Consumer<String> printIt = temp  -> out.println(temp);
		
		Predicate<String> condition = temp -> nonNull(temp);
		
		Function<String,String> function = temp -> temp.toUpperCase();
				
		printIt.accept(hello.get());
		
		names.stream()
			 .filter(condition)
			 .map(function)
			 .forEach(printIt);

	}
}