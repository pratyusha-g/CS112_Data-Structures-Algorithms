package ps3;

import java.util.Arrays;

/* File: BigInt.java
 *
 * Author:  CS112 Instructor
 *          Code adapted from Wayne Snyder
 *
 * Purpose: Skeleton Code for the BigInt class
 */


public class BigInt  {

    private static final int SIZE = 20;      // length of arrays representing big ints
    private static final int[] NaBI = { -1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };  // error value
    private int[] digits;    // The value unique to each BigInt
    private int sigDigits;   // the number of significant digits for each BigInt
	private int n;
	private String s;
	private int[] arr;


/*
 * CONSTRUCTORS
 */

    // No-arg Constructor
    public BigInt() {
        // allocate the array of integers that
        // will be used to represent our big integer
        digits = new int[SIZE];
        // This object does not represent a number 
        // at this point so we have no significant 
        // digits.
        sigDigits = 0;
    }

    // Validates and initializes a BigInt object
    // based on the integer passed to the constructor.
    
    //n wont overflow array since it is an int
    public BigInt(int n){ 
      this();
    //if n<0 then object initialized as NaBI
      if (n<0) {
    	  digits = NaBI;
      }
      else {
    	  
    	  
    	  
    	  String temp = Integer.toString(n);
    	  
    	 
    	  //use number of digits in integer to initialize starting index in array
    	  int count = SIZE - temp.length();
    	  //make integer into array of digits
    	  int[] newArray = new int[temp.length()];
    	  for (int i = 0; i < temp.length(); i++) {
    		  newArray[i] = temp.charAt(i) - '0';
    	  }
   	  
    	  
    	  //add the digits into the "boxes" within array
    	  for (int i = 0; i < temp.length(); i += 1) {
    		  digits[count] = newArray[i];
    		  count+=1;
    	  }
    	  //update number of significant digits
    	  sigDigits = temp.length();
      }
    }
    // Validates and initializes a BigInt object based on the
    // string passed to the constructor.
    //convert string s to object
    public BigInt(String s){
      this();
      boolean temp;
      int[] chars;
    //if s does not rep a legal integer or is longer than SIZE characters then object is NaBI
      temp = OnlyDigits(s);
      int count = SIZE - s.length();
      if (temp && s.length() <= SIZE) {
    	  //create array of digits
    	  chars = new int[s.length()];
    	  for (int i = 0; i < s.length(); i++  ) {
    		  chars[i] = s.charAt(i) - '0';
    	  }
    	  digits = chars;
    	  sigDigits = chars.length;
      
      }  
      else {
    	  digits = NaBI;
      }
    }  
    	  
    // Validates and initializes a BigInt object based on the
    // Java array of integers passed to the constructor.
    public BigInt( int[] arr ){

    this();
    if(arr.length > 20) {
        digits = NaBI;
    } else {

        for (int i = 0; i < arr.length; ++i) {
            digits[SIZE - arr.length - i] = arr[i];

            if(arr[i] > 9 || arr[i] < 0) {
                digits[0] = -1;
                break;
            }
        }
    }
}
/*
      boolean temp1;
      boolean temp2;
      //array can be passed of any size BUT BigInt can only contain up to SIZE digits 
      //to be valid rep of bigint, 
      String[] newArray = new String[arr.length];
      for (int i = 0; i < arr.length; i++) 
    	  newArray[i] = String.valueOf(arr[i]);
      
      temp1 = true;
      for (int i = 0; i < arr.length; i++) {
  		try { 
  		     Integer.parseInt(newArray[i]); 
  		   } catch(NumberFormatException e) { 
  		      temp1 = false; 
  		   }    
  	}  
      temp2 = true;
      for (int i = 0; i < newArray.length; i++) {
    	  if (newArray[i].length() != 1) {
    		  temp2 = false;
    	  }  
       }
      if (temp1 != true || temp2 != true)  {
    	  digits = NaBI;
      }
      else {
    	  if (arr.length > SIZE) {
    		  int[] arr_subset = Arrays.copyOfRange(arr, 0, SIZE);
    		  digits = arr_subset;
    		  sigDigits = arr_subset.length;
    	  }
    	  else {
    		  digits = arr;
    		  
    		  sigDigits = arr.length;
    	  }
      }
      
      } 

/*
    REQUIRED INSTANCE METHODS

    THIS SECTION IS COMMENTED OUT SO THAT
    THIS SOURCE FILE CAN COMPILE. UNCOMMENT
    EACH METHOD AS YOU WORK IN IT.

    THE METHODS ARE LISTED IN SUGGESTED ORDER
    OF IMPLEMENTATION AS EACH METHOD
    INCREASES IN DIFFICULTY.
 */


