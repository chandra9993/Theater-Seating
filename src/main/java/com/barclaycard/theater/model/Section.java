package com.barclaycard.theater.model;

import java.util.ArrayList;
import java.util.List;

public class Section {

	private int secId;
	private int rowId;
	private int initialCapacity;
	private int availCapacity;
	private List<Order> orders = new ArrayList<>();

	public Section(int rowId, int secId, int initialCapacity) {
		this.rowId = rowId;
		this.secId = secId;
		this.initialCapacity = initialCapacity;
		this.availCapacity = initialCapacity;
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

	public int getSecId() {
		return secId;
	}

	public void setSecId(int secId) {
		this.secId = secId;
	}

	@Override
	public String toString() {
		return "Section{" +
				", rowId=" + rowId +
				"secId=" + secId +
				", initialCapacity=" + initialCapacity +
				", availCapacity=" + availCapacity +
				'}';
	}
}
