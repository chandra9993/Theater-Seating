package com.barclaycard.theater.constants;
/***
 * Order note for each order if any
 * 
 * @author hqcpundr
 *
 */
public enum OrderNote {
	CAN_NOT_HANDLE("  Sorry, we can't handle your party."),
	SPLIT_PARTY("  Call to split party.");
	String note;
	OrderNote(String note){
		this.note=note;	
	}
	public String getNote() {
		return note;
	}
}
