/*
 * Problem6.java
 * 
 * A method for determining whether the complete tree represented by an array 
 * is a heap.
 */ 

public class Problem6 {
    public static <T extends Comparable<T>> boolean isHeap(T[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        
        return isHeapTree(arr, 0);
    }
    
    private static <T extends Comparable<T>> boolean isHeapTree(T[] arr, int i) {
        //use recursion to process the complete tree/subtree whose root is at position i in arr
    	//return true if tree/subtree is a heap and false otherwise
    	//leaf node is the root of a heap with only one node
    	
    	//check if i is a leaf node
    	if (2*i + 2 > arr.length) {
    		return true;
    	}
    	
    	//if it is not, then do recursion
    	//compareTo --> need to be of same type, returns int value of negative if object is less than other object, =0 if equal and 1 if object value is greater than other object
    	
    	boolean left = arr[i].compareTo(arr[2*i+1]) >= 0 && isHeapTree(arr, 2*i+1);
    	boolean right = (2*i+2 == arr.length) || arr[i].compareTo(arr[2*i+2]) >= 0 && isHeapTree(arr, 2*i+2);
    	boolean answer = left && right;
    	return answer;   	
        
    }
    
    public static void main(String[] args) {
        System.out.println("--- testing isHeapTree() ---");
        System.out.println();
        System.out.println("(0) an array of integers that is a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            Integer[] arr1 = {50, 30, 35, 25, 23, 13, 18, 8, 20, 16};
            boolean results = isHeap(arr1);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
                           
        /*
         * Add at least two additional unit tests.
         * Follow the same format that we have used above.
         */
        System.out.println("(1) an array of strings that is a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            String[] arr1 = {"Indigo", "Hello", "Go", "Fun", "Elephant", "Dog", "Cat", "Boo", "Away"};
            boolean results = isHeap(arr1);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests
        
        System.out.println("(2) an array of integers that is a heap");
        try {
            // Note that we need to use the wrapper class (Integer)
            // instead of the primitive type (int).
            Integer[] arr1 = {12, 30, 35, 25, 23, 13, 2, 8, 20, 43};
            boolean results = isHeap(arr1);
            
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(false);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests

        
        
        
    }
}