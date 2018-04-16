# From-CSV-to-SQL
  This program is intend to be usefull when you need to get the data from the CSV file where the data is represented like rows with comma delimetrs and write it into the MySql table with receiving test as a result of proper transaction. So, for successful work you would need:
  1. Create one MySql table for writig the data into it and the text file with csv-style data representation for reading information from it ("\src\DataTableTest\fileCVS.cvs").
  2. Create Annotated POJO java class  (like "Item.java", "Item_test.java" from project).
  3. Change configuration of the "\src\resources\hibernate.cfg.xml" accordingly your purpose.
  4. Write the full name of the your object class in the file "MainApp.java".
  5. For getting sure that your data if represented well repeat 4 tasks above with another names of table and test.
  6. ;)
