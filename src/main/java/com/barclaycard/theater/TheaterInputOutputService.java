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
 * 
 * @author hqcpundr
 *
 */
public class TheaterInputOutputService {

	private List<Section> sectionList = new ArrayList<>();
	private List<Order> orderList = new ArrayList<>();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TheaterInputOutputService.class);

	public void readInput() {
		Scanner scanner = new Scanner(System.in);
		int emptyLineCount = 0;
		int row = 1;
		int orderId = 1;
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
					readSections(inputLine, row++);
				} else if (emptyLineCount == 1) {
					readOrders(inputLine, orderId++);
				}
			} catch (Exception exception) {
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

	public void readOrders(String inputLine, int orderId) {
		this.orderList.add(new Order(orderId, inputLine
				.split(Constants.SINGLE_SPACE)[0], Integer.parseInt(inputLine
				.split(Constants.SINGLE_SPACE)[1])));
	}

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

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
