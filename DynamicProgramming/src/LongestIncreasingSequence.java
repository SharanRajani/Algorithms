import java.util.*;

//The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence
//of a given sequence such that all elements of the subsequence are sorted in increasing order. 
//For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.

public class LongestIncreasingSequence {

	static Scanner scan = new Scanner(System.in);

	// tabulation method
	static void lis(int[] arr) {
		int[] lis = new int[arr.length];
		for (int i = 0; i < lis.length; i++) {// lis[i] gives max length of LIS ending at ith element
			lis[i] = 1;
		}
		for (int i = 1; i < lis.length; i++) {
			for (int j = 0; j < i; j++) {// for every ith element, we check for all j<i whether i forms the last element
											// of the LIS containing j and checking if its length is greater than
											// previous max LIS length ending at i
				if ((arr[i] > arr[j]) && (lis[i] < lis[j] + 1)) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		int max = 1;
		for (int i = 1; i < lis.length; i++) {
			if (max < lis[i])
				max = lis[i];
		}
		System.out.println("The length of the Longest Increasing Subsequence is:" + max);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of elements:");
		int count = scan.nextInt();
		int[] arr = new int[count];
		System.out.println("Enter the values of each of the elements:");
		for (int i = 0; i < count; i++) {
			arr[i] = scan.nextInt();
		}
		lis(arr);
	}
}
