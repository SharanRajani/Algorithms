import java.util.*;
//Huffman coding is a lossless data compression algorithm.
//The idea is to assign variable-length codes to input characters,
//lengths of the assigned codes are based on the frequencies of corresponding characters.
//The most frequent character gets the smallest code and the least frequent character gets the largest code.
//The variable-length codes assigned to input characters are Prefix Codes,
//means the codes (bit sequences) are assigned in such a way that the code assigned
//to one character is not prefix of code assigned to any other character.
//This is how Huffman Coding makes sure that there is no ambiguity when decoding the generated bit stream.

class node {
	int frequency;
	node left;
	node right;
	String name;
	String code;
}

public class HuffmanCoding {

	static void printCodes(node temp, String s) {
		if (temp.left == null && temp.right == null) {
			System.out.println(temp.name + " " + s);
		} else {
			printCodes(temp.left, s + "0");
			printCodes(temp.right, s + "1");
		}
	}

	static void createCodes(PriorityQueue<node> minHeap, node[] letters) {
		node n1, n2;
		while (true) {
			n1 = minHeap.poll();
			n2 = minHeap.poll();
			if (n2 == null)
				break;
			node nev = new node();
			nev.frequency = n1.frequency + n2.frequency;
			nev.left = n1;
			nev.right = n2;
			minHeap.add(nev);
		}
		node root = n1;
		System.out.println("The codes are as follows:");
		printCodes(root, "");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of letters:");
		int count = scan.nextInt();
		PriorityQueue<node> minHeap = new PriorityQueue<node>(count, new Comparator<node>() {
			public int compare(node a, node b) {
				return a.frequency - b.frequency;
			}
		});
		node[] letters = new node[count];
		System.out.println("Enter the letters and frequencies:");
		for (int i = 0; i < count; i++) {
			letters[i] = new node();
			letters[i].name = scan.next();
			letters[i].frequency = scan.nextInt();
			minHeap.add(letters[i]);
		}
		createCodes(minHeap, letters);
	}

}
