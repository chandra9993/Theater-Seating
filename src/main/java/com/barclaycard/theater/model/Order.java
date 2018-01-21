package com.barclaycard.theater.model;

import com.barclaycard.theater.constants.OrderStatus;

public class Order {
	private int id;
	private String name;
	private int qty;
	private Section section;
	private OrderStatus orderStatus= OrderStatus.REQUESTED;
	private String response;

	public  Order(int id, String name, int qty){
		this.id = id;
		this.name = name;
		this.qty = qty;
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

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", name='" + name + '\'' +
				", qty=" + qty +
				", section=" + section +
				", orderStatus=" + orderStatus +
				", response='" + response + '\'' +
				'}';
	}
}
