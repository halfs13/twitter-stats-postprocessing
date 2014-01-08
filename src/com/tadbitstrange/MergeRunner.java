package com.tadbitstrange;

public class MergeRunner {
	public static void main(String[] args) {
		String file1 = "/home/halfs13/Desktop/out_0_50.csv";
		String file2 = "/home/halfs13/Desktop/out_51_61.csv";
		String output = "/home/halfs13/Desktop/out_0_61.csv";
		
		FileMerger merger = new FileMerger(file1, file2, output);
		Boolean done = merger.merge2();
		merger.close();
	}
}
