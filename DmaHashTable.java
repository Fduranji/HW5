/*
   DmaHashTable class PART 3 OF HOMEWORK
      Uses TableFunctions.java ~~ in order to work
      
   The class reads from a file, "AreaCodes.txt"
      and sets the DMA codes as keys to insert into
      a HashTable.
      The key's value then is the Region and State
      
      Key: 500 Value: Portland-Auburn,ME
      
   The class also takes time from the first item read until the last item is read
      and inserted into the table.
      
   Collisions are also accounted for, however for the DMA codes
      no collions are found. Which is to be expected.
   
   Lines to test the search and delete methods start at line 71, uses User input
      to search and delete specific keys.
*/
import java.util.Scanner;
import java.io.*;

public class DmaHashTable
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      
      String filename = "AreaCodes.txt";
      
      File file = new File(filename);
      
      Scanner reader = new Scanner(file).useDelimiter("[,\n]");           

      TableFunctions hashTable = new TableFunctions();
      
      int key = 0;
      
      String city = "";
      String state = "";
      
      String value  = "";
      
      long startTime = System.nanoTime();
      int collisions = 0;
      
      while(reader.hasNext())
      {
         key = reader.nextInt();
         city = reader.next();
         state = reader.next();
         value = city + "," + state;
         value = value.replace("\n", "").replace("\r", ""); 
         
         if(hashTable.search(key))
         {
            collisions++;
         }
                 
         hashTable.insert(key, value);
      }
      
      long endTime = System.nanoTime();
      
      double duration = (endTime - startTime) / 1000000.0;
      System.out.println("After data is inserted time is: " + duration + " milliseconds");
      System.out.println("Number of collisions when using DMA codes as keys: " + collisions);
      
      System.out.println();
      
      System.out.println("~~Searching Method Test~~");      
      
      System.out.print("Enter a numerical key to look for in the table: ");
      int input = keyboard.nextInt();
      
      boolean found = false;
      
      if(found != hashTable.search(input))
      {
         System.out.println(input + " was found, its value is: " + hashTable.getValue(input));
      }
      else
      {
         System.out.println(input + " was NOT found, therefore no value");
      } 
      
      System.out.println();
      
      System.out.println("~~Delete Method Test~~");
      
      System.out.print("Enter a numerical key to delete from the table: ");
      int delete = keyboard.nextInt();
      
      if(found != hashTable.search(delete))
      {
         System.out.println(delete + " found...deleting...");
         hashTable.delete(delete);
         System.out.println("Searching for deleted key");
         System.out.println("Is " + delete + " in the table?..." + hashTable.search(delete)); 
      }
      else
      {
         System.out.println(delete + " NOT found in table....CANNOT delete...");
      }
   }
}
