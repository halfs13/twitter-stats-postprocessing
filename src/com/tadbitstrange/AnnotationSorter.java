package com.tadbitstrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class AnnotationSorter {
	BufferedReader reader1;
	PrintWriter writer;
	int counter = 0;
	
	public AnnotationSorter(String file1, String output) {
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			writer = new PrintWriter(output, "UTF-8");
			
		} catch (IOException e) {
			System.out.println("Error occurred while reading the file");
		}
	}
	
	public Boolean sort(){
		String line1;
		String line1Text;
		String line2;
		String line2Text;
		Boolean swapped = true;
		
		try {
			line1 = reader1.readLine();
			
			if(line1 == null) {
				return swapped;
			} else {
				//System.out.println(line1);
				line1Text = line1.substring(1, line1.indexOf("\","));
				//line1Text = 
			}
			
			while(true) {
				line2 = reader1.readLine();
				
				if(line2 == null) {
					writer.println(line1);
					return swapped;
				} else {
					//System.out.println(line2);
					line2Text = line2.substring(1, line2.indexOf("\","));
					//line2Text = 
				}
				
				int comparison = line1Text.compareTo(line2Text);
				
				if(counter % 50000 == 0) {
					System.out.println(++counter);
				} else {
					counter++;
				}
				if(comparison < 0) {
					writer.println(line1);
					line1 = line2;
					line1Text = line2Text;
				} else if(comparison > 0) {
					writer.println(line2);
					swapped = false;
				} else {
					System.out.println("Need to combine");	
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
	
	public Boolean sort2() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try{
			String line = reader1.readLine();
			
			while(line != null) {
				lines.add(line);
				line = reader1.readLine();
			}
			
			Collections.sort(lines);
			
			for(String ln : lines) {
				writer.println(ln);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void close() {
		try {
			reader1.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
