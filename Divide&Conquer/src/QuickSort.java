import java.util.*;

public class QuickSort {

	static Scanner scan = new Scanner(System.in);

	static int split(int[] arr, int lb, int ub) {
		int i = lb + 1, j = ub, pivot = lb;
		while (true) {
			while (i < arr.length && arr[pivot] > arr[i])
				i++;
			while (arr[pivot] < arr[j])
				j--;
			if (i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			} else {
				int temp = arr[j];
				arr[j] = arr[pivot];
				arr[pivot] = temp;
				break;
			}
		}
		return j;
	}

	static void qsort(int[] arr, int lb, int ub) {
		if (lb < ub) {
			int pivot = split(arr, lb, ub);
			qsort(arr, lb, pivot - 1);
			qsort(arr, pivot + 1, ub);
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
		qsort(arr, 0, count - 1);
		System.out.println("\nThe sorted elements are:");
		for (int i = 0; i < count; i++) {
			System.out.print(arr[i] + "\t");
		}
	}
}
