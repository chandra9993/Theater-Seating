package com.barclaycard.theater;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barclaycard.theater.constants.Constants;
import com.barclaycard.theater.constants.OrderStatus;
import com.barclaycard.theater.model.Order;
import com.barclaycard.theater.model.Section;

public class TheaterAllocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheaterAllocationService.class);
    /***
     * Method to allocate seating
     * @param sectionList
     * @param orderList
     */
    public void allocateSeating(List<Section> sectionList, List<Order> orderList) {
    	TheaterAllocationService theaterAllocationService=new TheaterAllocationService();	 
    	orderList.forEach(order -> {
    		theaterAllocationService.arrangeSeat(sectionList, order);
    	});
    }
    /***
     * method to arrangeSeat
     * @param sectionList
     * @param order
     */
    public void arrangeSeat(List<Section> sectionList, Order order) {
    	int totalInitCapcity=sectionList.stream().mapToInt(Section::getInitialCapacity).sum();
    	if(order.getQty()>totalInitCapcity){
    		order.setOrderStatus(OrderStatus.CAN_NOT_HANDLE);
    		return;
    	}
    	int maxInitCapcity=sectionList.stream().mapToInt(Section::getInitialCapacity).max().getAsInt();
    	if(order.getQty()>maxInitCapcity){
    		order.setOrderStatus(OrderStatus.SPLIT_REQUESTED);
    		return;
    	}
    	int qtyAvailRowId=1;
    	qtyAvailRowId=sectionList.stream().filter(s->s.getAvailCapacity()!=0).findFirst().get().getRowId();
    	for (Section section : sectionList) {
    		if(section.getRowId()<=qtyAvailRowId+Constants.CLOSE_THRESHOLD)	{
    			if(section.getAvailCapacity()==order.getQty()){
    				assignOrder(section,order);
    				return;
    			}
    		}else{
    			break;
    		}
    	}
    	for (Section section : sectionList) {
    		if (section.getAvailCapacity() >= order.getQty()) {
    			assignOrder(section,order);
    			break;
    		}
    	}
    	if (order.getOrderStatus() == OrderStatus.REQUESTED) {
    		boolean canReArrange=false;
    		for (Section section : sectionList) {
    			if (order.getQty() <= section.getInitialCapacity() && section.getAvailCapacity() > (section.getInitialCapacity() - order.getQty())) {
    				canReArrange=true;
    				allocateSeating(sectionList,section.getOrders());
                    section.setOrders(new ArrayList<>());
                    order.setOrderStatus(OrderStatus.COMPLETED);
                    order.setSection(section);
                    section.getOrders().add(order);
    			}
    		}
    		int totalAvailCapcity=sectionList.stream().mapToInt(Section::getAvailCapacity).sum();
        	if(!canReArrange&&order.getQty()<=totalAvailCapcity){
        		order.setOrderStatus(OrderStatus.SPLIT_REQUESTED);
        		return;
        	}
    	}
    }
    /***
     * Method to assign order
     * @param section
     * @param order
     */
    private void assignOrder(Section section, Order order) {
    	section.getOrders().add(order);
    	order.setSection(section);
    	section.setAvailCapacity(section.getAvailCapacity() - order.getQty());
    	order.setOrderStatus(OrderStatus.COMPLETED);
    }
}
