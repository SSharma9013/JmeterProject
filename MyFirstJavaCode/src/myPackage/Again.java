package myPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Again {
	   public static void main1(String[] args) throws Exception
	   {
		   	String[] myList = {"1.9", "2.9", "3.4", "3.5"};
	           //Numbers[Numbers.length - 1] = Float.parseFloat(row[3]);

		      // Print all the array elements
		      for (int i = 0; i < myList.length; i++) {
		         //System.out.println(myList[i] + " ");
		      }
		      
		       float[] Numbers = {};
		       float[] Number = {};
		       for(int i= 0;i<5;i++) {
		           //Number[i] = Float.parseFloat(myList[i]);
		    	   Number = Arrays.copyOf(Numbers, Numbers.length + 1);
		    	   Numbers = Arrays.copyOf(Number, Number.length + 1);
		    	   Numbers[(Numbers.length)- 1] = i;
		           Number[Number.length - 1] = Float.parseFloat(myList[i]);
		    	   System.out.println(Numbers[i]);
		    	   System.out.println(Number[i]);
		    	   

		       }

	   }
	   
	   public void JsonToCSV(String Response, String Filename) throws JSONException 
	   {
	      JSONObject output;
	      //Gson gson = new Gson();
	      try {
	    	  //JsonObject output = gson.fromJson(Response, JsonObject.class);
	    	  //JSONObject output = gson.fromJson(Response, JSONObject.class);
	         output = new JSONObject(Response);
	         System.out.println(output);
	         JSONArray docs = output.getJSONArray("products");
	         File file = new File("first_url_response.csv");
	         System.out.println(docs);
	         String csv = CDL.toString(docs);
	         FileUtils.writeStringToFile(file, csv);
	         System.out.println("Data has been Sucessfully Writen to "+ file);
	         System.out.println(csv);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public  static float Averagepercentage() throws Exception
    { 
		File file = new File("./TemparrayArrayfile"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String nextvalue;
		float sum = 0;
		int i = 0;
		while ((nextvalue = br.readLine()) != null)
		{
			sum += Float.parseFloat(nextvalue);
			i++;
		}
	       System.out.println("sum: "+sum);

		return sum/i;
    }
	
	public static  void NewDelimiter(char delimiter, String filename, String givencsvfile) throws Exception
	{
      
    //Created a reader instance which will read our csv file, 
		CSVReader reader = new CSVReader(new FileReader(givencsvfile), ',', '"', 0);
	 //Reading csv line by line, each line in nextLine string array
		String[] nextLine;
		String newLine = "";
		File f = new File("./" + filename);
		FileWriter fw = new FileWriter(f);
		while ((nextLine = reader.readNext()) != null) 
		{
          if (nextLine != null) 
          {   newLine = "";    
        	  for(int i=0;i<nextLine.length;i++) 
        	  {   if(i<nextLine.length-1)
            	  newLine= newLine + nextLine[i]+delimiter;
        	      else
        	      newLine= newLine + nextLine[i];
        	  }
          }
          newLine = newLine + "\n";
   	      System.out.println(newLine);
   	      fw.write(newLine);
       } fw.close();
	}
	
	public  void NewCSVFile(String filename) throws Exception
	{
		   //Build reader instance
	      CSVReader reader = new CSVReader(new FileReader(filename), ',', '"', 1);
	      //Read all rows at once
	      List<String[]> allRows = reader.readAll();
	       
	   	  File file = new File("data.csv");
	     
	     try {
	         // create FileWriter object with file as parameter
	         FileWriter outputfile = new FileWriter(file);
	   
	         // create CSVWriter with '|' as separator
	         CSVWriter writer = new CSVWriter(outputfile, ';',
	                                          CSVWriter.NO_QUOTE_CHARACTER,
	                                          CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                                          CSVWriter.DEFAULT_LINE_END);
	         
	         writer.writeAll(allRows);
	   
	         // closing writer connection
	         writer.close();
	     }
	     catch (IOException e) {
           e.printStackTrace();
	     }
	}
	
	public  static String SortandReturnTitle( String givencsvfile,int colomnIndex1, int colomnIndex2 ) throws Exception
    {     
		   int i=0;
		  //An Array to store all the discount percentages which will used for sorting
		  float[] DiscountPercentages = {}; 
		  //List of a Lists of strings to store all the rows from the csv files. 
		  List<List<String>> list = new ArrayList<>();
        
      //Created a reader instance which will read our csv file, 
      //we start reading from the line number 2 as the 1st one is a header. 
		CSVReader reader = new CSVReader(new FileReader(givencsvfile), ',', '"', 1);
	 //Reading csv line by line, each line in nextLine string array
		String[] nextLine;
		File f = new File("./" + "TemparrayArrayfile");
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
                DiscountPercentages[DiscountPercentages.length - 1] = Float.parseFloat(nextLine[colomnIndex1]);
                //Writing Array of DiscountPercentages to a file to use it to find average
                fw.write(nextLine[colomnIndex1] + "\n");
            }
       }
	   fw.close();
	   System.out.println(list);
	   
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
           // Printing sorted array elements
           System.out.print(DiscountPercentages[i] + " ");
       }
     // Printing sorted list
       System.out.print(list);
       List<String> temp = list.get(i-1);
    // Printing and printing the Title as required.
       String title = temp.get(colomnIndex2);
       System.out.println("Title: "+title);
       
       return title;
            
    }
	
  public  static void main2(String[] args) throws Exception
  {     
   SortandReturnTitle("EmpDetails1.csv",3,1);
   System.out.println(Averagepercentage());

  }
  
  static String mostFrequent(String[] arr, int n)
  {
       
      // Insert all elements in hash
      Map<String, Integer> hp =
             new HashMap<String, Integer>();
       
      for(int i = 0; i < n; i++)
      {
          String key = arr[i];
          if(hp.containsKey(key))
          {
              int freq = hp.get(key);
              freq++;
              hp.put(key, freq);
              System.out.println(hp);
              
          }
          else
          {
              hp.put(key, 1);
          }
      }
       
      // find max frequency.
      int max_count = 0;
	String res = "-1";
       
      for(Entry<String, Integer> val : hp.entrySet())
      {
          if (max_count < val.getValue())
          {
        	  System.out.println("I am here");
              res = val.getKey();
              max_count = val.getValue();
        	  System.out.println(res);

          }
      }
       
      return res;
  }
   
  
  public static void main5(String[] args) throws Exception
  {

      // Custom string as input
      String strDate = "2022-06-14T00:00:00.000+05:30";

      // Creating an object of Date class with reference
      // to SimpleDateFormat class and
      // lately parsing the above string into it
		Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(strDate);
		//for offset
		DateFormat df = new SimpleDateFormat("Z");
		String reportDate = df.format(date);
        //String split[] = strDate.split("-");
        //for (String s: split)
            //System.out.println(s);

      // Print and display the date corresponding
      // to above input string
      System.out.print(strDate + " " + date + "---" + reportDate);
      
      Dictionary<Integer, String> dic = new Hashtable<Integer, String>();
      dic.put(1, "Jay");
      dic.put(2, "Suresh");
      dic.put(3, "Jay");
      dic.put(4, "Ramya");
      dic.put(5, "Jay");
      String[] arr = {"Mon","Tues", "wed","Tues", "wed","Mon","Mon"};
      System.out.println("Size of the dictionary: " + dic.size());
      System.out.println("Value of the key 3: " + dic.get(3));
      System.out.println("Name of the key i hope: " + dic.keys());
      
      List<List<String>> list = new ArrayList<>();
      
      
          int  i, j, len, count=0;
          String t;
          //Scanner in = new Scanner(System.in);
          System.out.println("Enter number of elements to insert in an array: ");
          len = arr.length;
          //int[] arr = new int[len];
          System.out.println("Enter elements to insert in an array: ");
          System.out.println("\n");
          for(i=0;i<len;i++)
          {
              count=1;
              for(j=i+1;j<=len-1;j++)
              {
                  if(arr[i]==arr[j] && arr[i]!="\0")
                  {
                      count++;
                      arr[j] = "\0";
                  }
              }
              if(arr[i]!="\0")
              {
                  System.out.println(arr[i] + " is " + count + " times.\n");
              }
          }        
  
          int n = arr.length;
          
          System.out.println(mostFrequent(arr, n));        
          
  }
  
  public static void main(String[] args)
  {
      String[] football_club = {"Mon","Tues", "wed","Tues", "wed","Mon","Mon"};
      boolean[] seen = new boolean[football_club.length];
      String result_club = null;
      int result_count = 0;
      for (int i = 0; i < football_club.length; i++) {
          if (!seen[i]) {
              seen[i] = true;
              int count = 1;
              for (int j = i + 1; j < football_club.length; j++) {
                  if (!seen[j]) {
                      if (football_club[i].equals(football_club[j])) {
                          seen[j] = true;
                          count++;
                      }
                  }
              }
              if (count > result_count) {
                  result_count = count;
                  result_club = football_club[i];
              }
          }
      }
      System.out.println(result_club);
  }
  
  
}



