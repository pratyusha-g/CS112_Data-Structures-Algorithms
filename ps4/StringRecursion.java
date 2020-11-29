package ps4;

public class StringRecursion {
	//iteration not allowed
	//you may use are charAt, length, equals, and substring.
	//do not use static variBles
	//no additional helper methods

	
	public static void printLetters(String str) {
		//print individual characters in string separated by commas
		//all characters, not just letters
		//even empty spaces should be accounted for
		
		//NO printing if empty string or null passes as parameter
		
		if (str.equals("") || str == null) {
			return;
		}
		else if (str.length() == 1) {
			System.out.print(str.charAt(str.length()-1));
			
		}
		
		else {
			System.out.print(str.charAt(0) + ", ");
			printLetters(str.substring(1));
		}

	}
	
	public static String weave(String str1, String str2) {
		//NO PRINTING, return the resulting string
		//if null is passed, method should throw IllegalArgumentException
		//if empty string is passed for either string, method should return other string
		String temp = "";

		if (str1 == null || str2 == null) {
			throw new IllegalArgumentException();
			
		}
		else if(str1.equals("") && !str2.equals("")) {
			return str2;
		}
		else if(str2.equals("") && !str1.equals("")) {
			return str1;
		}
		else if(str1.equals("") && str2.equals("")) {
			return str1;
		}
		//return string that is formed by weaving
		//join 2 strings to create 1 string
		//add 1 character alternately from each string!
		//if one string is longer, extra characters should appear after the woven string
		else {
			if (str1.length() == 1 && str2.length()==1) {
				temp = str1 + str2;
				
			}
			else if (str1.length() == 1 && str2.length() > 1) {
				temp = str1 + str2;
				
			}
			else if (str2.length() == 1 && str1.length() > 1) {
				temp = str1.charAt(0) + str2;
				
			}
			else {
				temp = "" + str1.charAt(0) + str2.charAt(0);
				
			}
			return temp + weave(str1.substring(1), str2.substring(1));
			
			
			
		}		
		
	}
	
	public static int indexOf(char ch, String str) {
		
		//find and return the index of the first occurrence of the character ch in the string str
		//or -1 if ch does not occur in str
		//return -1 if empty string or value null is passed as second parameter
		//do not use indexOf() in solution!!
		
		
		//why does the order of the null matter??
		if (str == null || str.equals("")) {
			return -1;
		}
		else if (ch == str.charAt(0)){
				return 0;
			}
		
		
		int temp = indexOf(ch, str.substring(1));
		if (temp == -1) {
			return -1;
		} else {
			return temp + 1;
		}

	}
	
	
	
	
	public static void main(String[] args) {
		printLetters("Rabbit");
		//printLetters("I like to recurse!");
		//weave("aaaa", "bbbb");
		//System.out.print(weave("hello", "world"));
		//System.out.print(weave("recurse", "NOW"));
		//System.out.print(weave("hello", ""));
		//weave("", "");
		//System.out.println(indexOf('b', "Rabbit"));
		//System.out.println( indexOf('P', "Rabbit") );
		//System.out.println( indexOf('P', "") );
		//System.out.println( indexOf('m', null ) );
	}	

}
