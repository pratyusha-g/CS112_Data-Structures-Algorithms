/*
 * Palindrome.java
 */
   
public class Palindrome {
    // Add your definition of isPal here.
	public static boolean isPal(String item) {
		
item = item.replaceAll("[^A-Za-z]+", "").toLowerCase();
        
        ArrayStack<Character> stack = new ArrayStack<Character> (item.length());
        
        for(int i = 0; i < item.length(); i++) {
                stack.push(item.charAt(i));
        }
        
      
      
        for(int i=0; i<item.length(); i++) {
                
                if(item.charAt(i) != stack.pop()) {
                        return false;
                }
        }
        
        return true;
}

	
	
	
	
	
    
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */

        System.out.println("(1) Testing on \"Do geese see God?\"");
        try {
            boolean results = isPal("Do geese see God?");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
       
        System.out.println("(2) Testing on \"A Santa Lived As a Devil At NASA\"");
        try {
            boolean results = isPal("A Santa Lived As a Devil At NASA");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("(3) Testing on \"Son, say a papaya.\" ");
        try {
            boolean results = isPal("Son, say a papaya.");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("(4) Testing on \"I am tired\" ");
        try {
            boolean results = isPal("I am tired");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        System.out.println("(5) Testing on \"No sir -- away! A papaya war is on.\" ");
        try {
            boolean results = isPal("No sir -- away! A papaya war is on.");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
    }
}