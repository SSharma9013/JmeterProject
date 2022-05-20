package myPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.json.*;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

public class Trying 
{
	public  static float[] Averagepercentage() throws Exception
    { 
		File file = new File("./TemparrayArrayfile"); 
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
	
	 public List<List<String>> Anotherfunction(String givencsvfile,int colomnIndex1, int colomnIndex2) throws JSONException, IOException 
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
	   //System.out.println(list);
	   return list;
	   }
	
   public void main() throws Exception 
   {
	   int i;
	   List<List<String>> list = Anotherfunction("EmpDetails1.csv", 3, 0);
	   System.out.print(list);
	   float[] DiscountPercentages = Averagepercentage();
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
      		System.out.print("Inside");
          // Printing sorted array elements
          System.out.print(DiscountPercentages[i] + " ");
      }
    // Printing sorted list
      System.out.print(list);
      List<String> temp = list.get(i-1);
   // Printing and printing the Title as required.
      String title = temp.get(1);
      System.out.println("Title: "+title);
      
   }
   
   public static void main1(String[] args) throws Exception
   {
       float[] Numbers = {1,2,3,4,5}; 
       System.out.println(Numbers);
       for(int i= 0;i<5;i++) {
       Numbers = Arrays.copyOf(Numbers, Numbers.length + 1);
       Numbers[(Numbers.length)- 1] = i;
       }
       System.out.println(Numbers);

   }
   
   
   //Function to find Maximum discount percentage
      //@SuppressWarnings("resource")
      public static void main2(String[] args) throws Exception
      {
         //Build reader instance
         CSVReader reader = new CSVReader(new FileReader("EmpDetails.csv"), ',', '"', 1);
          
         //Read all rows at once
         List<String[]> allRows = reader.readAll();
         ArrayList <String> list = new ArrayList<String>();
         float[] Numbers = new float[] {};  
         	int i =0;
         //Read CSV line by line and use the string array as you want
        for(String[] row : allRows)
        {
           String tempArray = Arrays.toString(row);
           //System.out.println(tempArray);
           //System.out.println(row[3]);    
           list.add(tempArray);
           //Numbers = Arrays.copyOf(Numbers, Numbers.length + 1);
           //Numbers[Numbers.length - 1] = Float.parseFloat(row[3]);
           for(int j=0;j < Numbers.length; j++) 
           {
        	   float temp = 0;
               if (Numbers[j] < Numbers[i]) {

                   // Swapping
                   temp = Numbers[i];
                   Numbers[i] = Numbers[j];
                   Numbers[j] = temp;
                   System.out.println(Numbers[i]);
                   
               }
               i++;
               

        	   
           }
           

        }
      }
      
      public static void main(String[] args) throws Exception
      {
         //Build reader instance
       //Read data.csv
       //Default seperator is comma
       //Default quote character is double quote
       //Start reading from line number 2 (line numbers start from zero)
  		  float[] Numbers = {};
  		  int i=0;
          //ArrayList <String> list = new ArrayList<String>();
  		  List<List<String>> list = new ArrayList<>();


         CSVReader reader = new CSVReader(new FileReader("EmpDetails1.csv"), ',' , '"' , 1);
          
         //Read CSV line by line and use the string array as you want
         String[] nextLine;
         while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
            	Numbers = Arrays.copyOf(Numbers, Numbers.length + 1); 
            	//Arrays.toString(nextLine);
            	List<String> strList = new ArrayList<String>();
            	Collections.addAll(strList, nextLine);
                Collections.addAll(list, strList);
                //System.out.println(strList.get(1));

                //list.addAll(nextLine);
            	Numbers[Numbers.length - 1] = Float.parseFloat(nextLine[3]);
               //System.out.println(Arrays.toString(nextLine));
            }
            
          } System.out.println(list.get(2));
            List<String> temp = list.get(2);
            System.out.println(temp.get(3));
      }
   }
