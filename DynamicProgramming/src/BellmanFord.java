import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class edge {
	int u, v, w;
}

public class BellmanFord {

	static Scanner scan = new Scanner(System.in);

	static edge[] create(int[][] adMatrix) {
		int count = 0;
		System.out.println("Enter the weight of each of the edges considering a undirected Graph:");
		System.out.println("Enter 0 if edge does not exist.");
		for (int i = 0; i < adMatrix.length; i++) {
			for (int j = 0; j < adMatrix[0].length; j++) {
				if (i != j) {
					System.out.println("Enter the weight of the edge " + i + "-" + j + ":");
					adMatrix[i][j] = scan.nextInt();
					if (adMatrix[i][j] != 0)
						count++;
				}
			}
		}
		int cnt = 0;
		edge[] edgeList = new edge[count];
		for (int i = 0; i < adMatrix.length; i++) {
			for (int j = 0; j < adMatrix[0].length; j++) {
				if (adMatrix[i][j] != 0) {
					edgeList[cnt] = new edge();
					edgeList[cnt].u = i;
					edgeList[cnt].v = j;
					edgeList[cnt++].w = adMatrix[i][j];
				}
			}
		}
		return edgeList;
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

	static void calDist(edge[] edgeList, int count, int[][] adMatrix) {
		int[] dist = new int[count];
		int[] parent = new int[count];
		boolean flag;
		for (int i = 0; i < count; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		System.out.println("Enter the source vertex:");
		int src = scan.nextInt();
		dist[src] = 0;
		parent[src] = src;
		int w=0;
		for (int i = 0; i < count - 1; i++) {// check whether dist[u] is not infinity because dist[u] + -ve weight can
												// be less than infinity
			flag=false;
			for (int j = 0; j < edgeList.length; j++) {
				if ((dist[edgeList[j].u] != Integer.MAX_VALUE)
						&& (dist[edgeList[j].v] > dist[edgeList[j].u] + edgeList[j].w)) {
					flag=true;
					dist[edgeList[j].v] = dist[edgeList[j].u] + edgeList[j].w;
					parent[edgeList[j].v] = edgeList[j].u;
				}
			}
			if(!flag)
				break;
		}
		flag = false;
		for (int j = 0; j < edgeList.length; j++) {
			if ((dist[edgeList[j].u] != Integer.MAX_VALUE)
					&& (dist[edgeList[j].v] > dist[edgeList[j].u] + edgeList[j].w)) {
				System.out.println("ERROR:Graph contains -ve cycle.");
				flag = true;
				break;
			}
		}
		if (!flag)
			displayDist(parent, dist, src);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of Vertices in the Graph:");
		int count = scan.nextInt();
		int[][] adMatrix = new int[count][count];
		edge[] edgeList;
		edgeList = create(adMatrix);
		calDist(edgeList, count, adMatrix);
	}
}