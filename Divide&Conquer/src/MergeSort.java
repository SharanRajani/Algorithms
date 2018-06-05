import java.util.*;

public class MergeSort {

	static Scanner scan = new Scanner(System.in);

	static void merge(int[] arr, int lb, int mid, int ub) {
		int[] temp = new int[arr.length];
		int i = lb, j = mid + 1;
		int cnt = lb;
		while (i <= mid && j <= ub) {
			if (arr[i] <= arr[j]) {
				temp[cnt++] = arr[i];
				i++;
			} else {
				temp[cnt++] = arr[j];
				j++;
			}
		}
		while (i <= mid) {
			temp[cnt++] = arr[i];
			i++;
		}
		while (j <= ub) {
			temp[cnt++] = arr[j];
			j++;
		}
		for(int k=lb;k<=ub;k++)
		{
			arr[k]=temp[k];
		}
	}

	static void msort(int[] arr, int lb, int ub) {
		if (lb < ub) {
			int mid = (lb + ub) / 2;
			msort(arr, lb, mid);
			msort(arr, mid + 1, ub);
			merge(arr, lb, mid, ub);
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of elements:");
		int count = scan.nextInt();
		int[] arr = new int[count];
		System.out.println("\nEnter the elements:");
		for (int i = 0; i < count; i++) {
			arr[i] = scan.nextInt();
		}
		msort(arr, 0, count - 1);
		System.out.println("\nThe sorted elements are:");
		for (int i = 0; i < count; i++) {
			System.out.print(arr[i] + "\t");
		}
	}

}
