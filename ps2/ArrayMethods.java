import java.util.Arrays;

public class ArrayMethods {
	
	//static final array of strings
	//class level scope
	//constant variables in uppercase
	public static final String[] DAYS = 
		{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
	public static void swapAdjacent(int[] values) {
		//takes in array of intergers and swaps adjacent pairs of elements
		//0 with 1, 2 and 3, etc.
		//print it out
		//in odd length array, last element should not be moved!!
		
		//special case: null
		if (values == null) {
			throw new IllegalArgumentException();
		}
		//for even length array
		else if (values.length%2 == 0) {
			for (int i = 0; i < values.length; i += 2) {
				int temp = values[i] + values[i+1];
				values[i] = temp - values[i];
				values[i+1] = temp - values[i+1];
			}
		}	
		else {
		
			for (int i = 0; i < values.length - 1; i += 2) {
				int temp = values[i] + values[i+1];
				values[i] = temp - values[i];
				values[i+1] = temp - values[i+1];
			}
		}
	
	}
	
	public static int[] copyReplace(int[] values, int oldVal, int newVal) {
		//takes array of integers and 2 integers 
		//create and return a new array that is a copy of original array
		//BUT replace all occurrences of oldVal with newVal
		//no built in methods can be used
		//do not modify original array
		
		//special case: null
		if (values == null) {
			throw new IllegalArgumentException();
		}
		
		int [] new_array = new int[values.length];
		
		//copying of array into new array
		for (int i = 0; i < values.length; i++) {
			new_array[i] = values[i];
			
		}
		//now to replace oldVal with newVal
		for (int i = 0; i < new_array.length; i++) {
			if (new_array[i] == oldVal) {
				new_array[i] = newVal;
			}
		}
		return new_array;
	}
	
	public static int maxSorted(int[] values) {
		//takes array of integers and returns length of longest sorted sequence of integers
		//sorted sequence can contain duplicates
		
		//special cases
		if (values == null) {
			throw new IllegalArgumentException();
		}
		if (values.length == 0) {
			return 0;
		}
		
		//return 1 if passed an array with 1 element 
		//or an array in which all elements are in decreasing order
		//do not need to explicitly check for them
		//base case
		int len_longest_seq = 1;
		//keep count of each time a sequence is started 
		int count = 1;
		for (int i = 1; i < values.length; i++) {
			if (values[i] >= values[i-1] ) {
				count++;
			}
			else {
				count = 1;
			}
			//if the count is higher than the previous count, update the length of longest sequence!!
			if (count > len_longest_seq) {
				len_longest_seq = count;
			}
		}
		return len_longest_seq;
	}
	
	public static int getIndexOfDay(String day) {
		//takes reference to string and returns index of string in class array DAYS
		//if parameter is null or string not found, return -1
		//need to ensure that capital letters do not matter
		int index = -1;
		for (int i = 0; i < DAYS.length; i++) {
			if (DAYS[i].equalsIgnoreCase(day)) {
				index = i;
			}
			
		}
		return index;
	}
	
	public static int[] reverseInterchange(int[] arr1, int[] arr2) {
		//takes references to 2 integer arrays and returns reference to 3rd integer array
		//copy elements from 2 arrays passed to the array that will be returned 
		//but interchanged from forward and reverse direction
		
		//if length of arrays are different, interchange as many elements as smallest of 2 arrays
		
		//special case: null
		if (arr1 == null || arr2 == null) {
			throw new IllegalArgumentException();
		}
		
		//number of interchanges must be the length of smallest array of the 2 given
		//so total length of new array is 2x the number of interchanges
		final int new_len = Math.min(arr1.length, arr2.length) * 2;
		
		//copying arrays to new array of size new_len
		int[] new_array = new int[new_len];
		
		//reverse arr2!! to make life easy
		for(int i = 0; i < arr2.length / 2; i++){
		    int temp = arr2[i];
		    arr2[i] = arr2[arr2.length - i - 1];
		    arr2[arr2.length - i - 1] = temp;
		}
		
		//to finally get the elements alternatively added to the 3rd array
		int j=0, k=0;
		for (int i = 0; i < new_len; i++) {
			if (i%2==0) {
				new_array[i] = arr1[j++];
			} else {
				new_array[i] = arr2[k++];
		}
		}
		
		return new_array;
		
	}

	public static void main(String[] args) {
		int[] a1 = {0, 2, 4, 6, 8, 10};
		ArrayMethods.swapAdjacent(a1);
		System.out.println( Arrays.toString(a1) );
		int[] a2 = {1, 2, 3, 4, 5, 6, 7};
		ArrayMethods.swapAdjacent(a2);
		System.out.println( Arrays.toString(a2) );
		//int[] a3 = null;
		//ArrayMethods.swapAdjacent(a3);
		//System.out.println( Arrays.toString(a3) );
		int[] a4 = {2, 5, 4, 2, 7, 4, 2};
		int[] a5 = ArrayMethods.copyReplace(a4, 4, 0);
		System.out.println( Arrays.toString(a5) );
		int[] a6 = ArrayMethods.copyReplace(a4, 2, -1);
		System.out.println( Arrays.toString(a6) );
		System.out.println( Arrays.toString(a4) );
		int[] a7 = {3, 8, 6, 14, -3, 0, 14, 207, 98, 12};
		System.out.println( ArrayMethods.maxSorted(a7) );
		int[] a8 = {17, 42, 3, 5, 5, 5, 8, 4, 6, 1, 19};
		System.out.println( ArrayMethods.maxSorted(a8) );
		int[] a9 = {1};
		System.out.println(ArrayMethods.maxSorted(a9));
		int[] a10 = {3,2,1};
		System.out.println(ArrayMethods.maxSorted(a10));
		System.out.println( ArrayMethods.getIndexOfDay("Wednesday") );
		System.out.println( ArrayMethods.getIndexOfDay(null) );
		System.out.println( ArrayMethods.getIndexOfDay("hello") );
		System.out.println( ArrayMethods.getIndexOfDay("monday") );
		int b1[] = {1,2,3,4,5,6};
		int b2[] = {10,11,12,13};
		System.out.println(Arrays.toString(ArrayMethods.reverseInterchange(b1, b2)));
		
		
	}

}
