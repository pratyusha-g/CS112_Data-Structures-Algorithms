/*
 * Problem Set 1
 *
 * File: Statistics.java
 * Author: Pratyusha Ghosh
 * Course: CS112, Boston University
 * Section: 
 *
 * Purpose: Template code - Alter to state the purpose of this program
 */

// The following import command is required for performing user input:
// The Scanner class is a library which provides functionality for
// reading input from the user in the Interactions Pane. Some
// libraries (such as Math) are already inported, but most (such
// as Scanner) you need to explicitly import. The import statement
// must occur before your class definition. 

import java.util.Scanner;
import java.lang.Math;

public class Statistics { 
    
    private static Scanner input;

	/* 
     * main method of the Statistics program which will
     * be used as the main entry point of our program and 
     * to test the methods of the class.
     */
    public static void main(String[] args) {

    	input = new Scanner (System.in);
    	int num1 = 0, num2 = 0, num3 = 0;
    	
    	//get user to key in 3 numbers 
    	System.out.println("Enter three numbers: ");
    	   num1 = input.nextInt();
    	   num2 = input.nextInt();
    	   num3 = input.nextInt();
    	//show user which numbers they keyed in
    	   System.out.println("The numbers entered are: " );
    	   System.out.println( num1 + " " + num2 + " " + num3 );
    	 //implement statistics program using 3 numbers given by user
    	display_statistics(num1, num2, num3);
    }
    
    /*
     * display_statistics()
     *
     * Static method of this class which will be used to calculate and
     * display the required statistics.
     *
     * This method accepts three integer arguments as input values and uses
     * the input values to compute the statistics. This method is a void 
     * method and does not return any value.
     */ 
    
    public static void display_statistics( double num1, double num2, double num3 ) {
       // sum of 3 numbers
    	int sum_num = (int) (num1 + num2 + num3);
    	System.out.println(sum_num);
    	
    	// max of 3 numbers
    	int max_num = (int) Math.max(Math.max(num1, num2), num3);
    	System.out.println(max_num);
    	
    	//range between max number and min number
    	//find minimum number first
    	int min_num = (int) Math.min(Math.min(num1, num2), num3);
    	int range = max_num - min_num;
    	System.out.println(range);
    	
    	//average of 3 numbers
    	//careful of types--> use double
    	double average_num = (sum_num/3.0000);
    	//4 digit precision and linefeed needed
    	System.out.printf("%1.4f%n", average_num);
    	
    	//population standard deviation of 3 numbers
    	//formula: sqrt ( sum of squared deviations from population mean) / N
    	double variance = (Math.pow((num1 - average_num), 2) + Math.pow((num2 - average_num), 2) + Math.pow((num3 - average_num), 2))/3;
    	double stdev = Math.sqrt(variance);
    	//4 digit precision and linefeed needed
    	System.out.printf("%1.4f%n" , stdev);
    	
    	//print numbers in ascending number: min, median, max
    	int median_num = (int) (sum_num - max_num - min_num);
    	System.out.println( min_num + " " + median_num + " " + max_num + " ");
    	
    }
}
