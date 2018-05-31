import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//Given an array of jobs where every job has a deadline and associated profit if the job
//is finished before the deadline. It is also given that every job takes single unit of time,
//so the minimum possible deadline for any job is 1.
//How to maximize total profit if only one job can be scheduled at a time.

class Job {
	private String name;
	private int deadline;
	private int profit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}
}

public class JobSequencing {

	static void find(Job[] jobs, int maxDeadline) {
		Arrays.sort(jobs, new Comparator<Job>() {
			public int compare(Job a, Job b) {
				return b.getProfit() - a.getProfit();
			}
		});
		String[] names = new String[maxDeadline + 1];
		int[] profits = new int[maxDeadline + 1];
		int totalProfit = 0;
		for (int i = 0; i < jobs.length; i++) {
			int d = jobs[i].getDeadline();
			for (int j = d; j >= 0; j--) {
				if (profits[j] == 0) {
					profits[j] = jobs[i].getProfit();
					totalProfit += profits[j];
					names[j] = jobs[i].getName();
					break;
				}
			}
		}
		System.out.println("The Maximum Total Profit is:" + totalProfit);
		System.out.println("The Jobs completed are:");
		for (int i = 0; i < maxDeadline + 1; i++) {
			if (names[i] != null) {
				System.out.print("\t" + names[i]);
			}
		}
	}

	public static void main(String[] args) {
		int jobCount, maxDeadline = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of Jobs:");
		jobCount = scan.nextInt();
		Job[] jobs = new Job[jobCount];
		System.out.println("Enter the Name, Deadline and Profit of the Jobs:");
		for (int i = 0; i < jobCount; i++) {
			jobs[i] = new Job();
			jobs[i].setName(scan.next());
			jobs[i].setDeadline(scan.nextInt() - 1);
			if (jobs[i].getDeadline() > maxDeadline) {
				maxDeadline = jobs[i].getDeadline();
			}
			jobs[i].setProfit(scan.nextInt());
		}
		/*
		 * for(int i=0;i<jobCount;i++) {
		 * System.out.println(jobs[i].getName()+" "+jobs[i].getDeadline()+" "+jobs[i].
		 * getProfit()); }
		 */
		find(jobs, maxDeadline);
	}

}
