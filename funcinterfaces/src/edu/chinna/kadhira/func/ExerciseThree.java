package edu.chinna.kadhira.func;

import java.util.Random;
import java.util.List;
import static java.util.List.of;
import java.util.Optional;

public class ExerciseThree {
	
	private static List<BookingInfo> lBookingInfoList = of(new BookingInfo(),new BookingInfo(),
	new BookingInfo(),new BookingInfo(),new BookingInfo(),new BookingInfo());
	
	public static Optional<BookingInfo> usingOptional(){
		return lBookingInfoList.stream()
							   .filter(BookingInfo::isSlotAvail)
							   .findFirst();
	}
}

class BookingInfo {
	
	public String getBookingInfo(){
		return "---"+hashCode();
	}
	
	public boolean isSlotAvail(){
		return new Random().nextBoolean();
	}
}