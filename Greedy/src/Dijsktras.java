import java.util.Scanner;

public class Dijsktras {

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

	static int findMin(int[] dist, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < dist.length; i++) {
			if (visited[i] == false && min > dist[i]) {
				min = dist[i];
				index = i;
			}
		}
		return index;
	}

	static void update(int index, int[] dist, boolean[] visited, int[] parent, int[][] adMatrix) {
		for (int i = 0; i < dist.length; i++) {
			if (visited[i] == false && adMatrix[index][i] != 0) {
				if (dist[i] > dist[index] + adMatrix[index][i]) {
					dist[i] = dist[index] + adMatrix[index][i];
					parent[i] = index;
				}
			}
		}
	}

	static void displayDist(int[] parent, int[] dist, int src) {
		int total = 0;
		System.out.println("The Distances to all the vertices from the source vertex is as follows:");
		for (int i = 0; i < dist.length; i++) {
			if (i != src)
				System.out.println(i + ":" + dist[i]);
		}
		System.out.println("The path from the source to each of the nodes is as follows:");
		for (int z = 0; z < parent.length; z++) {
			if (z != src) {
				int i = z;
				boolean flag = false;
				System.out.print(i + "->");
				while (i != parent[i]) {
					if (flag == false) {
						System.out.print(parent[i]);
						flag = true;
						i = parent[i];
					} else {
						System.out.print("->" + parent[i]);
						i = parent[i];
					}
				}
				System.out.println();
			}
		}
	}

	static void calDist(int[][] adMatrix) {
		int count = adMatrix.length;
		int[] dist = new int[count];
		int[] parent = new int[count];
		boolean[] visited = new boolean[count];
		System.out.println("Enter the source vertex:");
		int src = scan.nextInt();
		for (int i = 0; i < count; i++) {
			parent[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}
		dist[src] = 0;
		parent[src] = src;
		for (int i = 0; i < count - 1; i++) {
			int index = findMin(dist, visited);
			visited[index] = true;
			update(index, dist, visited, parent, adMatrix);
		}
		displayDist(parent, dist, src);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of Vertices in the Graph:");
		int count = scan.nextInt();
		int[][] adMatrix = new int[count][count];
		create(adMatrix);
		calDist(adMatrix);
	}
}
