package com.barclaycard.theater;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/***
 * 
 * @author hqcpundr
 *
 */
public class TheaterSeatingApp {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TheaterSeatingApp.class);
	public static void main(String[] args) {
		LOGGER.info("Program Starting..");
		Scanner scanner = new Scanner(System.in);
		Theater theater=new Theater();
		int[][] layoutArr=theater.getLayout(scanner);
		String[][] requestsArr=theater.getRequests(scanner);
		theater.parseOrder(requestsArr,layoutArr);	
		scanner.close();
		LOGGER.info("Program Ended.");
	}
}
