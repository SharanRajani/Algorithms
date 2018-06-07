import java.util.*;

public class MinDiffSubsets {

	static Scanner scan = new Scanner(System.in);

	static int cal(int[] arr, int i, int totalSum, int sumA) {
		if (i == 0)
			return Math.abs(sumA - (totalSum - sumA));
		else
			return Math.min(cal(arr, i - 1, totalSum, sumA + arr[i]), cal(arr, i - 1, totalSum, sumA));
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of elements:");
		int count = scan.nextInt();
		int[] arr = new int[count];
		int sum = 0;
		System.out.println("Enter the values of the elements:");
		for (int i = 0; i < count; i++) {
			arr[i] = scan.nextInt();
			sum += arr[i];
		}
		int minDiffBetwSubsets = cal(arr, count - 1, sum, 0);
		System.out.println("The minimum possible difference between 2 subsets created from the elements is:" + minDiffBetwSubsets);
	}
}
