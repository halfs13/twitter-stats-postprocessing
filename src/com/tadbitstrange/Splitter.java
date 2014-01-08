package com.tadbitstrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

public class Splitter {
	BufferedReader reader;
	PrintWriter writer_ones;
	PrintWriter writer_twos;
	PrintWriter writer_else;
	int counter = 0;
	int ones = 0;
	int twos = 0;
	int elses = 0;
	
	public Splitter(String inFile, String file1, String file2, String fileElse) {
		try {
			reader = new BufferedReader(new FileReader(inFile));
			writer_ones = new PrintWriter(file1, "UTF-8");
			writer_twos = new PrintWriter(file2, "UTF-8");
			writer_else = new PrintWriter(fileElse, "UTF-8");
			
		} catch (IOException e) {
			System.out.println("Error occurred while reading the file");
		}
	}
	
	public Boolean split(){
		try{
			String line = reader.readLine();
			int count;
		
			while(line != null) {
				if(counter % 100000 == 0) {
					System.out.println((counter/1000000f) + " million complete ~~ ones:" + ones + " ~~ twos:" + twos + " ~~ more:" + elses);
				}
				
				count = Integer.parseInt(line.substring(line.indexOf("\", ")+3));
				
				if(count == 1) {
					//write to 1
					writer_ones.println(line);
					ones++;
				} else if(count == 2){
					//write to 2
					writer_twos.println(line);
					twos++;
				} else {
					//write to else
					writer_else.println(line);
					elses++;
				}
				counter++;
				line = reader.readLine();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			close();
			System.exit(1);
		}
		return false;
	}

	public void close() {
		try {
			reader.close();
			writer_ones.close();
			writer_twos.close();
			writer_else.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}