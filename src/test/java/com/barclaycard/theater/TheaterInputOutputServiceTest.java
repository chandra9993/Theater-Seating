package com.barclaycard.theater;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.barclaycard.theater.model.Order;
import com.barclaycard.theater.model.Section;

public class TheaterInputOutputServiceTest {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testReadSections() {
		TheaterInputOutputService theaterInputOutputService=new TheaterInputOutputService();
		assertEquals(theaterInputOutputService.getSectionList().size(),0);
		theaterInputOutputService.readSections("6 6", 1);
		theaterInputOutputService.readSections("3 5 5 3", 2);
		theaterInputOutputService.readSections("4 6 6 4", 3);
		theaterInputOutputService.readSections("2 8 8 2", 4);
		theaterInputOutputService.readSections("6 6", 5);
		assertEquals(theaterInputOutputService.getSectionList().size(),16);
		Section section=new Section(5,1 , 6);
		assertEquals(theaterInputOutputService.getSectionList().get(14),section);
	}
	@Test
	public void testReadOrders() {
		TheaterInputOutputService theaterInputOutputService=new TheaterInputOutputService();
		assertEquals(theaterInputOutputService.getOrderList().size(),0);
		theaterInputOutputService.readOrders("Smith 2", 1);
		theaterInputOutputService.readOrders("Jones 5", 2);
		theaterInputOutputService.readOrders("Davis 6", 3);
		theaterInputOutputService.readOrders("Wilson 100", 4);
		theaterInputOutputService.readOrders("Johnson 3", 5);
		assertEquals(theaterInputOutputService.getOrderList().size(),5);
		Order order=new Order(5, "Johnson", 5);
		assertEquals(theaterInputOutputService.getOrderList().get(4),order);
	}

}
