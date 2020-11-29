/*
 * Problem Set 1
 *
 * Practice with static methods, part I
 *
 * Note: Because this class is simply a collection of static methods 
 * and it does not have a main method, you cannot run it.
 * See the problem set for instructions about how to test the methods 
 * that you write.
 */

public class Methods {
    /*
     * printVertical - takes a string s and prints the characters of 
     * the string vertically -- with one character per line.
     */
		
	public static void printVertical(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
	        System.out.println(c);
		}
    }
	
	//takes a string s and prints the characters of the string in 1 line with space between each character
	public static void printWithSpaces(String s) {
		String c = ""; //declare string variable
		for (int i = 0; i < s.length(); i++) {
			//assigning the character and the space into string variable c
			c += s.charAt(i) + " ";
		}
		System.out.println(c);
    }
	
	//takes a string s and returns character at the middle
	public static char middleChar(String s) {
		 //dividing the length by 2
		//odd, then middle is at index e.g. length of 5/2 = 2
		//even then middle is at index e.g  length of 4/2 = 2 and at index 3, but we select first char
		int index;
		char c;
		if ( s.length() % 2 == 0) {
			index = s.length()/2;
			c = s.charAt(index - 1);
		}
		else {
			index = s.length()/2;
			c = s.charAt(index);		
					
		}
		return c;
	}
	
	//take string s and int i as inputs
	//returns new string after shifting first i characters to the end of string s
	//if string has i or fewer characters, return original string s
	public static String moveToEnd(String s, int i) {
		if (s.length() <= i) {
			return s;
		}
		else {
			//get the sequence of characters in the string that will not be moved
			String c = s.substring(i,s.length());
			for (int n = 0; n < i; n++) {
				//assigning the character and the space into string variable c
				c = c + s.charAt(n);
				}
			return c;
		}		
	}
	
	
	public static void main(String[] args) { //adding main method to program
		String a = "method"; //initializing string variable called a
		printVertical(a); //calling printVertical method to print characters one by one
		printWithSpaces(a); //calling printWithSpaces method to print characters with a space
		String b = "clock";
		String c = "Boston";
		middleChar(b); //calling middleChar method to get the middle character
		middleChar(c); //calling middleChar method to get the middle character
		moveToEnd("Boston", 4); //calling movetoEnd method to get desired string
		moveToEnd("Boston", 8); //calling movetoEnd method to get desired string
		moveToEnd("Terriers", 2); //calling movetoEnd method to get desired string
	}
}

