import java.util.Scanner;

class unit {
	int max;
	int min;

	unit() {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
	}
}

public class MinMax {

	static Scanner scan = new Scanner(System.in);

	static void findmm(int[] arr, int lb, int ub, unit val) {
		if (lb == ub) {
			val.max = arr[lb];
			val.min = arr[ub];
		} else if (lb == (ub - 1)) {
			if (arr[lb] < arr[ub]) {
				val.min = arr[lb];
				val.max = arr[ub];
			} else {
				val.max = arr[lb];
				val.min = arr[ub];
			}
		} else {
			int mid = (lb + ub) / 2;
			findmm(arr, lb, mid, val);
			unit val2 = new unit();
			findmm(arr, mid + 1, ub, val2);
			if (val.max < val2.max)
				val.max = val2.max;
			if (val.min > val2.min)
				val.min = val2.min;
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
		unit val = new unit();
		findmm(arr, 0, count - 1, val);
		System.out.println("\nMax Value:" + val.max);
		System.out.println("Min Value:" + val.min);
	}
}
