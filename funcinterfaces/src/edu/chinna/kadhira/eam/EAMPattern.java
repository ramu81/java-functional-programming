package edu.chinna.kadhira.eam;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.System.out;

public class EAMPattern {
	
	public static void main(String[] args) throws IOException {
	
		for( var i =0; i<50; i++) {
			new Heavy();
		}
		
		try (ResourceHandling handle = new ResourceHandling()){
			handle.write("Hi Ram");
			handle.write("this is ");
		}
		
		EAMResource.useResource(EAMResource::displayInfo);
		
		String temp = Resource.useResource(Resource::displayInfo);
		
		Integer val = Resource.useResource(Resource::displayVal);
		
		out.println(temp+"    "+val);
	}
}

class Heavy {
	private int[] a = null;
	Heavy() {
		a = new int[1000000];
	}
	
	public void finalize() {
		out.println(" Calling finalize");
	}
}

class ResourceHandling implements AutoCloseable {
	private FileWriter fileWriter = null;
	
	ResourceHandling() throws IOException {
		fileWriter = new FileWriter("./build/sample.txt");
	}
	
	public void write(String writeText) throws IOException {
		fileWriter.write(writeText);
	}
	
	public void close() throws IOException {
		fileWriter.close();
	}
}

class EAMResource {
	
	private EAMResource() {
	}
	
	public void displayInfo(){
		out.println(" Hi ");
	}
	
	public void close() {
		out.println("Closing the EAMResource");
	}
	
	public static void useResource(Consumer<EAMResource> consumer){
		EAMResource resource = new EAMResource();
		try {
			consumer.accept(resource);
		} finally {
			resource.close();
		}
	}
}


class Resource {
	
	private Resource() {
	}
	
	public String displayInfo(){
		return " Hi ";
	}
	
	public Integer displayVal(){
		return 9;
	}
	
	public void close() {
		out.println("Closing the Resource ");
	}
	
	public static <R> R useResource(Function<Resource,R> function){
		Resource resource = new Resource();
		try {
			return function.apply(resource);
		} finally {
			resource.close();
		}
	}
}