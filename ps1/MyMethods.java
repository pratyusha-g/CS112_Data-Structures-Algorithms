
public class MyMethods {

	//takes string s as input and prints substrings of decreasing length
	public static void printDecreasing(String s) {
		//for loop to remove one letter from the end of the string each time
		//start with full length, then reduce the number of characters in the string by 1
		//the total number of characters left in string must be greater than 0
		for (int i = s.length() ; i > 0; i--) {
			String a = s.substring(0,i);
			//not returning so print out the result
			System.out.println(a);
		}
	}
	
	//takes string s as input and returns a new string 
	public static String firstAndLast(String s) {
		//combine first and last characters of string
		//if only has 1 character, return original string
		String new_s = "";
		int index;
		char start_char;
		char final_char;
		if (s.length() == 1) {
			return s;
		}
		else {
			index = s.length();
			start_char = s.charAt(0);
			new_s += start_char;
			final_char = s.charAt(index - 1);
			new_s += final_char;
			return new_s;
			
		}
		
	}
	
	//takes string s and char ch as inputs
	//returns index of last occurrence of ch in s
	public static int lastIndexOf(String s, char ch) {
		//if ch does not appear in str at all, method should return -1
		//no printing, return integer!!
		int wanted_char=-1; 
		//initialize it as -1 so that if the character is not found
		//the loop will keep skipping till the end and give output of -1
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ch) {
				continue; 
			}
			else {
				wanted_char = i; 
			}
		}
		return wanted_char;
		}
	
	//takes a string s and int n as inputs
	//returns a new string consisting of n copies of s
	public static String repeat(String s, int n) {
		//returns, not print
		//n is positive
		String new_s = "";
		for (int i = 0; i < n; i++) {
			new_s += s;
		}
		return new_s;
		
	}
	
	
	public static void main(String[] args) { //adding main method to program, used to test
		String b = "method";
		printDecreasing(b);
		firstAndLast("Boston");
		firstAndLast("University");
		firstAndLast("a");
		lastIndexOf("Boston", 'o');
		lastIndexOf("banana", 'a');
		lastIndexOf("banana", 'b');
		lastIndexOf("banana", 'x');
		repeat("Java", 3);
		repeat("oh!", 7);
		
	}
	
}
