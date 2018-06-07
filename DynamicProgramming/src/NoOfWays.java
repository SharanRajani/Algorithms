
//Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.

import java.util.*;

public class NoOfWays {

	static Scanner scan = new Scanner(System.in);

	static void ways(int steps) {
		int[] array = new int[steps + 1];
		for (int i = 0; i <= steps; i++) {
			if (i == 0)
				array[i] = 1;
			else if (i == 1)
				array[i] = array[i - 1];
			else if (i == 2)
				array[i] = array[i - 1] + array[i - 2];
			else
				array[i] = array[i - 1] + array[i - 2] + array[i - 3];
		}
		System.out.println("\nThe No of Ways in which the distance can be covered is:" + array[steps]);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of steps to be covered:");
		int steps = scan.nextInt();
		ways(steps);
	}

}
