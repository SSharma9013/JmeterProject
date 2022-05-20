package myPackage;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;

import au.com.bytecode.opencsv.*;


public class CSV_Operations {
	
	public void ConvertJsonToCSV(String Response, String Filename, String url) throws JSONException 
	   {
		try { 
	    //since the Response boday is in different format need to parse differently
		//for 1st url response is in json object so need to generate JSONArray 
		// for second url response is in JSONArray format so no need to conversion
		if(url.compareTo("first_url") == 0)
		  {
			  JSONObject object;
			  //JSONParser parser = new JSONParser();
		      //JSONObject json = (JSONObject) parser.parse(Response);
		      object = new JSONObject(Response);
		      JSONArray Array = object.getJSONArray("products");
			  File file = new File(url+"_response.csv");
		      System.out.println(object);
		      //line by line Conversion onto csv 
		      String csv = CDL.toString(Array);
		    //writing it to a file
		      FileUtils.writeStringToFile(file, csv);
		      System.out.println("Data has been Sucessfully Writen to "+ file);
		      System.out.println(csv);
		  }
		  else if(url.compareTo("second_url") == 0)
		  {
			  JSONArray Array;
			  Array = new JSONArray(Response);
			  File file = new File(url+"_response.csv");
		      System.out.println(Array);
		      //line by line Conversion onto csv 
		      String csv = CDL.toString(Array);
		      //writing it to a file
		      FileUtils.writeStringToFile(file, csv);
		      System.out.println("Data has been Sucessfully Writen to "+ file);
		      System.out.println(csv);
		  }
		  else 
		  {
		      System.out.println("Invalid url");
			  
		  }
	      //Gson gson = new Gson();
	      
	    	  //JsonObject output = gson.fromJson(Response, JsonObject.class);
	    	  //JSONObject output = gson.fromJson(Response, JSONObject.class);
	         //System.out.println(output);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public  static float FindAverage() throws Exception
 {   //Array of Discount percentages is returned from the below function that we use to find average.
		float[] DiscountPercentages = GeneratingArrayforOrderingForDiscountPercentages();
		float sum = 0;
		 for(int i=0;i<DiscountPercentages.length;i++)
			 sum += DiscountPercentages[i];
	return sum/DiscountPercentages.length;
		
 }
	
	public static List<List<String>> GeneratinglistandArrayforOrdering(String CSVFileName, int ColumnIndexforOrdering, String url ) throws IOException, ParseException 
	{
		int i=0;
		  //An Array to store all the discount percentages and due dates and saved to file which will used for sorting
		  float[] ColumToOrder = {}; 
		  Date[] datecolumn = {};
		  //List of a Lists of strings to store all the rows from the csv files. 
		  List<List<String>> list = new ArrayList<>();
 
		  //Created a reader instance which will read our csv file, 
		  //we start reading from the line number 2 as the 1st one is a header. 
		CSVReader reader = new CSVReader(new FileReader(CSVFileName), ',', '"', 1);
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
            	 		if(url.compareTo("first_url") == 0)
            	 		 {
            	 			//Appending Discount percentages of each rows to the Array.
            	 			//ColumToOrder = Arrays.copyOf(ColumToOrder, ColumToOrder.length + 1); 
            	 			//ColumToOrder[ColumToOrder.length - 1] = Float.parseFloat(nextLine[ColumnIndexforOrdering]);
            	 			//Writing Array of DiscountPercentages to a file to use it to find average
            	 			fw.write(nextLine[ColumnIndexforOrdering] + "\n");
		                 }
            	 		else if(url.compareTo("second_url") == 0)
            			  {
            	 			//datecolumn = Arrays.copyOf(datecolumn, datecolumn.length + 1); 
            	 			//Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(nextLine[ColumnIndexforOrdering]);
            	 			//datecolumn[datecolumn.length - 1] = date;
            	 			//Writing Array of DiscountPercentages to a file to use it to find average
            	 			fw.write(nextLine[ColumnIndexforOrdering] + "\n");
            			  }	
             	}
          }
	   fw.close();
	   //System.out.println(list);
	   return list;
 }
	
