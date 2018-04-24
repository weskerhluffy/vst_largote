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

class MaxBst {
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

class GfG {
	public Integer[] largestBstRecursive(Node node, Integer maxBstSz, Integer lLimit, Integer rLimit) {
		Integer r[] = new Integer[2];
		Integer curBstSz = 0;

		System.out.println(" nodo " + node.data + " ll " + lLimit + " rl " + rLimit);
		if (node.data > lLimit && node.data < rLimit) {
			if (node.left != null) {
				System.out.println(" hi " + node.left.data);

				Integer rTmp[] = largestBstRecursive(node.left, maxBstSz, lLimit, node.data);
				if (rTmp[0] > 0 && node.left.data < node.data) {
					curBstSz += rTmp[0];
				} else {
					curBstSz = 0;
				}
				maxBstSz = java.lang.Math.max(maxBstSz, rTmp[1]);
			}
			if (node.right != null) {
				System.out.println(" hd " + node.right.data);

				Integer rTmp[] = largestBstRecursive(node.right, maxBstSz, node.data, rLimit);
				if (curBstSz > 0 && rTmp[0] > 0 && node.right.data > node.data) {
					curBstSz += rTmp[0];
				} else {
					curBstSz = 0;
				}
				maxBstSz = java.lang.Math.max(maxBstSz, rTmp[1]);
			}
		}
		maxBstSz = java.lang.Math.max(maxBstSz, curBstSz);
		r[0] = curBstSz;
		r[1] = maxBstSz;
		System.out.println("r node " + node.data + " " + r[0] + " " + r[1]);
		return r;
	}

	public int largestBst(Node node) {
		Integer r[] = largestBstRecursive(node, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		// System.out.println("a "+r[0]+ " b "+r[1]);
		System.out.println("" + r[1]);
		return r[1];
	}
}