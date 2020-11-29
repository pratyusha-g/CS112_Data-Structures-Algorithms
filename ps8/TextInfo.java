/*
 * TextInfo.java
 *
 * TextInfo is (1) a blueprint class for objects that summarize information
 * about a text file and (2) a program that can be run to allow clients 
 * to obtain information about a text file.
 * 
 * name:
 * BU email:
 */

import java.io.*;
import java.util.*;

public class TextInfo {
    // Add your fields here.
    
    /*
     * Creates a TextInfo object based on the text file with the specified name.
     */
    public TextInfo(String filename) throws FileNotFoundException {
        File file = new File(filename);
        long numBytes = file.length();       // number of bytes in the file
        int size = (int)(numBytes / 10);     // size for the hash tables
        
        // some code will probably be needed here!
        
        Scanner text = new Scanner(file);    // Scanner for reading from file
        while (text.hasNextLine()) {
            String line = text.nextLine();

            // code to process each line goes here
        }
        
        // some additional code may be needed here!
        
    }
    
    
    /*** Add your other methods here. ***
    
    
    /*
     * getChoice - prints a menu of choices and obtains and returns 
     * the number of the user's choice
     */
    public static int getChoice(Scanner console) {
        System.out.println("1) Find out where a word appears");
        System.out.println("2) Lookup a word's frequency");
        System.out.println("3) List the most common words");
        System.out.println("4) Quit");
        
        int choice;
        do {
            System.out.print("\nEnter your choice: ");
            choice = console.nextInt();
        } while (choice < 1 || choice > 4);
        
        console.nextLine();   // flush the Scanner's buffer
        return choice;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
       
        System.out.print("name of file: ");
        String filename = console.nextLine();
       
        // Add code to create the TextInfo object and print the number of words.
        
        while (true) {
            System.out.println();
            int choice = getChoice(console);
            
            if (choice == 1) {        
                System.out.print("word: ");
                String word = console.nextLine();

                // Add code to implement choice 1.
                
                
            } else if (choice == 2) {
                System.out.print("word: ");
                String word = console.nextLine();

                // Add code to implement choice 2.
                
            } else if (choice == 3) {
                System.out.print("how many words: ");
                int k = console.nextInt();
                console.nextLine();    // flush the Scanner's buffer    
                
                // Add code to implement choice 3.
                
            } else if (choice == 4) {
                break;
            }
        }
    }
}
