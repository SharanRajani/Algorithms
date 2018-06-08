import java.util.*;

//https://www.geeksforgeeks.org/knapsack-problem/

public class Knapsack01 {

	static Scanner scan = new Scanner(System.in);

	// Simple Recursive
	static int simpleRecursion(int W, int[] weights, int[] values, int n) {
		if (W == 0 || n == 0)
			return 0;
		else if (weights[n - 1] > W)
			return simpleRecursion(W, weights, values, n - 1);
		return Math.max(values[n - 1] + simpleRecursion(W - weights[n - 1], weights, values, n - 1),
				simpleRecursion(W, weights, values, n - 1));
	}

	// Memoization
	static int MCal(int W, int[] weights, int[] values, int n, int[][] matrix) {
		if (matrix[n][W] == -1) {
			if (W == 0 || n == 0)
				matrix[n][W] = 0;
			else if (weights[n - 1] > W)
				matrix[n][W] = MCal(W, weights, values, n - 1, matrix);
			else
				matrix[n][W] = Math.max(values[n - 1] + MCal(W - weights[n - 1], weights, values, n - 1, matrix),
						MCal(W, weights, values, n - 1, matrix));
		}
		return matrix[n][W];
	}

	// Tabulation
	static void TCal(int W, int[] weights, int[] values, int n, int[][] matrix) {
		for (int i = 0; i <= values.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0)
					matrix[i][j] = 0;
				else if (weights[i - 1] > j)
					matrix[i][j] = matrix[i - 1][j];
				else
					matrix[i][j] = Math.max(values[i - 1] + matrix[i - 1][j - weights[i - 1]], matrix[i - 1][j]);
			}
		}
		System.out.println("\nThe Max Profit is:" + matrix[values.length][W]);
		System.out.print("\nThe Items selected are: ");
		int i = values.length, j = W;
		while (i > 0 && j > 0) {
			if (matrix[i][j] != matrix[i - 1][j]) {
				System.out.print((i - 1) + "\t");
				j = j - weights[i - 1];
				i--;
			} else {
				i--;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of Items:");
		int count = scan.nextInt();
		int[] values = new int[count];
		int[] weights = new int[count];
		for (int i = 0; i < count; i++) {
			System.out.println("\nEnter the weight & value of Item " + (i + 1) + ":");
			weights[i] = scan.nextInt();
			values[i] = scan.nextInt();
		}
		System.out.println("\nEnter the weight of the Knapsack:");
		int W = scan.nextInt();
		// int maxProfit = simpleRecursion(W, weights, values, count);
		// System.out.println("\nMax Profit:" + maxProfit);
		int[][] matrix = new int[count + 1][W + 1];
		for (int i = 0; i <= count; i++)
			for (int j = 0; j <= W; j++)
				matrix[i][j] = -1;
		// int maxProfit = MCal(W, weights, values, count, matrix);
		// System.out.println("\nMax Profit:" + maxProfit);
		TCal(W, weights, values, count, matrix);
	}
}
