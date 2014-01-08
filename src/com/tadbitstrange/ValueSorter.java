package com.tadbitstrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ValueSorter {
	BufferedReader reader1;
	PrintWriter writer;
	int counter = 0;
	
	public ValueSorter(String file1, String output) {
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			writer = new PrintWriter(output, "UTF-8");
			
		} catch (IOException e) {
			System.out.println("Error occurred while reading the file");
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
			
			System.out.println("Done reading file!");
			
			Collections.sort(lines, new Comparator<String> () {
			    public int compare(String a, String b) {
			        int value =  Integer.parseInt(b.substring(b.indexOf("\", ")+3)) - Integer.parseInt(a.substring(a.indexOf("\", ")+3));
			        if(value != 0) {
			        	return value;
			        } else {
			        	return a.compareTo(b);
			        }
			    }
			});
			
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
