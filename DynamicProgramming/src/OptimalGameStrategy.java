import java.util.Scanner;

//https://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
//Consider a row of n coins of values v1 . . . vn, where n is even.
//We play a game against an opponent by alternating turns.
//In each turn, a player selects either the first or last coin from the row,
//removes it from the row permanently, and receives the value of the coin.
//Determine the maximum possible amount of money we can definitely win if we move first.
//eg  8, 15, 3, 7: 22(7+15)
//eg  5, 3, 7, 10: 15(10+5)

class OptimalGameStrategy {

	static Scanner scan = new Scanner(System.in);

	static int cal(int[] array, int i, int j, int[][] matrix) {
		if (matrix[i][j] == -1) {
			int i1, i2, j1, j2;
			if (i + 1 != j) {
				if (array[i + 1] > array[j]) {
					i1 = i + 2;
					j1 = j;
				} else {
					i1 = i + 1;
					j1 = j - 1;
				}
				if (array[i] > array[j - 1]) {
					i2 = i + 1;
					j2 = j - 1;
				} else {
					i2 = i;
					j2 = j - 2;
				}
				matrix[i][j] = Math.max(array[i] + cal(array, i1, j1, matrix), array[j] + cal(array, i2, j2, matrix));
			} else {
				matrix[i][j] = Math.max(array[i], array[j]);
			}
		}
		return matrix[i][j];
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of coins:");
		int count = scan.nextInt();
		int[] array = new int[count];
		System.out.println("\nEnter the values of each coin:");
		for (int i = 0; i < count; i++)
			array[i] = scan.nextInt();
		int[][] matrix = new int[count][count];
		for (int i = 0; i < count; i++)
			for (int j = 0; j < count; j++)
				matrix[i][j] = -1;
		int val = cal(array, 0, count - 1, matrix);
		System.out.println("\nThe Maximum Total that can be obtained is:" + val);
	}
}
