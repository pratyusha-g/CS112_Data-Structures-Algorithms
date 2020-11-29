import java.util.Scanner;

public class PalindromeTest {
	
	public static boolean isPalindrome(String s) {
		boolean isPal = false;
		/*
		 *determine if string s is a palindrome
		 *default is that it is NOT
		 *so, need to determine if s is a palindrome then assign true if it is or keep it the same
		 *palindrome = word/sequence reads the same backwards and forwards
		 *need to check each character front and back to see if the same, and same order
		 */
		
		//but first, convert to lowercase!
		s = s.toLowerCase();
		
		//next, remove character which is not letter or digit
		
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		
		//now check palindrome using mirror image analysis!!
		int n = s.length();
		int count = 0;
		//(n/2) used here cos first half read forwards and second half read backwards should be same
		for (int i=0; i<(n/2); ++i) {
			if (s.charAt(i) == s.charAt(n - i - 1)) {
				count += 1;
			}
			//the characters and order must be right for each half (one read forwards the other backwards)
			if (count == (n/2)) {
				isPal = true;
			}
		}
		
		return(isPal);
	}
	
	public static int[] inputStringPalindrome() {
		/*
		 * prompt user to enter string
		 * invoke ispalindrome method
		 * output: whether string is/is not palindrome
		 * run continuously until user signals done
		 * then print goodbye and return array
		 * array should contain data on how many time ispalindrome was called
		 * and how many string inputs were palindromes
		 * 1st element: no. of times method was called
		 * 2nd element: how many of inputs, if any, were palindromes
		 */
		
		//print welcome message
		
		System.out.println("\nWelcome to the Palindrome Test Program!");
	    System.out.println("This demonstrates how to input strings from the console.");
	    
	 // Declare a scanner object for user input

	    Scanner userInput = new Scanner(System.in);

	    System.out.println("\nType in a line of text (a String) or \"quit\" to end:");

	    // Continue to receive user input until some
	    // sentinal (i.e. final) value is entered.
	    // In this case, the user must enter the
	    // word "quit".
	    int call_count = 0;
		int pal_count = 0;
	    while (userInput.hasNextLine()) {
	        String line = userInput.nextLine();
	        if (line.equals("quit")) {
	          break;
	        } else {
	          //keep count of how many times ispalindrome was called
	          call_count += 1;
	          System.out.println("You input: " + line);
	          //find out if string is palindrome
	          boolean result = isPalindrome(line);
	          System.out.println(result);
	          System.out.println(call_count);
	          //keep count of how many inputs were palindromes
	          if (isPalindrome(line) == true) {
	        	  pal_count +=1;
	          }
	          System.out.println(pal_count);
	        } // while
	      
	    //userInput.close();
	    } // inputString()
		System.out.println("goodbye!");
		//return array
		int[] quit_message = {call_count, pal_count};
		return quit_message;
	}
	
	public static void main(String[] args) {
		inputStringPalindrome();

	}

}