    public String toString() {
      String result = "";
      if (isNaBI() == true) {
    	  return ("NaBI");
      }
      else {

    	  
    	  for (int i: this.digits) {
    		  result += "" + i + "";
    	  }
    	  
    	  result = result.replaceAll("^0+(?=.)", "");
 
    	  return result;
    	 
      }
      }	

    
    // compareTo(BigInt other): return -1, 0, or 1, depending
    // on whether A < B, A == B, or A > B, respectively.
    public int compareTo(BigInt other) {
    	String temp1 = "";
    	
    	
    	for (int i: this.digits) {
    		temp1 += "" + i + "";
    	}
    	int int1 = Integer.parseInt(temp1);
    	
    	String temp2 = "";
    	for (int i: other.digits) {
    		temp2 += "" + i + "";
    		
    	}
    	int int2 = Integer.parseInt(temp2);
    	
    	int result = 0;
    	if (int1 < int2 ) {
    		result = -1;
    	}
    	else if (int1 == int2) {
    		result = 0;
    	}
    	else if (int1 > int2 ){
    		result = 1;
    		
    	}
		return result;
    }


    // Add two big integers and return a new BigInt object 
    // representing their sum.
    //
    // if the result overflows, i.e., contains more than SIZE digits, 
    // return NaBI object.
    /*
    public BigInt add(BigInt other) {
		
    	 BigInt sum = new BigInt(0);

         int temp2 = 0;
         
         for (int i = SIZE-1; i >= 0; --i) {
        	 int a = this.sigDigits[i];
             int r = this.digits[i] + other.digits[i] + temp2;
             if(r > 9) {
                 temp2 = 1;
                 r = r - 10;
             } else {
                 temp2 = 0;
             }

             sum.digits[i] = r;
         }
         
         if(temp2 == 1) {
             sum.digits = NaBI;
         }

         return sum;

    	 
    	/*
    	String temp1 = "";
    	
    	
    	for (int i: this.digits) {
    		temp1 = temp1 + "" + i + "";
    	}
    	int int1 = Integer.parseInt(temp1);
    	
    	System.out.println(int1);
    	
    	
    	String temp2 = "";
    	for (int i: other.digits) {
    		temp2 = temp2 +  "" + i + "";
    		
    	}
    	int int2 = Integer.parseInt(temp2);
      
    	int sum = int1 + int2;
    	
    	String str_sum = Integer.toString(sum);
    	
    	if (str_sum.length() > SIZE) {
    		BigInt newBI = new BigInt(NaBI);
    		return newBI;
    	} else {
    		BigInt newBI = new BigInt(sum);
    		return newBI;
    	
    	}
    	
    	*/
    //}

    
    
    /*
    // Multiply two big integers and return a new BigInt object
    // representing their sum.
    //
    // if the result overflows, i.e., contains more than SIZE digits,
    // return NaBI object.
    //                  
    public BigInt mul(BigInt other) {
      
      // YOUR CODE HERE
    }
*/

/*
 * Private class methods, if any
 */

   //check if BigInt object represents the number zero
  
    /*
    private boolean isZero() {
    	
    }
    
    
    */
    
    
   //check if BigInt object represents NaBI
    private boolean isNaBI() {
    	if (this.digits == NaBI) {
    		return true;
    	} else {
    		return false;
    	}
    }
      
    //check if string object contains only digits from 0 to 9
    private static boolean OnlyDigits(String chars) {
    	String[] newArray = chars.split("(?!^)");
    	//check if all values are integers
    	//if any is not, return false
    	for (String ch: newArray) {
    		try { 
    		     Integer.parseInt(ch); 
    		   } catch(NumberFormatException e) { 
    		      return false; 
    		   }    
    	}    	
        return true;
    }
    
    
    
    
/*
 * Unit Test: Following is a local main method where we put some sample tests
 * you can use to verify that this class works properly; This code should only
 * be used except for debugging and testing purposes.
 */

