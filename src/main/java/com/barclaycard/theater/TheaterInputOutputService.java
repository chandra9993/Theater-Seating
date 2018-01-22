package com.barclaycard.theater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barclaycard.theater.constants.Constants;
import com.barclaycard.theater.constants.OrderStatus;
import com.barclaycard.theater.model.Order;
import com.barclaycard.theater.model.Section;

/***
 * This class represents the read input to read rows,sections and orders/requests and 
 * print the orders/requests with status
 * @author hqcpundr
 */
public class TheaterInputOutputService {
	/**
	 * sectionList to hold sections 
	 */
	private List<Section> sectionList = new ArrayList<>();
	/**
	 * OrderList to hold orders/requests
	 */
	private List<Order> orderList = new ArrayList<>();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TheaterInputOutputService.class);
	/***
	 * Method readInput to read input and create list of section objects and 
	 * list of order objects from user input for layout and requests
	 * 
	 */
	public void readInput() {
		//Scanner object creation to read console input read
		Scanner scanner = new Scanner(System.in);
		int emptyLineCount = 0;
		int row = 1;
		int orderId = 1;
		//keep reading until more than one empty lines in the input
		//theater layout input and theater requests separated by empty line
		while (scanner.hasNextLine()) {
			try {
				String inputLine = scanner.nextLine();
				if (Constants.BLANK.equals(inputLine.trim())) {
					emptyLineCount++;
					if (emptyLineCount > 1) {
						break;
					} else {
						continue;
					}
				}
				if (emptyLineCount == 0) {
					//to read sections from input line
					readSections(inputLine, row++);
				} else if (emptyLineCount == 1) {
					//to read orders from input line
					readOrders(inputLine, orderId++);
				}
			} catch (Exception exception) {
				//clear the section and orders list incase if it finds any exception during input
				sectionList.clear();
				orderList.clear();
				LOGGER.error("Please enter valid input");
			}
		}
		scanner.close();
		if (sectionList.size() == 0 || orderList.size() == 0) {
			LOGGER.info("The input provides is invalid.\n Please Rerun the application with valid inputs");
			return;
		}

	}
	/***
	 * Method to read Layout Input  and create sections list from it
	 * @param inputStr
	 * @param rowNo
	 */
	public void readSections(String inputStr, int rowNo) {
		String[] sections = inputStr.split(Constants.SINGLE_SPACE);
		AtomicInteger secId = new AtomicInteger(1);
		List<Section> secList = Arrays
				.stream(sections)
				.map(s -> {
					Section ss = new Section(rowNo, secId.getAndIncrement(),
							Integer.parseInt(s));
					return ss;
				}).collect(Collectors.toList());
		this.sectionList.addAll(secList);
	}
	/***
	 * Method to read theater request and create orders list from it
	 * @param inputLine
	 * @param orderId
	 */
	public void readOrders(String inputLine, int orderId) {
		this.orderList.add(new Order(orderId, inputLine
				.split(Constants.SINGLE_SPACE)[0], Integer.parseInt(inputLine
				.split(Constants.SINGLE_SPACE)[1])));
	}
	/***
	 * Method to print out
	 */
	public void printOutput() {
		orderList.forEach(k -> {
			if (k.getOrderStatus() == OrderStatus.COMPLETED) {
				System.out.println(k.getName() + "  Row " + k.getSection().getRowId()
						+ " Section " + k.getSection().getSecId());
			} else if (k.getOrderStatus() == OrderStatus.SPLIT_REQUESTED) {
				System.out.println(k.getName() + k.getOrderNote().getNote());
			} else {
				System.out.println(k.getName() + k.getOrderNote().getNote());
			}
		});
	}
	/**
	 * Method to get sections list
	 * @return
	 */
	public List<Section> getSectionList() {
		return sectionList;
	}
	/***
	 * Method to set sections list
	 * @param sectionList
	 */
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
	/***
	 * Method to get Orders List
	 * @return
	 */
	public List<Order> getOrderList() {
		return orderList;
	}
	/***
	 * Method to set orders List
	 * @param orderList
	 */
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
