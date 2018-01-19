package com.barclaycard.theater;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Theater {
private static final Logger LOGGER=LoggerFactory.getLogger(Theater.class);
	public  int[][] getLayout(Scanner scanner) {
		LOGGER.info("Please enter how many lines you want to enter for Layout?");
		int rows = scanner.nextInt();
		scanner.nextLine();
		LOGGER.info("Please enter {} lines of data",rows);
		int[][] arr = new int[rows][];
		for (int i = 0; i < rows; ++i) {
			String line = scanner.nextLine();
			arr[i] = lineToIntArray(line);
		}
		return arr;
	}

	private  int[] lineToIntArray(String line) {
		String[] cols = line.split(" ");
		int[] ints = new int[cols.length];
		for (int i = 0; i < cols.length; ++i) {
			ints[i] = Integer.parseInt(cols[i]);
		}
		//Arrays.asList(line.split(" ")).stream().mapToInt(Integer::parseInt).toArray();
		return ints;
	}

	private String[] lineToStringArray(String line) {
		String[] str = line.split(" ");
		return str;
	}


	public void parseOrder(String[][] strArr,int[][] arr) {
		for(int i=0;i<strArr.length;i++){
			String name=strArr[i][0];
			int qty=Integer.parseInt(strArr[i][1]);
			printItem(name,qty,arr);
		}
	}

	private  void printItem(String name, int qty, int[][] arr) {
		int sum=Arrays.stream(arr).flatMapToInt(x -> Arrays.stream(x)).sum();
		if(qty>sum){
			LOGGER.info("{} Sorry, we can't handle your party.",name);
			return;
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
		LOGGER.info("{} Call to split party.",name);
	}

	public String[][] getRequests(Scanner scanner) {
		LOGGER.info("Please enter how manys lines you want to enter for requests?");
		int rows = scanner.nextInt();
		scanner.nextLine();
		LOGGER.info("Please enter {} lines of data",rows);
		String[][] strArr = new String[rows][];
		for (int i = 0; i < rows; ++i) {
			String line = scanner.nextLine();
			strArr[i] = lineToStringArray(line);
		}
		return strArr;
	}
}
