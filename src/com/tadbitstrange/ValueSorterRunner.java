package com.tadbitstrange;


public class ValueSorterRunner {
	public static void main(String[] args) {
	
		String tmp1 = "/home/halfs13/Desktop/results/value_sort_0_61.csv";
		
		ValueSorter sorter = new ValueSorter("/home/halfs13/Desktop/out_0_61_else.csv", tmp1);
		Boolean done = sorter.sort2();
		sorter.close();
	}
}
