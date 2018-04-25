package caca;

import java.util.*;

class Node {
	int data;
	Node left, right;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

public class Crap {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			Node root = null;
			if (n == 0) {
				System.out.println("0");
				continue;
			}
			if (n == 1) {
				int a = sc.nextInt();
				System.out.println("1");
				continue;

			} else {
				while (n-- > 0) {
					int n1 = sc.nextInt();
					int n2 = sc.nextInt();
					char ch = sc.next().charAt(0);
					if (root == null) {
						root = new Node(n1);
						switch (ch) {
						case 'L':
							root.left = new Node(n2);
							break;
						case 'R':
							root.right = new Node(n2);
							break;
						}
					} else {
						insert(n1, n2, ch, root);
					}
				}
				GfG g = new GfG();
				g.largestBst(root);
			}
		}
	}

	public static void insert(int n1, int n2, char ch, Node root) {
		if (root == null)
			return;
		if (root.data == n1)
			switch (ch) {
			case 'L':
				root.left = new Node(n2);
				break;
			case 'R':
				root.right = new Node(n2);
				break;
			}
		insert(n1, n2, ch, root.left);
		insert(n1, n2, ch, root.right);
	}
}

/*
 * Please note that it's Function problem i.e. you need to write your solution
 * in the form of Function(s) only. Driver Code to call/invoke your function is
 * mentioned above.
 */

/*
 * The Node class is as follows class Node{ int data; Node left,right; Node(int
 * data) { this.data = data; this.left = null; this.right = null; } }
 */

class BstAttrs {
	Integer size;
	Integer maxSize;
	Integer minVal;
	Integer maxVal;
}

class GfG {
	public BstAttrs largestBstRecursive(Node node, Integer maxBstSz) {
		BstAttrs r = new BstAttrs();
		Integer curBstSz = 1;
		Integer minVal = node.data;
		Integer maxVal = node.data;
		Integer lSz = 0;
		Integer rSz = 0;

//		System.out.println(" nodo " + node.data + " maxsz " + maxBstSz);
		if (node.left != null) {
//			System.out.println(" hi " + node.left.data);

			BstAttrs rTmp = largestBstRecursive(node.left, maxBstSz);
			if (rTmp.size != null && rTmp.size > 0 && node.left.data < node.data && rTmp.maxVal != null
					&& rTmp.maxVal < node.data) {
				lSz = rTmp.size;
				minVal = java.lang.Math.min(minVal, rTmp.minVal);
			} else {
				curBstSz = null;
				minVal = null;
			}
			maxBstSz = java.lang.Math.max(maxBstSz, rTmp.maxSize);
		}
		if (node.right != null) {
//			System.out.println(" hd " + node.right.data);

			BstAttrs rTmp = largestBstRecursive(node.right, maxBstSz);
			if (curBstSz != null && curBstSz > 0 && rTmp.size != null && rTmp.size > 0 && node.right.data > node.data
					&& rTmp.minVal != null && rTmp.minVal > node.data) {
				rSz = rTmp.size;
				maxVal = java.lang.Math.max(maxVal, rTmp.maxVal);
			} else {
				curBstSz = null;
				maxVal = null;
			}
			maxBstSz = java.lang.Math.max(maxBstSz, rTmp.maxSize);
		}
		if (curBstSz != null) {
			curBstSz += lSz + rSz;
			maxBstSz = java.lang.Math.max(maxBstSz, curBstSz);
		}
		r.size = curBstSz;
		r.maxSize = maxBstSz;
		r.minVal = minVal;
		r.maxVal = maxVal;
//		System.out.println("r node " + node.data + " " + r.size + " " + r.maxSize);
		return r;
	}

	public int largestBst(Node node) {
		BstAttrs r = largestBstRecursive(node, 1);
		// System.out.println("a "+r[0]+ " b "+r[1]);
		System.out.println("" + r.maxSize);
		return r.maxSize;
	}
}