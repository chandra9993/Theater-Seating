package com.barclaycard.theater;

import java.util.List;

public class Section {
	
	private int id;
	private int initialCapacity;
	private int availCapacity;
	private List<Order> orders;
	private int rowId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInitialCapacity() {
		return initialCapacity;
	}
	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	public int getAvailCapacity() {
		return availCapacity;
	}
	public void setAvailCapacity(int availCapacity) {
		this.availCapacity = availCapacity;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	@Override
	public String toString() {
		return "Section [id=" + id + ", initialCapacity=" + initialCapacity
				+ ", availCapacity=" + availCapacity + ", orders=" + orders
				+ ", rowId=" + rowId + "]";
	}
	
}
