package com.niwatorichan.terminal;

/**
   An easy interface to read numbers and strings from 
   standard input

   @version 1.10 10 Mar 1997
   @author Cay Horstmann
*/

public class Console
{  /**
      print a prompt on the console but don't print a newline
      
      @param prompt the prompt string to display
    */

   public static void printLine(String prompt)
   {  System.out.print(prompt + "\n");
      System.out.flush();
   }
   
      public static void print(String prompt)
   {  System.out.print(prompt + "\n");
      System.out.flush();
   }
   
   /**
      read a string from the console. The string is 
      terminated by a newline

      @return the input string (without the newline)
    */
    
   public static String readLine()
   {  int ch;
      String r = "";
      boolean done = false;
      while (!done)
      {  try
         {  ch = System.in.read();
            if (ch < 0 || (char)ch == '\n')
               done = true;
            else if ((char)ch != '\r') // weird--it used to do \r\n translation
               r = r + (char) ch;
         }
         catch(java.io.IOException e)
         {  done = true;
         }
      }
      return r;
   }

   /**
      read a string from the console. The string is 
      terminated by a newline

      @param prompt the prompt string to display
      @return the input string (without the newline)
    */
    
   public static String readLine(String prompt)
   {  print(prompt);
      return readLine();
   }

   /**
      read an integer from the console. The input is 
      terminated by a newline

      @param prompt the prompt string to display
      @return the input value as an int
      @exception NumberFormatException if bad input
    */
    
   public static int readInt(String prompt)
   {  while(true)
      {  print(prompt);
         try
         {  return Integer.valueOf
               (readLine().trim()).intValue();
         } catch(NumberFormatException e)
         {  System.out.println
               ("Not an integer. Please try again!");
         }
      }
   }

   /**
      read a floating point number from the console. 
      The input is terminated by a newline

      @param prompt the prompt string to display
      @return the input value as a double
      @exception NumberFormatException if bad input
    */
    
   public static double readDouble(String prompt)
   {  while(true)
      {  print(prompt);
         try
         {  return Double.parseDouble(readLine().trim());
         } catch(NumberFormatException e)
         {  System.out.println
         ("Not a floating point number. Please try again!");
         }
      }
   }
}
