package com.barclaycard.theater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheaterHelper {
private static final Logger LOGGER=LoggerFactory.getLogger(TheaterHelper.class);
	public  List<Row> getLayout(Scanner scanner) {
		LOGGER.info("Please enter theator layout");
		int rowId=1;
		ArrayList<Row> rowList=new ArrayList<>();
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if(line.equals("")){
				break;
			}
			Row row = lineToIntArray(line,rowId);
			rowList.add(row);
			rowId++;
		}
		System.out.println(rowList.toString());
		return rowList;
	}

	private  Row lineToIntArray(String line,int rowId) {
		String[] cols = line.split(" ");
		Row row=new Row();
		row.setId(rowId);
		for(int i=0;i<cols.length;i++){
			Section section=new Section();
			section.setId(i+1);
			section.setInitialCapacity(Integer.parseInt(cols[i]));
			section.setAvailCapacity(Integer.parseInt(cols[i]));
			row.getSections().add(section);
		}
		return row;
	}

	private Order lineToOrder(String line,int id) {
		Order order=new Order();
		String[] str = line.split(" ");
		order.setId(id);
		order.setName(str[0]);
		order.setQty(Integer.parseInt(str[1]));
		return order;
	}


	public void parseOrder(List<Row> rowList,List<Order> orders) {
		for(Order order:orders){
			for(Row row:rowList){
				for(Section section:row.getSections()){
					if(order.getQty()<=section.getAvailCapacity()){
						order.setSection(section);
						section.getOrders().add(order);
					}
				}
			}
		}
	}

	/*private  void allocateOrder(Section section,List<Order> orders) {
		int sum=Arrays.stream(arr).flatMapToInt(x -> Arrays.stream(x)).sum();
		if(qty>sum){
			LOGGER.info("{} Sorry, we can't handle your party.",name);
			return;
		}
		
			
		}
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				if(qty<=arr[i][j]){
					LOGGER.info("{} Row {}  Section {} ",name,(i+1),(j+1));
					arr[i][j]=arr[i][j]-qty;
					return;
				}
			}
		}
		//LOGGER.info("{} Call to split party.",name);
	}*/

	public List<Order> getOrders(Scanner scanner) {
		List<Order> orders=new ArrayList<Order>();
		int i=0;
		while(scanner.hasNextLine()){
		String line=scanner.nextLine();
		if(line.equals("")){
			break;
		}
		orders.add(lineToOrder(line,i));
		}
		return orders;
	}
}
