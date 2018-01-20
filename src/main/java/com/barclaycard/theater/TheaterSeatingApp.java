package com.barclaycard.theater;

import java.util.List;
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
		TheaterHelper theater=new TheaterHelper();
		List<Row> rowList=theater.getLayout(scanner);
		List<Order> orders=theater.getOrders(scanner);
		theater.parseOrder(rowList,orders);	
		scanner.close();
		LOGGER.info("Program Ended.");
	}
}
