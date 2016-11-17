/*
   RegionHashTable class
      Uses TableFunctions.java ~~ in order to work
      
   The class reads from a file, "AreaCodes.txt"
      and sets the regions as the keys to insert into
      a HashTable.
      The key's value then is the State and DMA code
      
      Key: Portland-Auburn Value: ME,500
      
   The class also takes time from the first item read until the last item is read
      and inserted into the table.
      
   Collisions are also accounted for. When the region is the key
      3 collisions were found. 

   Lines to test the search and delete methods start at line 76, uses User input
      to search and delete specific keys.      
*/
import java.util.Scanner;
import java.io.*;

public class RegionHashTable
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      
      String filename = "AreaCodes.txt";
      
      File file = new File(filename);
      
      Scanner reader = new Scanner(file).useDelimiter("[,\n]");           

      TableFunctions regionTable = new TableFunctions();
      
      String key = "";
      
      String city = "";
      int region = 0;
      
      String state = "";
      
      String value  = "";
      
      long startTime = System.nanoTime();
      int collisions = 0;
      
      while(reader.hasNext())
      {
         key = reader.next();
         city = reader.next();
         state = reader.next();
         value = state + "," + key;
         value = value.replace("\n", "").replace("\r", ""); 
         
         region = regionTable.hashCode(city);
         
         if(regionTable.search(region))
         {
            collisions++;
         }
                 
         regionTable.insert(region, value);
      }
      
      long endTime = System.nanoTime();
      
      double duration = (endTime - startTime) / 1000000.0;
      System.out.println("After data is inserted time is: " + duration + " milliseconds");
      System.out.println("Number of collisions when using DMA codes as keys: " + collisions);
      
      System.out.println();
      
      System.out.println("~~Searching Method Test~~");
      
      System.out.print("Enter a region to look for in the table: ");
      String input = keyboard.nextLine();
      
      int hash = regionTable.hashCode(input);
      
      boolean found = false;
      
      if(found != regionTable.search(hash))  //1296007197 - key for first entry 500,Portland-Auburn,ME
      {
         System.out.println(input + " was found, its value is: " + regionTable.getValue(hash));
      }
      else
      {
         System.out.println(input + " was NOT found, therefore no value");
      }
      
      System.out.println();
      
      System.out.println("~~Delete Method Test~~"); 
      
      System.out.print("Enter a region to delete from the table: ");
      String delete = keyboard.nextLine();
      
      int deleteHash = regionTable.hashCode(delete);
      
      if(found != regionTable.search(deleteHash))
      {
         System.out.println(delete + " found...deleting...");
         regionTable.delete(deleteHash);
         System.out.println("Searching for deleted key");
         System.out.println("Is " + delete + " in the table?..." + regionTable.search(deleteHash)); 
      }
      else
      {
         System.out.println(delete + " NOT found in table....CANNOT delete...");
      }
   }
}