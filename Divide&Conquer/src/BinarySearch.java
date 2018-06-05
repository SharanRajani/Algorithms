import java.util.*;

public class BinarySearch {

	static Scanner scan = new Scanner(System.in);

	static void bsearch(int[] arr, int key, int lb, int ub) {
		if (lb <= ub) {
			int mid = (lb + ub) / 2;
			if (arr[mid] == key) {
				System.out.println("\nElement found at " + (mid + 1) + "th location.");
			} else if (key > arr[mid]) {
				bsearch(arr, key, mid + 1, ub);
			} else {
				bsearch(arr, key, lb, mid - 1);
			}
		} else {
			System.out.println("\nElement not found.");
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
		System.out.println("\nEnter the element to be searched:");
		int key = scan.nextInt();
		Arrays.sort(arr);
		bsearch(arr, key, 0, arr.length - 1);
	}
}
