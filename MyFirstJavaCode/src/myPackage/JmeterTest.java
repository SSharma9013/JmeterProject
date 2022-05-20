package myPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import au.com.bytecode.opencsv.CSVReader;

public class JmeterTest 
{
	public void function4(String Response, String Filename) throws JSONException 
	   {
	      JSONObject output;
	      JSONArray output2;
	      //Gson gson = new Gson();
	      try {
	    	  //JsonObject output = gson.fromJson(Response, JsonObject.class);
	    	  //JSONObject output = gson.fromJson(Response, JSONObject.class);
		     output2 = new JSONArray(Response);
	    	 JSONParser parser = new JSONParser();
	    	 JSONObject json = (JSONObject) parser.parse(Response);
	         output = new JSONObject(Response);
	         System.out.println(output);
	         JSONArray docs = output.getJSONArray("products");
	         File file = new File("first_url_response.csv");
	         System.out.println(output2);
	         String csv = CDL.toString(output2);
	         FileUtils.writeStringToFile(file, csv);
	         System.out.println("Data has been Sucessfully Writen to "+ file);
	         System.out.println(csv);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public  static float function3() throws Exception
    { 
		float[] DiscountPercentages = function1();
		float sum = 0;
		 for(int i=0;i<DiscountPercentages.length;i++)
			 sum += DiscountPercentages[i];
	return sum/DiscountPercentages.length;
		
    }
	
	public static List<List<String>> function() throws IOException 
	{
		int i=0;
		  //An Array to store all the discount percentages which will used for sorting
		  float[] DiscountPercentages = {}; 
		  //List of a Lists of strings to store all the rows from the csv files. 
		  List<List<String>> list = new ArrayList<>();
    
  //Created a reader instance which will read our csv file, 
  //we start reading from the line number 2 as the 1st one is a header. 
		CSVReader reader = new CSVReader(new FileReader("EmpDetails1.csv"), ',', '"', 1);
	 //Reading csv line by line, each line in nextLine string array
		String[] nextLine;
		File f = new File("./" + "TemparrayArrayfile.txt");
		FileWriter fw = new FileWriter(f);
		while ((nextLine = reader.readNext()) != null) 
		{
        if (nextLine != null) 
        {
          //Converting string array returned by reading the csv to list of strings
        	List<String> strList = new ArrayList<String>();
        	Collections.addAll(strList, nextLine);
         //Appending the list of strings to the list of rows.
            Collections.addAll(list, strList);
          //Appending Discount percentages of each rows to the Array.
        	DiscountPercentages = Arrays.copyOf(DiscountPercentages, DiscountPercentages.length + 1); 
            DiscountPercentages[DiscountPercentages.length - 1] = Float.parseFloat(nextLine[3]);
            //Writing Array of DiscountPercentages to a file to use it to find average
            fw.write(nextLine[3] + "\n");
        }
        }
	   fw.close();
	   //System.out.println(list);
	   return list;
  }
	
	
	public  static float[] function1() throws Exception
    { 
		File file = new File("./TemparrayArrayfile.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String nextvalue;
		float DiscountPercentages[] = {};
		int i = 0;
		while ((nextvalue = br.readLine()) != null)
		{
			//sum += Float.parseFloat(nextvalue);
			DiscountPercentages = Arrays.copyOf(DiscountPercentages, DiscountPercentages.length + 1); 
            DiscountPercentages[DiscountPercentages.length - 1] = Float.parseFloat(nextvalue);
			i++;
		}
	  return DiscountPercentages;
    }
	
	public  static String function2() throws Exception
    {
		//System.out.print(function());
		 //System.out.print(function1());
		 
		   int i;
		   List<List<String>> list = function();
		   System.out.print(list);
		   float[] DiscountPercentages = function1();
			//We now have an array of discounted Percentages and List with tall the rows from the csv file.
		    //Sorting the array, List in ascending order, and ordering the csv based on discount Percentage 
		   for(i=0;i<DiscountPercentages.length;i++)
	      {
	      		for (int j = i + 1; j < DiscountPercentages.length; j++) 
	      	    {
	              // Checking elements
	              float temp = 0;
	              if (DiscountPercentages[j] < DiscountPercentages[i]) 
	              {
	                  // Swapping
	                  temp = DiscountPercentages[i];        
	                  DiscountPercentages[i] = DiscountPercentages[j];
	                  DiscountPercentages[j] = temp;
	                  Collections.swap(list, i, j);
	              }
	            }
	      		//System.out.print("Inside");
	          // Printing sorted array elements
	          System.out.print(DiscountPercentages[i] + " ");
	      } 
		   System.out.print(list);
		   List<String> temp = list.get(i-1);
		 // Printing and printing the Title as required.
		   String title = temp.get(0);
		   System.out.println("Title: "+title);
		   return title;
    }
	
	
	//###-------------------------------Main Function-------------------------##//
	public static void main(String[] args) throws Exception
	{
		System.out.print(function3());
		
    }
	
}


