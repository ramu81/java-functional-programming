package edu.chinna.kadhira.func;

import static java.lang.String.format;
import static java.util.List.of;
import static java.lang.System.out;
import static java.util.stream.IntStream.range;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

import java.util.function.Function;
import java.io.Serializable;
import java.util.List;
import java.util.Comparator;

public class ExerciseFive {
	
	public static void usingCompare(){
		
		List<Person> persons = of(new Person("Ram",40),
			new Person("Vasantha",35),new Person("Sai",5));
			
		Comparator<Person> byAge = (p1,p2) -> p1.age.compareTo(p2.age);
		
		persons.stream()
			   .sorted(byAge.reversed())
			   .forEach(out::println);
			   
		persons.stream()
			   .sorted((p1,p2) -> p1.name.compareTo(p2.name))
			   .forEach(out::println);
		out.println("    ");
		usingCompareVer2();
	}
	
	public static void usingCompareVer2(){
		
		List<Person> persons = of(new Person("Ram",40),
			new Person("Vasantha",35),new Person("Sai",5));
			
		Function<Person,Integer> ageBy = Person::getAge;	
		
		Comparator<Person> byAge = (p1,p2) -> ageBy.apply(p1).compareTo(ageBy.apply(p2));
				
		persons.stream()
			   .sorted(camparing(Person::getAge).reversed())
			   .forEach(out::println);
			   
		persons.stream()
			   .sorted(camparing(Person::getName))
			   .forEach(out::println);
			   
		range(1,8).forEach(i -> out.println(i+" Prime  : "+isPrime(i)));
		out.println();
		range(1,8).forEach(i -> out.println(i+" Prime  : "+isAdvPrime(i)));
		
		countWords();
	}
	
	public static <T extends Comparable> Comparator<Person> camparing(Function<Person,T> ageBy){
		return (p1,p2) -> ageBy.apply(p1).compareTo(ageBy.apply(p2));
	}
	
	public static boolean isPrime(Integer num) {
		boolean flag = true;
		for(var i =2; i<num ; i++) 
			if(num % i == 0 )  { flag= false; break;}
		return num>1 && flag; 
	}
	
	public static boolean isAdvPrime(Integer num) {
		
		return num>1 && range(2,num)
						.noneMatch(i-> num % i == 0); 
	}
	
	public static void countWords() {
		try {
			long count = lines(get("./build.bat"))
						.filter(a -> a.contains("build"))
						.count();
			out.println(" build  number of times : "+count);
		}catch(java.nio.file.InvalidPathException | java.io.IOException e){e.printStackTrace();}
	}
}

class Person implements Serializable {
	
	String name;
	Integer age;
	
	Person(String theName,Integer age){ this.name = theName;this.age=age;}
	
	Integer getAge() {return this.age;}
	
	String getName() {return this.name;}
	
	public String toString() { return format( " %s -- %d ",this.name,this.age); }
}