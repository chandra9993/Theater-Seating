package com.barclaycard.theater;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barclaycard.theater.constants.Constants;
import com.barclaycard.theater.constants.OrderNote;
import com.barclaycard.theater.constants.OrderStatus;
import com.barclaycard.theater.model.Order;
import com.barclaycard.theater.model.Section;
/***
 * TheaterAllocationService class 
 * 
 * @author hqcpundr
 *
 */
public class TheaterAllocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheaterAllocationService.class);
    /***
     * Method to allocate seating based on sectionList and orderList
     * @param sectionList
     * @param orderList
     */
    public void allocateSeating(List<Section> sectionList, List<Order> orderList) {
    	TheaterAllocationService theaterAllocationService=new TheaterAllocationService();	 
    	//each order and sectionList pass as arguments to arrangeSeat
    	orderList.forEach(order -> {
    		theaterAllocationService.arrangeSeat(sectionList, order);
    	});
    }
    /***
     * method to arrangeSeat based on below rules as mentioned in problem
     * Rule 1.	Fill as many orders as possible
	   Rule 2.	Put parties as close to the front as possible.
	   Rule 3.	If there are not enough seats available in the theater to handle a party, tell them "Sorry, we can't handle your party."
	   Rule 4.	Each party must sit in a single row in a single section.  If they won't fit, tell them "Call to split party".

     * @param sectionList
     * @param order
     */
    public void arrangeSeat(List<Section> sectionList, Order order) {
    	//finding out total initial capacity
    	int totalInitCapcity=sectionList.stream().mapToInt(Section::getInitialCapacity).sum();
    	//checking Rule 3
    	if(order.getQty()>totalInitCapcity){
    		order.setOrderStatus(OrderStatus.CAN_NOT_HANDLE);
    		order.setOrderNote(OrderNote.CAN_NOT_HANDLE);
    		return;
    	}
    	//finding maximum Initial capacity of from all sections
    	int maxInitCapcity=sectionList.stream().mapToInt(Section::getInitialCapacity).max().getAsInt();
    	//checking Rule4
    	if(order.getQty()>maxInitCapcity){
    		order.setOrderStatus(OrderStatus.SPLIT_REQUESTED);
    		order.setOrderNote(OrderNote.SPLIT_PARTY);
    		return;
    	}
    	//get AvailRowId to find out row number with non zero available capacity
    	int qtyAvailRowId=1;
    	qtyAvailRowId=sectionList.stream().filter(s->s.getAvailCapacity()!=0).findFirst().get().getRowId();
    	//CLOSE_THRESHOLD is used to achieve to Rule1 and Rule 2 requirements
    	// current CLOSE_THRESHOLD set to 1 
    	//Iterating section list for each order to find perfect section for requested order if find assign that
    	//section to that order and continue with next order
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
    	//If exact match not found then look for section where that order can fit into it and assign to it
    	for (Section section : sectionList) {
    		if (section.getAvailCapacity() >= order.getQty()) {
    			assignOrder(section,order);
    			break;
    		}
    	}
    	//if exact match not found and  appropriate section not found then rearrange
    	if (order.getOrderStatus() == OrderStatus.REQUESTED) {
    		//canReArrange is boolean variable ,true if rearrange is possible else false
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
    		if(!canReArrange){
    			//to find total available capacity
    			int totalAvailCapcity=sectionList.stream().mapToInt(Section::getAvailCapacity).sum();
    			if(order.getQty()<=totalAvailCapcity){
    				//checking Rule4 in case rearrange is not possible
    				order.setOrderStatus(OrderStatus.SPLIT_REQUESTED);
    				order.setOrderNote(OrderNote.SPLIT_PARTY);
    			}else{
    				//checking Rule 3 in case if  rearrange not possible
    				order.setOrderStatus(OrderStatus.CAN_NOT_HANDLE);
    				order.setOrderNote(OrderNote.CAN_NOT_HANDLE);
    			}
    		}
    	}
    }
    /***
     * Method to assign order
     * @param section
     * @param order
     */
    public void assignOrder(Section section, Order order) {
    	section.getOrders().add(order);
    	order.setSection(section);
    	section.setAvailCapacity(section.getAvailCapacity() - order.getQty());
    	order.setOrderStatus(OrderStatus.COMPLETED);
    }
}
