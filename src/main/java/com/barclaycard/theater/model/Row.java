package com.barclaycard.theater.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
 int id;
 List<Section> sections=new ArrayList<>();
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public List<Section> getSections() {
	return sections;
}
public void setSections(List<Section> sections) {
	this.sections = sections;
}
@Override
public String toString() {
	return "Row [id=" + id + ", sections=" + sections + "]";
}
 
}
