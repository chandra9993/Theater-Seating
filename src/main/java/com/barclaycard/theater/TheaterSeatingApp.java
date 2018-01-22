package com.barclaycard.theater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * This is main class to run the TheaterSeating Application
 * It has main method
 * @author hqcpundr
 *
 */
public class TheaterSeatingApp {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TheaterSeatingApp.class);

	public static void main(String[] args) {
		LOGGER.info("Program started..");
		System.out.println("Please enter input");
		TheaterInputOutputService theaterInputOutputService = new TheaterInputOutputService();
		//To read user input
		theaterInputOutputService.readInput();
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		//To allocate seating
		theaterAllocationService.allocateSeating(theaterInputOutputService.getSectionList(),
				theaterInputOutputService.getOrderList());
		//To  print output 
		theaterInputOutputService.printOutput();
		LOGGER.info("Program Ended.");
	}
}
