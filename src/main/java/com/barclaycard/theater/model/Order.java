package com.barclaycard.theater.model;

import com.barclaycard.theater.constants.OrderNote;
import com.barclaycard.theater.constants.OrderStatus;
/***
 * Order class represents theater ticket request
 * @author hqcpundr
 *
 */
public class Order {
	/**
	 * Incremental id will be given for each order
	 */
	private int id;
	/**
	 * name of the requested person
	 */
	private String name;
	/**
	 * requested quantity
	 */
	private int qty;
	/**
	 * Section to assign this request
	 */
	private Section section;
	/***
	 * Initial order status is Requested
	 */
	private OrderStatus orderStatus= OrderStatus.REQUESTED;
	/***
	 * Order note if any 
	 */
	private OrderNote orderNote;
	/***
	 * Order constructor
	 * @param id
	 * @param name
	 * @param qty
	 */
	public  Order(int id, String name, int qty){
		this.id = id;
		this.name = name;
		this.qty = qty;
	}
	/***
	 * Order constructor
	 * @param id
	 * @param name
	 * @param qty
	 * @param orderStatus
	 */
	public  Order(int id, String name, int qty,OrderStatus orderStatus){
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.orderStatus=orderStatus;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderNote getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(OrderNote orderNote) {
		this.orderNote = orderNote;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", name='" + name + '\'' +
				", qty=" + qty +
				", section=" + section +
				", orderStatus=" + orderStatus +
				", response='" + orderNote + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
