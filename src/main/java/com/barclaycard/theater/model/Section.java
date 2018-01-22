package com.barclaycard.theater.model;

import java.util.ArrayList;
import java.util.List;
/***
 * Section class to represent section of each row
 * It will maintain secId,rowId,initialCapacity and availCapacity
 * and orders assigned to that session
 * 
 * @author hqcpundr
 *
 */
public class Section {
	/**
	 * secId is unique number to identify each section
	 */
	private int secId;
	/**
	 * row id is unique number for each row
	 */
	private int rowId;
	/**
	 * initial capacity for each section
	 */
	private int initialCapacity;
	/**
	 * availCapacity for each section
	 */
	private int availCapacity;
	/**
	 * orders associated with each section
	 */
	private List<Order> orders = new ArrayList<>();
	/***
	 * Section constructor
	 * @param rowId
	 * @param secId
	 * @param initialCapacity
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rowId;
		result = prime * result + secId;
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
		Section other = (Section) obj;
		if (rowId != other.rowId)
			return false;
		if (secId != other.secId)
			return false;
		return true;
	}
}
