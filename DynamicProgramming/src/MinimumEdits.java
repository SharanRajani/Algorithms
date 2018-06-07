
//Given two strings str1 and str2 and below operations that can performed on str1.
//Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2'.
//1.Insert,2.Remove,3.Replace
import java.util.*;

public class MinimumEdits {

	static Scanner scan = new Scanner(System.in);

	static void calEdits(char[] c1, char[] c2) {
		int[][] matrix = new int[c1.length + 1][c2.length + 1];
		for (int i = 0; i <= c1.length; i++) {
			for (int j = 0; j <= c2.length; j++) {
				if (i == 0)
					matrix[i][j] = j;
				else if (j == 0)
					matrix[i][j] = i;
				else if (c1[i - 1] == c2[j - 1])
					matrix[i][j] = matrix[i - 1][j - 1];
				else
					matrix[i][j] = 1 + Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]));
			}
		}
		System.out.println("\nThe Minimum number of edits required to convert String 1 to String 2 is:"
				+ matrix[c1.length][c2.length]);
	}

	public static void main(String[] args) {
		System.out.println("Enter String 1&2:");
		String s1 = scan.next();
		String s2 = scan.next();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		calEdits(c1, c2);
	}
}
