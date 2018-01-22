package com.barclaycard.theater;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.barclaycard.theater.constants.OrderStatus;
import com.barclaycard.theater.model.Order;
import com.barclaycard.theater.model.Section;
/***
 * Class to represent TheaterAllocationService Tests
 * @author hqcpundr
 *
 */
public class TheaterAllocationServiceTest {
	private List<Section> sections=new ArrayList<>();
	private List<Order> orders=new ArrayList<>(); 
	/***
	 * Set up for Theater layout and Theator requests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Section section1= new Section(1, 1, 4);
		Section section2= new Section(1, 2, 6);
		Section section3= new Section(2, 1, 2);
		sections.add(section1);
		sections.add(section2);
		sections.add(section3);
		Order order1=new Order(1,"john",2);
		Order order2=new Order(2,"miller",3);
		Order order3=new Order(3,"robert",4);
		Order order4=new Order(4,"stalin",40);
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);
	}
	/***
	 * to Test successful requests
	 */
	@Test
	public void testSuccessfullRequest(){
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		theaterAllocationService.allocateSeating(sections, orders);
		Order order=orders.get(0);
		assertNotNull(order.getSection());
		Section section3= new Section(2, 1, 2);
		assertEquals(order.getSection().getRowId(), section3.getRowId());
		assertEquals(order.getSection().getSecId(), section3.getSecId());
		assertEquals(order.getSection().getAvailCapacity(), 0);
		assertNotEquals(order.getOrderStatus(),OrderStatus.CAN_NOT_HANDLE);
		assertNotEquals(order.getOrderStatus(),OrderStatus.SPLIT_REQUESTED);
		assertEquals(order.getOrderStatus(),OrderStatus.COMPLETED);
	}
	/**
	 * TO test test Can not handled request
	 */
	@Test
	public void testCanNotHandledRequest(){
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		theaterAllocationService.allocateSeating(sections, orders);
		Order order=orders.get(3);
		assertEquals(order.getOrderStatus(),OrderStatus.CAN_NOT_HANDLE);
		assertNotEquals(order.getOrderStatus(),OrderStatus.COMPLETED);
		
	}
	/***
	 * To test splitRequest
	 */
	@Test
	public void testSplitRequest(){
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		Order order=new Order(5,"stalin",7);
		orders.add(order);
		theaterAllocationService.allocateSeating(sections, orders);
		order=orders.get(4);
		assertEquals(order.getOrderStatus(),OrderStatus.SPLIT_REQUESTED);
		assertNotEquals(order.getOrderStatus(),OrderStatus.COMPLETED);
	}
	/***
	 * TO test AssignOrder
	 */
	@Test
	public void testAssignOrder(){
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		Section section=new Section(5, 1, 5);
		Order order=new Order(5, "kevin", 3);
		assertNull(order.getSection());
		theaterAllocationService.assignOrder(section, order);
		assertNotNull(order.getSection());
		assertSame(order.getSection(),section);
		assertEquals(section.getAvailCapacity(), 2);
		assertEquals(section.getOrders().size(),1);
	
	}
	/**
	 * To test allocate seating
	 */
	@Test
	public void testAllocateSeating() {
		TheaterAllocationService theaterAllocationService=new TheaterAllocationService();
		List<Order> expectedOrders=new ArrayList<>();
		Order order1=new Order(1,"john",2);
		order1.setOrderStatus(OrderStatus.COMPLETED);
		Section section1=new Section(2, 1, 2);
		order1.setSection(section1);
		
		Order order2=new Order(2,"miller",3);
		order2.setOrderStatus(OrderStatus.COMPLETED);
		Section section2=new Section(1, 1, 6);
		order2.setSection(section2);
		
		Order order3=new Order(3,"robert",4);
		order3.setOrderStatus(OrderStatus.COMPLETED);
		Section section3=new Section(1, 2, 4);
		order3.setSection(section3);
		
		Order order4=new Order(4,"stalin",40);
		order4.setOrderStatus(OrderStatus.CAN_NOT_HANDLE);
		
		expectedOrders.add(order1);
		expectedOrders.add(order2);
		expectedOrders.add(order3);
		expectedOrders.add(order4);
		
		theaterAllocationService.allocateSeating(sections, orders);
		
		for(int i=0;i<orders.size();i++){
			assertEquals(orders.get(i).getOrderStatus(),expectedOrders.get(i).getOrderStatus());
			Section actSection=orders.get(i).getSection();
			Section expSection=expectedOrders.get(i).getSection();
			if(expectedOrders.get(i).getOrderStatus()==OrderStatus.COMPLETED){
				assertEquals(actSection.getRowId(), expSection.getRowId());
				assertEquals(actSection.getSecId(),expSection.getSecId());
			}
		}
		
	}

}
