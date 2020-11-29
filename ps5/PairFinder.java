
public class PairFinder {
	//given array of n integers
	//find all pairs of values that sum to integer k
	//output all pairs it finds
	//e.g. if k = 12
	//array is 10,4,7,7,8,5,15
	//result should be
	/*
	4+8 = 12
	7+5 = 12
	7+5 = 12
	*/
	//repeats are included
	//order not important



	
	

	public static void findPairSums(int k, int[] arr) {
		//should have O(n^2) steps to solve problem
		
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] + arr[j] == k) {
					System.out.println( arr[i] + " + " + arr[j] + " = " + k);
				}
				
			}
		}

	}

	
	public static void findPairSumsFaster(int k, int[] arr) {
		//requires O(nlogn) steps in the average case to solve problem
		
		//begin by sorting the array using one of the methods from sort class
		//then only O(n) steps needed to find pairs
		
		Sort.quickSort(arr); //everything is sorted from smallest to largest
		
		int i = 0; //first index
		int j = arr.length - 1; //last index
		
				
		while (i < j) {
			
			if (arr[i] + arr[j] == k) {
				System.out.println( arr[i] + " + " + arr[j] + " = " + k);
				i++;
				j--;
			}
			else if (arr[i] + arr[j] < k) { //if the smallest + largest still is less than desired int, we need larger "smaller" int
				i++;
			}
			
			else { //arr[i] + arr[j] < k then we want to have a "smaller" larger integer
				j--;
			}
			
		}
		
	}
	

	public static void main(String[] args) {
		int[] a = {10, 4, 7, 7, 8, 5, 15};
		findPairSums(12, a);
		System.out.println("----------");
		findPairSumsFaster(12,a);
	}

}
