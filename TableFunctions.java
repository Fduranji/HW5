/**
   TableFunctions class, implements the Hashtable methods from Java's 
      Hash Table Class.
      
   This class is used by DmaHashTable.java and RegionHashTable.java.
   Must be in the same folder for all 3 programs to work. 
*/
import java.util.Hashtable;

class TableFunctions
{
   private Hashtable<Integer, String> table = new Hashtable<>();
   
   //Empty Constructor
   TableFunctions() {}
   
   /*
      Hash algorithm used for string values
   */
   public int hashCode(String key)
   {
      int hash = 7; 
      
      for(int i = 0; i < key.length(); i++)
      {
         hash = hash * 31 + key.charAt(i);
      }
      
      return hash;  
   }
   
   public void insert(int key, String value) 
   {
      table.put(key, value);
   }
   
   public void delete(int key)
   {
      table.remove(key);
   }
   
   public boolean search(int key)
   {
      return table.containsKey(key);  
   }
   
   public String getValue(int key)
   {
      String value = "";
      value = table.get(key);
      return value;
   }
}