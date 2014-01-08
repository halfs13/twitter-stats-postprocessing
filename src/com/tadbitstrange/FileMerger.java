package com.tadbitstrange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileMerger {
	BufferedReader reader1;
	BufferedReader reader2;
	PrintWriter writer;
	int counter = 0;
	int uniques = 0;
	int dupes = 0;
	
	public FileMerger(String file1, String file2, String output) {
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			reader2 = new BufferedReader(new FileReader(file2));
			writer = new PrintWriter(output, "UTF-8");
			
		} catch (IOException e) {
			System.out.println("Error occurred while reading the file");
		}
	}
	
	public Boolean merge(){
		ArrayList<String> lines1 = new ArrayList<String>();
		ArrayList<String> lines2 = new ArrayList<String>();
		
		try{
			String line = reader1.readLine();
			
			while(line != null) {
				lines1.add(line);
				line = reader1.readLine();
			}
			
			System.out.println("Done file 1");
			
			line = reader2.readLine();
			while(line != null) {
				lines2.add(line);
				line = reader2.readLine();
			}
			
			System.out.println("Done file 2");
			
			return merge(lines1, lines2);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean merge(ArrayList<String> lines1, ArrayList<String> lines2){
//		ArrayList<String> result = new ArrayList<String>();
		
		String line1;
		String text1;
		int count1;
		String line2;
		String text2;
		int count2;
		
		while(lines1.size() > 0 || lines2.size() > 0) {
			if(counter % 10000 == 0) {
				System.out.println((counter/1000000f) + " million complete ~~ uniques:" + uniques + " ~~ dupes:" + dupes);
			}
			
			if(lines1.size() != 0 && lines2.size() !=0) {
				//get text
				line1 = lines1.get(0);
				text1 =  line1.substring(1, line1.indexOf("\","));
				line2 = lines2.get(0);
				text2 = line2.substring(1, line2.indexOf("\","));
				
				//compare
				int compare = text1.compareTo(text2);
//				System.out.println("Comparing " + counter );
				if(compare < 0) {
					//line1
//					result.add(lines1.remove(0));
					writer.println(lines1.remove(0));
					counter++;
					uniques++;
				} else if(compare > 0) {
					//line2
//					result.add(lines2.remove(0));
					writer.println(lines2.remove(0));
					counter++;
					uniques++;
				} else {
					//combine
//					System.out.print("Match found at count " + counter + "; totaling --");
					count1 = Integer.parseInt(line1.substring(line1.indexOf("\", ")+3));
					count2 = Integer.parseInt(line2.substring(line2.indexOf("\", ")+3));
					String newline = ("\"" + text1 + "\", " + (count1 + count2));
//					System.out.println("Result: " + newline);
//					result.add(newline);
					writer.println(newline);
					
					lines1.remove(0);
					lines2.remove(0);
					counter++;
					dupes++;
				}
			} else if(lines1.size() == 0) {
//				result.add(lines2.remove(0));
				writer.println(lines2.remove(0));
				counter++;
				uniques++;
			} else if(lines2.size() == 0) {
//				result.add(lines1.remove(0));
				writer.println(lines1.remove(0));
				counter++;
				uniques++;
			}
		}
		
		return true;
		
		//return writeResultArray(result);
	}
	
	public Boolean merge2() {
		try{
			String line1 = reader1.readLine();
			String text1;
			int count1;
			String line2 = reader2.readLine();
			String text2;
			int count2;
			
			while(line1 != null || line2 != null) {
				if(counter % 10000 == 0) {
					System.out.println((counter/1000000f) + " million complete ~~ uniques:" + uniques + " ~~ dupes:" + dupes);
				}
				
				if(line1 != null && line2 != null) {
					//get text
					text1 =  line1.substring(1, line1.indexOf("\","));
					try{
						text2 = line2.substring(1, line2.indexOf("\","));
					} catch (Exception e) {
						System.out.println("Oh shit" + line2);
						text2 = "";
						System.exit(1);
					}
					
					//compare
					int compare = text1.compareTo(text2);
	//				System.out.println("Comparing " + counter );
					if(compare < 0) {
						//line1
	//					result.add(lines1.remove(0));
						writer.println(line1);
						line1 = reader1.readLine();
						counter++;
						uniques++;
					} else if(compare > 0) {
						//line2
	//					result.add(lines2.remove(0));
						writer.println(line2);
						line2 = reader2.readLine();
						counter++;
						uniques++;
					} else {
						//combine
	//					System.out.print("Match found at count " + counter + "; totaling --");
						count1 = Integer.parseInt(line1.substring(line1.indexOf("\", ")+3));
						count2 = Integer.parseInt(line2.substring(line2.indexOf("\", ")+3));
						String newline = ("\"" + text1 + "\", " + (count1 + count2));
	//					System.out.println("Result: " + newline);
	//					result.add(newline);
						writer.println(newline);
						
						line1 = reader1.readLine();
						line2 = reader2.readLine();
						counter++;
						dupes++;
					}
				} else if(line1 == null) {
	//				result.add(lines2.remove(0));
					writer.println(line2);
					line2 = reader2.readLine();
					counter++;
					uniques++;
				} else if(line2 == null) {
	//				result.add(lines1.remove(0));
					writer.println(line1);
					line1 = reader1.readLine();
					counter++;
					uniques++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private Boolean writeResultArray(ArrayList<String> result) {
		for(String str : result) {
			writer.println(str);
		}
		System.out.println(counter);
		return true;
	}

	public void close() {
		try {
			reader1.close();
			reader2.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