    public static void main(String [] args) {

        //System.out.println("\nUnit Test for BigInt Library.\n");

        // Note that we may call the methods without the prefix BigInt.
        // because we are testing from inside the class; if you 
        // write a separate test program and call these
        // methods from outside the class you must use the prefix, e.g., 
        // BigInt.bigIntToString(A).
        //
        // I suggest you uncomment and run each test suite - one at a time -
        // as you build each method of your class.
        //

  /*      
        
        System.out.println("Test 1: Should be\n1234567");
        int[] A = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt B1 = new BigInt(A);
        System.out.println(B1);
        System.out.println();
        
        System.out.println("Test 2: Should be\n0");
        int[] B = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt B2 = new BigInt(B);
        System.out.println(B2);
        System.out.println();

        System.out.println("Test 3: Should be\nNaBI");
        int[] C = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt B3 = new BigInt(C);
        System.out.println(B3);
        System.out.println();
        
        System.out.println("Test 4: Should be\n1234567");
        BigInt B1 = new BigInt(123456789);
        System.out.println(B1);
        System.out.println();
/*
        System.out.println("Test 5: Should be \n0");
        B2 = new BigInt(0);
        System.out.println(B2);
        System.out.println();

        System.out.println("Test 6: Should be\nNaBI");
        B3 = new BigInt( -4 );
        System.out.println(B3);
        System.out.println();

        System.out.println("Test 7: Should be\n3141592");
        BigInt B1 = new BigInt("3141592");
        BigInt B3 = new BigInt("878512352645");
        System.out.println(B1);
        System.out.println(B3);
        System.out.println();
/*
        System.out.println("Test 8: Should be\n0");
        B2 = new BigInt("0");
        System.out.println(B2);
        System.out.println();

        System.out.println("Test 9: Should be \nNaBI");
        B3 = new BigInt("234h56");
        System.out.println(B3);
        System.out.println();

        System.out.println("Test 10: Should be\nNaBI");
        B3 = new BigInt("23456379238472937829384272939472937429374");
        System.out.println(B3);
        System.out.println();
       

        System.out.println("Test 11: Should be\n0");
        BigInt B1 = new BigInt("12375");
        BigInt B2 = new BigInt("12375");
        System.out.println(B1.compareTo(B2));
        System.out.println();

       
        System.out.println("Test 12: Should be\n-1");
        B2 = new BigInt("12378");
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 13: Should be\n1");
        System.out.println(B2.compareTo(B1));
        System.out.println();

        System.out.println("Test 14: Should be\n0");
        B1 = new BigInt(0);
        B2 = new BigInt(0);
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 15: Should be\n-1");
        B2 = new BigInt("12375");
        System.out.println(B1.compareTo(B2));
        System.out.println();

        System.out.println("Test 16: Should be\n1");
        System.out.println(B2.compareTo(B1));
        System.out.println();
*/
/*
        System.out.println("Test 17: Should be\n123456789123456789");
        BigInt B1 = new BigInt("36182736036182736");
        BigInt B2 = new BigInt("87274053087274053");
        System.out.println(B1);
        System.out.println(B1.add(B2));
        System.out.println();

       /* 
        System.out.println("Test 18: Should be\n123456789123456789");
        System.out.println(B2.add(B1));
        System.out.println();

/*
        System.out.println("Test 19: Should be\n3141592653598");
        B1 = new BigInt(0);
        B2 = new BigInt("3141592653598");
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 20: Should be\n10000000000000000000");
        B1 = new BigInt("9999999999999999999");
        B2 = new BigInt(1);
        System.out.println(B1.add(B2));
        System.out.println();

        System.out.println("Test 21: Should be\nNaBI");
        B1 = new BigInt("99999999999999999999");
        System.out.println(B1.add(B2));
        System.out.println();
/*
        System.out.println("Test 22: Should be\n5670");
        B1 = new BigInt("135");
        B2 = new BigInt("42");
        System.out.println(B1.mul(B2));
        System.out.println();

        System.out.println("Test 23: Should be \n99999999999999999999");
        B1 = new BigInt("99999999999999999999");
        B2 = new BigInt(1);
        System.out.println(B1.mul(B2));
        System.out.println();

        System.out.println("Test 24: Should be \nNaBI");
        B2 = B2.add(B2);
        System.out.println(B1.mul(B2));
        System.out.println();
        
		int[] b = {5, 2, 1, 9, 5, 8, 3};
        BigInt a = new BigInt(b);
        System.out.println(a); 
        */
    } // main
} // class
