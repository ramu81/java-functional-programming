package edu.chinna.kadhira.func;

import java.util.concurrent.ExecutorService;
import java.util.Optional;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.IntStream.rangeClosed;
import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.SECONDS;
import static edu.chinna.kadhira.func.ExerciseTwo.usingIterator;
import static edu.chinna.kadhira.func.ExerciseThree.usingOptional;
import static edu.chinna.kadhira.func.ExerciseFour.usingSplit;


class ExerciseOne {
	
	public static void main(String[] args) {
		
		ExecutorService service = newFixedThreadPool(5);
		
		rangeClosed(1,10).
		forEach(i -> service.submit(()-> 
			{out.println("  "+i);
			try { SECONDS.sleep(2); }
			catch(InterruptedException e){out.println(e.getMessage());}
			}
		));
		
		try { service.awaitTermination(1,SECONDS);} 
		catch(InterruptedException e){out.println(e.getMessage());}
		
		service.shutdownNow();
		
		out.println("  "+usingIterator().size());
		
		Optional<BookingInfo> lBookingOption = usingOptional();
		
		lBookingOption.ifPresentOrElse(a -> out.println(a.getBookingInfo())
		,()-> out.println("Booking not avail"));
		usingSplit();
	}
	
}