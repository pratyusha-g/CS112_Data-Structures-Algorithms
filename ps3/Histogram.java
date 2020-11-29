package ps3;
/* File: Histogram.java

 * Author: CS112 Instructor
 * 
 * Purpose: This is a potential solution to the
 * Histogram problem.
 */


import java.util.ArrayList;
import java.util.Arrays;
//produce and display a histogram of floating point values
	//histogram is a graphical description depicting freq	uency in which numbers occur within specific ranges
	//e.g. [0..10], (10,20] etc
	//where [a..b] denotes a set of numbers such than a<=x and x<=b
	//(a..b] denotes a set of numbers such that a<x<=b
import java.util.Scanner;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal end of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as a data
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]

    private static final int BIN_SIZE = 10;           // size of each bin
   
    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
        me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles in the range " + '[' + (int) LOWER_BOUND + ".." + (int) UPPER_BOUND + "]; " + "enter " + SENTINAL + " to end." );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	return (name+" ").split(" ")[0]; 
    }
    

    /* 
     * local main test driver
     *
     */
    public static void main(String[] args) { 
    	/*
    	double[] a = {2.2,5,67};
    	calculateHistogram(a);
   */
    	
	// Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	String heading = getHeaderAsString( user );

        // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );
        

	// Call the method to create the array histogram
	int[] histogram = calculateHistogram( numbers );
	

	// Print the histogram
	System.out.println( toString( histogram ) );
 


    }
    public static int[] calculateHistogram(double[] numbers) {
    	//numbers stored in an array of doubles
    	//array should be large enough to store maximum possible inputs
    	//array can be explicitly populated to its/ max size
    	//but program must provide a method which populates the array through user input
    	//histogram stored as array (what data type should this be)
    	
    	// determine appropriate bin of the histogram to update by using loop
    	//do NOT explicitly account for all possibilities
    	final int[] result = new int[NUM_BINS];
    	//BIN_SIZE = 10
    	
    	//sort the numbers into different bins
    	
    	for (double n: numbers) {
    		int count = 0;
    		for (double i = LOWER_BOUND; i <= UPPER_BOUND; i = i + BIN_SIZE ) {
    			//only the first bin has [] instead of (]
    			if (i != 0){
    				if (n >= i && n <= i + BIN_SIZE) {
    					result[count]+=1;
    					break;
    					
    				}
    				else {
    					//update count to go to the next bin
    					count+=1;
    				}
    			}
    			else {
    				if (n > i && n <= i + BIN_SIZE) {
    					result[count]+=1;
    					break;
    				}
    				else {
    					count+=1;
    					
    			}  			
    			}
    		}
    		}
    	
    		return result;
    	
    }
    
    
    public static String toString( int[] histogram) {
    	//construct and return a string rep of histogram
    	
    	//use loop that iterates over histogram array to form string rep of it
    	
    	//create instance of string builder class to assist me
    	//follow code in getHeaderAsString
    	
    	StringBuilder hist=new StringBuilder();

        	int count = 0;
        	for (int i = 0; i < histogram.length; i +=1) {
        		if (i == 0) {
        			hist.append("[" + count + ".." + (count+BIN_SIZE) + "]: ");
        		}
        		else {
        			hist.append("(" + count + ".." + (count+BIN_SIZE) + "]: ");
        		}
        		
        		
        		String original = "";
        		 char c = '*';
        		 int num = histogram[i];
        		 char[] repeat = new char[num];
        		 Arrays.fill(repeat, c);
        		 original += new String(repeat);
        		hist.append(original);
        		hist.append("\n");
        		count += BIN_SIZE; 		
        	}
        	return hist.toString();
        }
    
    public static boolean validInput(double num) {
    	
    	//determine if integer passed to method is in legal range of valid inputs as specified by static variables of class
    	//within lower and upper bounds
    	
    	if (num <= UPPER_BOUND && num >= LOWER_BOUND) {
    		return true;
    	}
    	else return false;
    }
    
    public static double[] inputNumbers(Scanner scan) {
    	//perform user input
    	//accept object of scanner class, uses object to perform user input by calling approperiate methods
    	//return array of floating point values that were input
    	
    	//guidelines:
    	
    	//user may input max amount of numbers: MAX_NUMBERS
    	//each number entered must be within valid bounds --> use validInput
    	//do error checking and report accordingly
    	//any input number outside range is an input error, and program should report error and ask for correct input
    	//determine how to keep track of no. keyed in to make sure max not exceeded
    	//dont force user to key in max, use sentinal to signal end of input
    	
    	
    	
	    // Continue to receive user input until some
	    // sentinal (i.e. final) value is entered.
    	int input_count = 0;
    	ArrayList list = new ArrayList();
	    while (scan.hasNextDouble()) {
	    	//read double number input
	    	double number = scan.nextDouble();
	        
	    	//error checking and reporting
	    	if (number != -999 && validInput(number) != true) {
	    		System.out.println("Input Error: Each number entered must be between the valid bounds");
	    		System.out.println("Please give a correct input.");
	    		number = scan.nextDouble();
	    	}
	    	
	        if (number == SENTINAL || input_count > MAX_NUMBERS) {
	          break;
	        
	    	} else {
	          //keep count of how many times number is keyed in
	    	  list.add(number);
	          input_count += 1;
	    	}
	    }
   
	    scan.close();
	    System.out.println("You input " + input_count + " numbers: " + list);
	    System.out.println("Histogram of Values in Decades from 0 to 100:");
	    System.out.println("\n");
	    
	    
	    //array returned will be passed to calculatehistogram
	    double[] array = new double[list.size()];
	    for (int i=0; i<array.length;i++) {
	    	array[i] = (double) list.get(i);
	    }
	    
	    
	    return array;
	    
	   
    	
	  
	    }
}// end of class
