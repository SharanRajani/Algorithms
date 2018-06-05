import java.util.*;

class point {
	int x, y;

	point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class ClosestPair {

	static Scanner scan = new Scanner(System.in);

	static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	static double bruteFind(point[] points, int lb, int ub) {
		double min = Double.MAX_VALUE;
		for (int i = lb; i <= ub; i++) {
			for (int j = i + 1; j <= ub; j++) {
				double curr = dist(points[i].x, points[i].y, points[j].x, points[j].y);
				if (curr < min) {
					min = curr;
				}
			}
		}
		return min;
	}

	static double findStripMin(point[] strip, double min) {
		for (int i = 0; i < strip.length; i++) {
			for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
				double t = dist(strip[i].x, strip[i].y, strip[j].x, strip[j].y);
				if (t < min)
					min = t;
			}
		}
		return min;
	}

	static double findClosest(point[] points, int lb, int ub) {
		if ((ub - lb) <= 3) {
			return bruteFind(points, lb, ub);
		} else {
			int mid = (lb + ub) / 2;
			point midPoint = points[mid];
			double lh = findClosest(points, lb, mid);
			double uh = findClosest(points, mid + 1, ub);
			double min = lh < uh ? lh : uh;
			ArrayList<point> strip = new ArrayList<point>();
			for (int i = lb; i <= ub; i++) {
				if (Math.abs(midPoint.x - points[i].x) < min) {
					strip.add(points[i]);
				}
			}
			point[] stripArr = new point[strip.size()];
			strip.toArray(stripArr);
			Arrays.sort(stripArr, new Comparator<point>() {
				public int compare(point a, point b) {
					return a.y - b.y;
				}
			});
			double stripMin = findStripMin(stripArr, min);
			return stripMin < min ? stripMin : min;
		}
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of points:");
		int count = scan.nextInt();
		point[] points = new point[count];
		for (int i = 0; i < count; i++) {
			System.out.println("Enter the x&y co-ordinate for " + (i + 1) + "th point:");
			int x = scan.nextInt();
			int y = scan.nextInt();
			points[i] = new point(x, y);
		}
		Arrays.sort(points, new Comparator<point>() {
			public int compare(point a, point b) {
				return a.x - b.x;
			}
		});
		double min = findClosest(points, 0, points.length - 1);
		System.out.println("The minimum distance between two points is:" + min);
	}
}
