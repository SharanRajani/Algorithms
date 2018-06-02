import java.util.*;

public class Prims {

	static Scanner scan = new Scanner(System.in);

	static void create(int[][] adMatrix) {
		System.out.println("Enter the weight of each of the edges considering a directed Graph:");
		System.out.println("Enter 0 if edge does not exist.");
		for (int i = 0; i < adMatrix.length; i++) {
			for (int j = i + 1; j < adMatrix[0].length; j++) {
				System.out.println("Enter the weight of the edge " + i + "-" + j + ":");
				adMatrix[j][i] = adMatrix[i][j] = scan.nextInt();
			}
		}
	}

	static int findMin(int[] key, boolean[] mst) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < key.length; i++) {
			if (mst[i] == false && min > key[i]) {
				min = key[i];
				index = i;
			}
		}
		return index;
	}

	static void update(int index, int[] key, boolean[] mst, int[] parent, int[][] adMatrix) {
		for (int i = 0; i < key.length; i++) {
			if (mst[i] == false && adMatrix[index][i] != 0 && adMatrix[index][i] < key[i]) {
				key[i] = adMatrix[index][i];
				parent[i] = index;
			}
		}
	}

	static void displayMST(int[] parent, int[] key) {
		int total = 0;
		System.out.println("The edges in the MST are:");
		for (int i = 1; i < parent.length; i++) {
			total += key[i];
			System.out.println(parent[i] + "-" + i);
		}
		System.out.println("The total weight of the MST is:" + total);
	}

	static void calMST(int[][] adMatrix) {
		int count = adMatrix.length;
		int[] key = new int[count];
		int[] parent = new int[count];
		boolean[] mst = new boolean[count];
		key[0] = 0;
		for (int i = 1; i < count; i++) {
			parent[i] = -1;
			key[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < count; i++) {
			int index = findMin(key, mst);
			mst[index] = true;
			update(index, key, mst, parent, adMatrix);
		}
		displayMST(parent, key);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of Vertices in the Graph:");
		int count = scan.nextInt();
		int[][] adMatrix = new int[count][count];
		create(adMatrix);
		calMST(adMatrix);
	}

}
