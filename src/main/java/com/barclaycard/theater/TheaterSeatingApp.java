package com.barclaycard.theater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
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
		theaterInputOutputService.readInput();
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		theaterAllocationService.allocateSeating(theaterInputOutputService.getSectionList(),
				theaterInputOutputService.getOrderList());
		theaterInputOutputService.printOutput();
		LOGGER.info("Program Ended.");
	}
}
