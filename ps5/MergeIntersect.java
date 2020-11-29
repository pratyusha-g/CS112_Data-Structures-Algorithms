import java.util.Arrays;

public class MergeIntersect {

	//implement static method called intersect
	//take 2 arrays of integers as parameters and use approach based on merging to find and return intersection of 2 arrays
	
	public static int[] intersect (int[] a, int[] b) {
		
		//begin by creating new array for intersection
		//new array should be the length of the shortest array
		int[] temp;
		
		if (a.length < b.length) {
			temp = new int[a.length];
		}
		else {
			temp = new int[b.length];
		}
		
		//use efficient sorting algorithm to sort both arrays
		//quicksort
		
		Sort.quickSort(a);
		Sort.quickSort(b);
		
		//now each array has been sorted, we need to find out the common elements between a and b
		
		//use approach similar to merge method
		
		//how do you account for repeats???
		int a_index = 0;
		int b_index = 0;
		int temp_index = 0; //keep track of the index within temp array
		int i = a[0]; //initialize i
		int j = b[0]; //initialize j
		
		while (a_index< a.length && b_index < b.length) {
			
			 i = a[a_index]; //integer of array a
			 j = b[b_index]; //integer of array b
			
			if (i == j /*&& !contains_int(temp, i)*/) { //if the 2 integers are the same, and integer not already in temp
				temp[temp_index] = i; //add integer into temp array
				temp_index += 1; //increment the index of temp
				a_index += 1;
				b_index += 1;
			}
			/*
			else if(i == j && contains_int(temp, i)) { //if the integer is already in temp then we will not add it in again
				a_index += 1;
				b_index += 1;
			}
			*/
			//because each array is sorted
			else if(i>j) { 
				b_index += 1;
			}
			
			else if(j>i) {
				a_index +=1;
			}
			
		
		}
		
		return temp;
	
	}
	
	/*
	//check if integer is in the array
	//returns true if integer is in the array
	public static boolean contains_int(int[] arr, int j) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]==j) {
				return true;
			}
		}
		return false;
	}
*/
	
	public static void main(String[] args) {
		int[] a1 = {10, 5, 7, 5, 9, 4};
		int[] a2 = {7, 5, 15, 7, 7, 9, 10};
		int[] result = intersect(a1, a2);
		System.out.println(Arrays.toString(result));
		int[] a3 = {0, 2, -4, 6, 10, 8};
		int[] a4 = {12, 0, -4, 8};
		int[] result2 = MergeIntersect.intersect(a3, a4);
		System.out.println(Arrays.toString(result2));

	}

}