	public  static Date[] GeneratingArrayforOrderingForDueDate() throws Exception
	 { 		//Reads values from the TemparrayArrayfile generated in function GeneratinglistandArrayforOrdering
			File file = new File("./TemparrayArrayfile.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String nextvalue;
			Date DateArray[] = {};
			
			
			while ((nextvalue = br.readLine()) != null)
			{		//Generating replica of array and adding one index at a time 
					DateArray = Arrays.copyOf(DateArray, DateArray.length + 1); 
					//Parsing the string to date before adding it to the array
		 			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(nextvalue);
		 			//Appending new value to the previous array
		 			DateArray[DateArray.length - 1] = date;
		     }
			return DateArray;
	 }
	
	
	public  static float[] GeneratingArrayforOrderingForDiscountPercentages() throws Exception
      { 
		//Reads values from the TemparrayArrayfile generated in function GeneratinglistandArrayforOrdering
		File file = new File("./TemparrayArrayfile.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String nextvalue;
		float Array[] = {};
		int i = 0;
		
		while ((nextvalue = br.readLine()) != null)
		{
				//sum += Float.parseFloat(nextvalue);
				Array = Arrays.copyOf(Array, Array.length + 1); 
				Array[Array.length - 1] = Float.parseFloat(nextvalue);
				i++;
		}
	  return Array;
      }
	
	public  static String SortAndReturntitleForMaxPercentage(String CSVFileName, int ColomnIndexOfTitle, int ColumnIndexforOrdering, String url) throws Exception
 {
		//System.out.print(function());
		 //System.out.print(function1());
		 
		   int i;
		   List<List<String>> list = GeneratinglistandArrayforOrdering(CSVFileName,ColumnIndexforOrdering, url);
		   System.out.print(list);
		   float[] Array = GeneratingArrayforOrderingForDiscountPercentages();
		   
			//We now have an array of discounted Percentages and List with all the rows from the csv file.
		    //Sorting the array, List in ascending order, and ordering the csv based on discount Percentage 
		   for(i=0;i<Array.length;i++)
	      {
	      		for(int j = i + 1; j < Array.length; j++) 
	      	    {
	      		  
	              // Checking elements
	              float temp = 0;
	              if (Array[j] < Array[i]) 
	              {
	                  // Swapping
	                  temp = Array[i];        
	                  Array[i] = Array[j];
	                  Array[j] = temp;
	                  //swapping the whole rows as the above arrays are arranged in the ascending order the whole row is do so. 
	                  Collections.swap(list, i, j);
	              }
	            }
	      		//System.out.print("Inside");
	          // Printing sorted array elements
	          System.out.print(Array[i] + " ");
	      } 
		   System.out.print(list);
		   List<String> temp = list.get(i-1);
		 // Printing and printing the Title as required.
		   String title = temp.get(ColomnIndexOfTitle);
		   System.out.println("Title: "+title);
		   return title;
   }
	
	
	public  static String SortAndReturntitleForLatestDueDate(String CSVFileName, int ColomnIndexOfTitle, int ColumnIndexforOrdering, String url) throws Exception
 {
		//System.out.print(function());
		 //System.out.print(function1());
		 
		   int i;
		   List<List<String>> list = GeneratinglistandArrayforOrdering(CSVFileName,ColumnIndexforOrdering, url);
		   System.out.print(list);
		   Date[] DateArray = GeneratingArrayforOrderingForDueDate();
		   
			//We now have an array of due Dates and List with all the rows from the csv file.
		    //Sorting the array, List in ascending order, and ordering the csv based on discount Percentage 
		   for(i=0;i<DateArray.length;i++)
	      {
	      		for (int j = i + 1; j < DateArray.length; j++) 
	      	    {
	      		  
	              // Checking elements
	              Date temp ;
	              if (DateArray[j].before(DateArray[i]) == true)
	              {
	                  // Swapping
	                  temp = DateArray[i];        
	                  DateArray[i] = DateArray[j];
	                  DateArray[j] = temp;
	                  //swapping the whole rows as the above arrays are arranged in the ascending order the whole row is do so. 
	                  Collections.swap(list, i, j);
	              }
	            }
	      		//System.out.print("Inside");
	          // Printing sorted array elements
	          System.out.print(DateArray[i] + " ");
	      } 
		   System.out.print(list);
		   List<String> temp = list.get(i-1);
		 // Printing and printing the Title as required.
		   String title = temp.get(ColomnIndexOfTitle);
		   System.out.println("Title: "+title);
		   return title;
   }
	
	public static  void CSVFileWithNewDelimiter(char delimiter, String filename, String givencsvfile) throws Exception
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
        	  {   
        		  //checking if the elements are last in row to avoid appending with delimiter at the end 
        		  if(i<nextLine.length-1)
            	  newLine= newLine + nextLine[i]+delimiter;
        	      else
        	      newLine= newLine + nextLine[i];
        	  }
          }
          //here we end up with a row that we will write into the file
          newLine = newLine + "\n";
   	      System.out.println(newLine);
   	      fw.write(newLine);
       } fw.close();
	}
	
	public static  String convertDate(String nextLine, String Format) throws ParseException
	{
		//Function 
		Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(nextLine);
		DateFormat df = new SimpleDateFormat(Format);
		String formattednewline = df.format(date);
		System.out.println(formattednewline);
	return formattednewline;
	}
	
	public static  void CSVFileWithNewDelimiterForSecondURL(char delimiter, String filename, String givencsvfile ) throws Exception
	{
      
    //Created a reader instance which will read our csv file, 
		CSVReader reader = new CSVReader(new FileReader(givencsvfile), ',', '"', 0);
	 //Reading csv line by line, each line in nextLine string array
		String[] nextLine;
		String newLine = "";
		File f = new File("./" + filename);
		FileWriter fw = new FileWriter(f);
		int k = 0;
		while ((nextLine = reader.readNext()) != null) 
		{  //Adding the headers
			for(int i=0;i<nextLine.length;i++) 
  	        {
			if(i<nextLine.length-1)
			  newLine= newLine + nextLine[i]+delimiter;
		    else
			  newLine= newLine + nextLine[i];
  	        }
	      //We go inside this loop only from the 2nd row as we parsing dates and converting the format 1st row contains headers which cause parse error
          if (nextLine != null & k>0) 
          {   newLine = "";    
        	  for(int i=0;i<nextLine.length;i++) 
        	  {  if(i==3) //3 refers the column number for due_date
        		  newLine= newLine + convertDate(nextLine[i], "yyyyMMdd")+delimiter;
        	      else
        	      {  //System.out.println("I am here Inside main else");
        	    	  if(i<nextLine.length-1)
        				  newLine= newLine + nextLine[i]+delimiter;
        			  else
        				  newLine= newLine + nextLine[i];
        		  }
        	 }
         }
          newLine = newLine + "\n";
  	      System.out.println(newLine);
  	      fw.write(newLine);
  	      k++;
       }fw.close();
          
    } 
	public static String FindMostFrequentDay(String StrigArray[]) 
	{
	      boolean[] seen = new boolean[StrigArray.length];
	      String mostfrequent = null;
	      int mostfrequent_count = 0;
	      for (int i = 0; i < StrigArray.length; i++) {
	          if (!seen[i]) {
	              seen[i] = true;
	              int count = 1;
	              for (int j = i + 1; j < StrigArray.length; j++) {
	                  if (!seen[j]) {
	                      if (StrigArray[i].equals(StrigArray[j])) {
	                          seen[j] = true;
	                          count++;
	                      }
	                  }
	              }
	              if (count > mostfrequent_count) {
	            	  mostfrequent_count = count;
	            	  mostfrequent = StrigArray[i];
	              }
	          }
	      }
	      System.out.println(mostfrequent);
	      return mostfrequent;
	  }
	
	public static void CreateCustomCSV(String givencsvfile) throws IOException, ParseException
	{
	 //Created a reader instance which will read our csv file, 
		CSVReader reader = new CSVReader(new FileReader(givencsvfile), ',', '"', 0);
	 //Reading csv line by line, each line in nextLine string array
		String[] nextLine;
		String newLine;
		String StringArray[] = {};
		File f = new File("./temp");
		FileWriter fw = new FileWriter(f);
		int k=0;
		while ((nextLine = reader.readNext()) != null) 
		{ 
		//Adding the headers
		 newLine = "id,day_of_week,utc_offset";  
		 if (nextLine != null& k>0)
		  { newLine = "";  
			for(int i=0;i<nextLine.length;i++) 
			{
			  	if(i==1)
			  		newLine= newLine + nextLine[i] + ',';
			    else if(i==3) //3 refers the column number for due_date
			    {   //E date format returns the day of the given date and Z returns the UTC Offset.
					newLine= newLine + convertDate(nextLine[i], "E") + ',';
					newLine= newLine + convertDate(nextLine[i], "Z");
					//Keeping an array of due days to use if for finding the name of the file(most frequent due day)
					StringArray = Arrays.copyOf(StringArray, StringArray.length + 1); 
					StringArray[StringArray.length - 1] = convertDate(nextLine[i], "E");
			  }
			  else
			      System.out.print("Skipped");
	          } 
		  }newLine = newLine + "\n";
	          System.out.println(newLine);
	          fw.write(newLine);
	          k++;
			      
		}
		fw.close();
        File rename = new File("./customized_" + FindMostFrequentDay(StringArray)+".csv");
        f.renameTo(rename);
	}
	
	
	//###-------------------------------Main Function-------------------------##//
	public static void main(String[] args) throws Exception
	{
		//System.out.println(GeneratinglistandArrayforOrdering("second_url_response.csv", 3, "second_url" ));
		System.out.println(SortAndReturntitleForMaxPercentage("first_url_response.csv", 7, 0, "first_url"));
		//CSVFileWithNewDelimiterForSecondURL('|', "Something.csv", "second_url_response.csv");
		//log.info("Average Percentage: " + Test.FindAverage());
		//NewDelimiter(';', newfilename, "first_url_response.csv");
		//log.info("File with new delimiter should be created");
		//CreateCustomCSV("second_url_response.csv");
		CreateCustomCSV("second_url_response.csv");
    }
	
}
	