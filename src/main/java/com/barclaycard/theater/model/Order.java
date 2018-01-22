package com.barclaycard.theater.model;

import com.barclaycard.theater.constants.OrderNote;
import com.barclaycard.theater.constants.OrderStatus;

public class Order {
	private int id;
	private String name;
	private int qty;
	private Section section;
	private OrderStatus orderStatus= OrderStatus.REQUESTED;
	private OrderNote orderNote;

	public  Order(int id, String name, int qty){
		this.id = id;
		this.name = name;
		this.qty = qty;
	}
	
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
