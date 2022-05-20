# JmeterProject
--Note from Suraksha--
-----------------------

AssignmentFolder holds all the required classes and jmx files which will perform the below mentioned tasks using userdefined library CSVOperations.
And other external libraries are also included which are used in the project. 
Steps to run thr project 
Get the Assighmemt.jmx file, the external and user defined (CSVOperations) jar files to a particular location and run the jmeter project. 
Might need to add Jar CSVOperations.jar to class path from library section in Jmeter. 

Test scenarios for Mannual testcases are in tetcases file inside AssignmentFolder. 

JMeter Tasks
------------------------
(First URL – https://dummyjson.com/products
Second URL - https://gorest.co.in/public/v2/todos) 

1. Use JMeter to send GET request to first URL, employ JSON parse library  (org.json.simple)to convert JSON response and store it in a CSV file (first_url_response.csv). 
CSV Format Sample: 
id,title,description,price,discountPercentage,rating,stock,brand
1,iPhone 9,An apple mobile which is nothing like apple,549,12.96,4.69,94, Apple 

2. Prepare java code to perform following operations on first_url_response.csv: 
  i. Order based on discount Percentage, return ‘product title’ with maximum discount percentage. 
  ii. Return average discount percentage. 
  iii. Create new csv using semicolon as delimiter, file name should be  semicolon_${File_Creation_Date}.csv
  
3. Use JMeter to send GET request to secondURL, employ JSON parse library  (org.json.simple)to convert and store it in a CSV file (second_url_response.csv). 
CSV Format Sample: 
id,user_id,title,due_on,status
2016,4066,Ulciscor quis tempore verecundia necessitatibus paulatim non armarium.,2022- 04-25T00:00:00.000+05:30,completed 

4. Prepare java code to perform following operations on second_url_response.csv: 
  i. Order based on ’due_on’ column, return ‘title’ with latest due date.
  ii. Create new csv using pipes as delimiter where column ‘due_on’should be in  ‘YYYYMMDD’ format, csv name should be pipes_${File_Creation_Date}.csv 
  iii. Create new csv with three columns (id,day_of_week,utc_offset) (grab new  values from original ‘due_on’ value), csv name should be  customized_${most_frequent_day_due_day}.csv Note:All above task are expected to be done programmatically. 

Please do not forget to add  descriptive comments in your code

Manual Testing Tasks(Write test cases for below scenarios)

5. You are a new customer, and you want to open a credit card account then there are  three conditions: first you will get a 15% discount on your first purchase, second if you are an existing customer and you hold a loyalty card, you get a 10% discount and third if you have a coupon, you can get 20% off today (but it can’t be used with the ‘new customer’ discount).

6. Your application has a search engine, you need to search a word: automobiles. The results are displayed in order of priority, title and number of occurrences of the search word. Write all the test cases to test the search engine and if the results are displayed accordingly.
