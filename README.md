# JmeterProject
JMeter Tasks
(First URL – https://dummyjson.com/products
Second URL - https://gorest.co.in/public/v2/todos) 

1. Use JMeter to send GET request to first URL, employ JSON parse library  (org.json.simple)to convert JSON response and store it in a CSV file (first_url_response.csv). CSV Format Sample: id,title,description,price,discountPercentage,rating,stock,brand 1,iPhone 9,An apple mobile which is nothing like apple,549,12.96,4.69,94, Apple 
2. Prepare java code to perform following operations on first_url_response.csv: 
  i. Order based on discount Percentage, return ‘product title’ with maximum discount percentage. 
  ii. Return average discount percentage. 
  iii. Create new csv using semicolon as delimiter, file name should be  semicolon_${File_Creation_Date}.csv 
3. Use JMeter to send GET request to secondURL, employ JSON parse library  (org.json.simple)to convert and store it in a CSV file (second_url_response.csv). CSV Format Sample: id,user_id,title,due_on,status 2016,4066,Ulciscor quis tempore verecundia necessitatibus paulatim non armarium.,2022- 04-25T00:00:00.000+05:30,completed 
4. Prepare java code to perform following operations on second_url_response.csv: 
  i. Order based on ’due_on’ column, return ‘title’ with latest due date.
  ii. Create new csv using pipes as delimiter where column ‘due_on’should be in  ‘YYYYMMDD’ format, csv name should be pipes_${File_Creation_Date}.csv 
  iii. Create new csv with three columns (id,day_of_week,utc_offset) (grab new  values from original ‘due_on’ value), csv name should be  customized_${most_frequent_day_due_day}.csv Note:All above task are expected to be done programmatically. 

Please do not forget to add  descriptive comments in your code
