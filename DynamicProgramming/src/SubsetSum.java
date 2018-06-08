
//Given a set of non-negative integers, 
//and a value sum, determine if there is a subset of the given set with sum equal to given sum.
import java.util.*;

public class SubsetSum {

	static Scanner scan = new Scanner(System.in);

	// Simple Recursion
	static boolean simpleRecursion(int[] set, int total, int n, int key) {
		if (total == key)
			return true;
		else if (total < key)
			return false;
		else if (n <= 0)
			return false;
		else {
			boolean res1 = simpleRecursion(set, total - set[n - 1], n - 1, key);
			boolean res2 = simpleRecursion(set, total, n - 1, key);
			if (res1 || res2)
				return true;
			return false;
		}
	}

	// Memoization
	static boolean MCal(int[] set, int total, int n, int key, int[][] matrix) {
		if (matrix[n][total] == -1) {
			if (total == key) {
				matrix[n][total] = 1;
				return true;
			} else if (total < key) {
				matrix[n][total] = 0;
				return false;
			} else if (n <= 0) {
				matrix[n][total] = 0;
				return false;
			} else {
				boolean res1 = MCal(set, total - set[n - 1], n - 1, key, matrix);
				boolean res2 = MCal(set, total, n - 1, key, matrix);
				if (res1 || res2) {
					matrix[n][total] = 1;
					return true;
				}
				matrix[n][total] = 0;
				return false;
			}
		} else if (matrix[n][total] == 1)
			return true;
		else
			return false;
	}

	// Tabulation
	static boolean TCal(int[] set, int total, int n, int key, int[][] matrix) {
		for (int i = 0; i <= set.length; i++) {
			for (int j = 0; j <= total; j++) {
				if (i <= 0)
					matrix[i][j] = 0;
				else if (j == key)
					matrix[i][j] = 1;
				else if (j < key)
					matrix[i][j] = 0;
				else if (j < set[i - 1])
					matrix[i][j] = matrix[i - 1][j];
				else
					matrix[i][j] = Math.max(matrix[i - 1][j - set[i - 1]], matrix[i - 1][j]);
			}
		}
		if (matrix[set.length][total] == 1)
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of elements in the set:");
		int count = scan.nextInt();
		int[] set = new int[count];
		int sum = 0;
		System.out.println("\nEnter the elements:");
		for (int i = 0; i < count; i++) {
			set[i] = scan.nextInt();
			sum += set[i];
		}
		System.out.println("\nEnter the sum:");
		int key = scan.nextInt();
		// boolean res = simpleRecursion(set, sum, count, key);
		int[][] matrix = new int[count + 1][sum + 1];
		for (int i = 0; i <= count; i++) {
			for (int j = 0; j <= sum; j++) {
				matrix[i][j] = -1;
			}
		}
		boolean res = MCal(set, sum, count, key, matrix);
		// boolean res = TCal(set, sum, count, key, matrix);
		if (res)
			System.out.println("\nThere is a subset present with the given sum.");
		else
			System.out.println("\nSubset with the given sum not found.");
	}
}
