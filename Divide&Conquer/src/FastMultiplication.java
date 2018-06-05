import java.util.Scanner;

//Logic is Correct but Implementation is Wrong.
//To be Implemented using Strings with same logic.
//This Implementation further increases time complexity than normal multiplication.
public class FastMultiplication {

	static Scanner scan = new Scanner(System.in);

	static int Multiply(int a, int b) {
		int nofA = (int) Math.log10(a) + 1;
		int nofB = (int) Math.log10(b) + 1;
		int n = nofA > nofB ? nofA : nofB;
		if (n <= 1) {
			return a * b;
		} else {
			int a0 = (int) (a % (Math.pow(10, n / 2)));
			int a1 = (int) ((a - a0) / (Math.pow(10, n / 2)));
			int b0 = (int) (b % (Math.pow(10, n / 2)));
			int b1 = (int) ((b - b0) / (Math.pow(10, n / 2)));
			int c0 = Multiply(a0, b0);
			int c2 = Multiply(a1, b1);
			int c1 = Multiply(a0 + a1, b0 + b1) - c0 - c2;
			return (int) ((c2 * (Math.pow(10, n))) + (c1 * (Math.pow(10, n / 2))) + c0);
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter the values of the 2 elements to be multiplied(Even number of digits):");
		int a = scan.nextInt();
		int b = scan.nextInt();
		int res = Multiply(a, b);
		System.out.println("The result of the multiplication is:" + res);
	}
}
