import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class edge {
	int u, v, w;
}

public class Kruskals {

	static Scanner scan = new Scanner(System.in);

	static edge[] create(int[][] adMatrix) {
		int count = 0;
		System.out.println("Enter the weight of each of the edges considering a directed Graph:");
		System.out.println("Enter 0 if edge does not exist.");
		for (int i = 0; i < adMatrix.length; i++) {
			for (int j = i + 1; j < adMatrix[0].length; j++) {
				System.out.println("Enter the weight of the edge " + i + "-" + j + ":");
				adMatrix[j][i] = adMatrix[i][j] = scan.nextInt();
				if (adMatrix[i][j] != 0)
					count++;
			}
		}
		int cnt = 0;
		edge[] edgeList = new edge[count];
		for (int i = 0; i < adMatrix.length; i++) {
			for (int j = i + 1; j < adMatrix[0].length; j++) {
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

	static int findComp(int v, int[] comp) {
		if (comp[v] != v)
			comp[v] = findComp(comp[v], comp);
		return comp[v];
	}

	static void union(int u, int v, int[] comp, int[] rank) {
		int c1 = findComp(u, comp);
		int c2 = findComp(v, comp);
		if (rank[c1] > rank[c2]) {
			comp[c2] = c1;
		} else if (rank[c1] < rank[c2]) {
			comp[c1] = c2;
		} else {
			rank[c1]++;
			comp[c2] = c1;
		}
	}

	static void calMST(edge[] edgeList, int vcount) {
		int mcount = 0, cnt = 0, cost = 0;
		edge[] mst = new edge[vcount - 1];
		Arrays.sort(edgeList, new Comparator<edge>() {
			public int compare(edge a, edge b) {
				return a.w - b.w;
			}
		});
		int[] comp = new int[vcount];
		int[] rank = new int[vcount];
		for (int i = 0; i < vcount; i++) {
			comp[i] = i;
		}
		while (mcount != vcount - 1) {
			edge nev = edgeList[cnt++];
			int c1 = findComp(nev.u, comp);
			int c2 = findComp(nev.v, comp);
			if (c1 != c2) {
				mst[mcount] = new edge();
				mst[mcount++] = nev;
				cost += nev.w;
				union(nev.u, nev.v, comp, rank);
			}
		}
		System.out.println("The Edges in the Minimum Spanning Tree(By Kruskals Method) is:");
		for (int i = 0; i < mcount; i++) {
			System.out.println(mst[i].u + "-" + mst[i].v);
		}
		System.out.println("The Cost is:" + cost);
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of Vertices in the Graph:");
		int count = scan.nextInt();
		int[][] adMatrix = new int[count][count];
		edge[] edgeList;
		edgeList = create(adMatrix);
		calMST(edgeList, adMatrix.length);
	}
}