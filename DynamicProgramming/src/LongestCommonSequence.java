
/*Given two sequences, find the length of longest subsequence present in both of them.
A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.*/
import java.util.*;

public class LongestCommonSequence {

	static Scanner scan = new Scanner(System.in);

	static int recursivelcs(char[] c1, char[] c2, int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		else if (c1[m - 1] == c2[n - 1])
			return 1 + recursivelcs(c1, c2, m - 1, n - 1);
		else
			return Math.max(recursivelcs(c1, c2, m, n - 1), recursivelcs(c1, c2, m - 1, n));
	}

	static void lcs(char[] c1, char[] c2, int m, int n) {
		int[][] matrix = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					matrix[i][j] = 0;
				else if (c1[i - 1] == c2[j - 1])
					matrix[i][j] = 1 + matrix[i - 1][j - 1];
				else
					matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
			}
		}
		System.out.println("The length of the Longest Common Sequence is:" + matrix[m][n]);
		int i = m, j = n;
		int cnt = matrix[m][n] + 1;
		char[] lcs = new char[cnt];
		lcs[--cnt] = '\0';
		while (i > 0 && j > 0) {
			if (c1[i - 1] == c2[j - 1]) {
				lcs[--cnt] = c1[i - 1];
				i--;
				j--;
			} else {
				if (matrix[i - 1][j] > matrix[i][j - 1])
					i--;
				else
					j--;
			}
		}
		System.out.print("The Longest Common Sequence is:");
		for (int k = 0; k <= matrix[m][n]; k++) {
			System.out.print(lcs[k]);
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter the 1st String:");
		String s1 = scan.next();
		System.out.println("Enter the 2nd String:");
		String s2 = scan.next();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		lcs(c1, c2, c1.length, c2.length);
		/*
		 * int max = recursivelcs(c1, c2, c1.length, c2.length);
		 * System.out.println("The length of the Longest Common Sequence is:" + max);
		 */
	}

}
