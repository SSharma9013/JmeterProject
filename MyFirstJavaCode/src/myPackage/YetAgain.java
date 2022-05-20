package myPackage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;


public class YetAgain 
{
	public static void main(String[] args) throws Exception
    {
		float[] Numbers = {};
		int i=0;
        ArrayList <String> list = new ArrayList<String>();

		CSVReader reader = new CSVReader(new FileReader("EmpDetails.csv"), ',', '"', 1);
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        for(String[] row : allRows)
        {
        	Numbers = Arrays.copyOf(Numbers, Numbers.length + 1);
        	String tempArray = Arrays.toString(row);
            list.add(tempArray);
        	Numbers[Numbers.length - 1] = Float.parseFloat(row[0]);
	    	//System.out.println("I am here");
	    	//System.out.println(Numbers.length);
        } //end of rows in csv file
 
        //We now have an array of discounted Percentages and List with tall the rows from the csv file.
       // Sorting the array, List and ordering the csv based on discount Percentage 
        for(i=0;i<Numbers.length;i++)
        {
        	for (int j = i + 1; j < Numbers.length; j++) 
        	{
                // Checking elements
                float temp = 0;
                if (Numbers[j] < Numbers[i]) 
                {
                    // Swapping
                    temp = Numbers[i];        
                    Numbers[i] = Numbers[j];
                    Numbers[j] = temp;
                    Collections.swap(list, i, j);
                }
            }
            // Printing sorted array elements
            System.out.print(Numbers[i] + " ");
        }
        // Printing sorted list
        System.out.print(list);  
        String Temp = list.get(i-1);
        String[] strArray = Temp.split(","); 
        System.out.print(Temp);  
        for (i = 0; i< strArray.length; i++){  
        	System.out.println(strArray[i]);  
        	}  
        System.out.print(strArray[3]);  

        }
    }


