import java.util.*;

//Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) such that 
//all cells along the path are in increasing order with a difference of 1.
//We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1)
//with the condition that the adjacent cells have a difference of 1.

public class LongestPathInMatrix {

	static Scanner scan = new Scanner(System.in);

	static int calPath(int[][] matrix, int[][] path, int i, int j) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix.length)
			return 0;
		else if (path[i][j] == -1) {
			if ((i - 1) >= 0 && matrix[i][j] + 1 == matrix[i - 1][j])
				path[i][j] = 1 + calPath(matrix, path, i - 1, j);
			else if ((i + 1) < matrix.length && matrix[i][j] + 1 == matrix[i + 1][j])
				path[i][j] = 1 + calPath(matrix, path, i + 1, j);
			else if ((j - 1) >= 0 && matrix[i][j] + 1 == matrix[i][j - 1])
				path[i][j] = 1 + calPath(matrix, path, i, j - 1);
			else if ((j + 1) < matrix.length && matrix[i][j] + 1 == matrix[i][j + 1])
				path[i][j] = 1 + calPath(matrix, path, i, j + 1);
			else
				path[i][j] = 1;
		}
		return path[i][j];
	}

	static void functionContainer(int[][] matrix, int[][] path) {
		int count = matrix.length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (path[i][j] == -1)
					path[i][j] = calPath(matrix, path, i, j);
				if (max < path[i][j])
					max = path[i][j];
			}
		} // Time Complexity is n^2 since each path[i][j] is computed only once.
		System.out.println("\nLength of the Longest Path is:" + max);
	}

	public static void main(String[] args) {
		System.out.println("Enter the size of the square matrix:");
		int count = scan.nextInt();
		int[][] matrix = new int[count][count];
		int[][] path = new int[count][count];
		System.out.println("\nEnter the values of the matrix elements:");
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				matrix[i][j] = scan.nextInt();
				path[i][j] = -1;
			}
		}
		functionContainer(matrix, path);
	}
}
