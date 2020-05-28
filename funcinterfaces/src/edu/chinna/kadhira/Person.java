package edu.chinna.kadhira;

import java.io.Serializable;

import static java.lang.String.format;

public class Person implements Serializable {
	
	public String name;
	public Integer age;
	
	public Person(String theName,Integer age){ this.name = theName;this.age=age;}
	
	public Integer getAge() {return this.age;}
	
	public String getName() {return this.name;}
	
	public String toString() { return format( " %s -- %d ",this.name,this.age); }
	
	public static int comapareAge(Person p1, Person p2){ return p1.getAge() - p2.getAge();}
}