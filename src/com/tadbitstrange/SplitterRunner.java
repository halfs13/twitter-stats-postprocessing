package com.tadbitstrange;

public class SplitterRunner {
	public static void main(String[] args) {
		String inFile = "/home/halfs13/Desktop/out_0_61.csv";
		String file1 = "/home/halfs13/Desktop/out_0_61_ones.csv";
		String file2 = "/home/halfs13/Desktop/out_0_61_twos.csv";
		String file3 = "/home/halfs13/Desktop/out_0_61_else.csv";
		
		Splitter splitter = new Splitter(inFile, file1, file2, file3);
		Boolean done = splitter.split();
		splitter.close();
	}
}