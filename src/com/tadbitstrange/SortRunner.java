package com.tadbitstrange;

public class SortRunner {
	public static void main(String[] args) {
		String tmp1 = "/home/halfs13/Desktop/results/out_56_61.csv";
		
		AnnotationSorter sorter = new AnnotationSorter("/home/halfs13/Desktop/results_56_61.csv", tmp1);
		Boolean done = sorter.sort2();
		sorter.close();
	}
}
