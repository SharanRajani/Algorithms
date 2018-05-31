
//You are given n activities with their start and finish times. 
//Select the maximum number of activities that can be performed by a single person, 
//assuming that a person can only work on a single activity at a time.

import java.util.*;

class Activities {
	private int start;
	private int end;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}

public class ActivitySelectionProblem {

	static int calculateMaxActivites(Activities[] jb) {
		int[] jobsd = new int[jb.length];
		int jobsDone = 0;
		int curStart, preStart, preFinish;
		Arrays.sort(jb, new Comparator<Activities>() {
			public int compare(Activities a, Activities b) {
				return a.getEnd() - b.getEnd();// Ascending
			}
		});
		for (int i = 0; i < jb.length; i++) {
			System.out.println(jb[i].getEnd() + " " + jb[i].getStart());
		}
		jobsd[jobsDone] = 0;
		jobsDone++;
		preStart = jb[0].getStart();
		preFinish = jb[0].getEnd();
		for (int i = 1; i < jb.length; i++) {
			curStart = jb[i].getStart();
			if (curStart >= preFinish) {
				jobsd[jobsDone] = i;
				preStart = curStart;
				preFinish = jb[i].getEnd();
				jobsDone++;
			}
		}
		System.out.println("The Jobs done are:");
		for (int i = 0; i < jobsDone; i++) {
			System.out.print(jobsd[i] + "\t");
		}
		return jobsDone;
	}

	public static void main(String[] args) {
		int jobCount;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of Jobs:");
		jobCount = scan.nextInt();
		Activities[] jb = new Activities[jobCount];
		System.out.println("Enter the Start and End time of each Job:");
		for (int i = 0; i < jobCount; i++) {
			jb[i] = new Activities();
			jb[i].setStart(scan.nextInt());
			jb[i].setEnd(scan.nextInt());
		}
		int res = calculateMaxActivites(jb);
		System.out.println("\nThe max number of activities that can be performed are:" + res);
	}
}
