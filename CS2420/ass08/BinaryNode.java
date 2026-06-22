package assign08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a (generic) node in a binary tree.
 * 
 * @author Haoquan Wang
 * @version 2026-3-16
 */
public class BinaryNode<T> {
	// representation of a binary node
	private T element;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	private BinaryNode<T> parent;
	
	// additional members used only for generating DOT format
	private static int dotLabelCount = 0;
	private int dotLabel;

	/**
	 * Creates a new binary node with the given data and child references.
	 * 
	 * @param element - data element to store at this node
	 * @param leftChild - reference to this node's left child
	 * @param rightChild - reference to this node's right child
	 */
	public BinaryNode(T element, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
		this.element = element;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.dotLabel = dotLabelCount++;
	}

	/**
	 * Creates a new binary node with the given data, child references, and parent reference.
	 * 
	 * @param element - data element to store at this node
	 * @param leftChild - reference to this node's left child
	 * @param rightChild - reference to this node's right child
	 * @param parent - reference to this node's parent
	 */
	public BinaryNode(T element, BinaryNode<T> leftChild, BinaryNode<T> rightChild, BinaryNode<T> parent) {
		this.element = element;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
		this.dotLabel = dotLabelCount++;
	}

	/**
	 * Creates a binary node with the given data, and both child references set to null.
	 * 
	 * @param element - data element to store at this node
	 */
	public BinaryNode(T element) {
		this(element, null, null, null);
	}
	
	/**
	 * Returns the data element stored at this node.
	 * 
	 * @return the data element at this node
	 */
	public T getData(){
		return this.element;
	}
	
	/**
	 * Sets the data element stored at this node.
	 * 
	 * @param element - the new data element
	 */
	public void setData(T element) {
		this.element = element;
	}
	
	/**
	 * Returns the left child of this node.
	 * 
	 * @return reference to this node's left child, or null if none
	 */
	public BinaryNode<T> getLeftChild() {
		return this.leftChild;
	}
	
	/**
	 * Sets the left child of this node.
	 * 
	 * @param leftChild - the new left child
	 */
	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * Returns the right child of this node.
	 * 
	 * @return reference to this node's right child, or null if none
	 */
	public BinaryNode<T> getRightChild() {
		return this.rightChild;
	}
	
	/**
	 * Sets the right child of this node.
	 * 
	 * @param rightChild - the new right child
	 */
	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * Returns the parent of this node.
	 * 
	 * @return reference to this node's parent, or null if this node is the root
	 */
	public BinaryNode<T> getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the parent of this node.
	 * 
	 * @param parent - the new parent node
	 */
	public void setParent(BinaryNode<T> parent) {
		this.parent = parent;
	}

	/**
	 * Print the elements of the tree rooted at this binary node, using preorder (element
	 * followed by left subtree followed by right subtree) traversal.
	 */
	public void printPreorder() {
		// "visit" this node
		System.out.print(element + " ");
		// do a recursive traversal of the subtree on the left
		if(leftChild != null)
			leftChild.printPreorder();
		// do a recursive traversal of the subtree on the right
		if(rightChild != null)
			rightChild.printPreorder();
	}

	/**
	 * Print the elements of the tree rooted at this binary node, using postorder (left
	 * subtree followed by right subtree followed by element) traversal.
	 */
	public void printPostorder() {
		// do a recursive traversal of the subtree on the left
		if(leftChild != null)
			leftChild.printPostorder();
		// do a recursive traversal of the subtree on the right
		if(rightChild != null)
			rightChild.printPostorder();
		// "visit" this node
		System.out.print(element + " ");
	}

	/**
	 * Print the elements of the tree rooted at this binary node, using inorder (left
	 * subtree followed by element followed by right subtree) traversal.
	 */
	public void printInorder() {
		// do a recursive traversal of the subtree on the left
		if(leftChild != null)
			leftChild.printInorder();
		// "visit" this node
		System.out.print(element + " ");
		// do a recursive traversal of the subtree on the right
		if(rightChild != null)
			rightChild.printInorder();
	}

	/**
	 * Print the elements of the tree rooted at this binary node, using level-order
	 * (breadth-first) traversal.
	 */
	public void printLevelorder() {
		Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
		q.offer(this);

		while(!q.isEmpty()) {
			BinaryNode<T> x = q.poll();
			System.out.print(x.element + " ");
			if(x.leftChild != null)
				q.offer(x.leftChild);
			if(x.rightChild != null)
				q.offer(x.rightChild);
		}
	}

	/**
	 * Generates the DOT-formatted description of the tree rooted at this binary node.
	 * 
	 * @return string containing edges in the tree rooted at this binary node, in DOT format
	 */
	private String generateDot() {
		String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
		if(leftChild != null)
			ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + 
					leftChild.generateDot();
		if(rightChild != null)
			ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + 
					rightChild.generateDot();
		return ret;
	}

	/**
	 * Generates a DOT representation of the tree rooted at the given root binary node.
	 * (Note that this method is generic independent of the enclosing class; thus its 
	 * type placeholder is different.)
	 * 
	 * @param root - binary node that is the root of the tree
	 * @return string containing this tree expressed using the DOT language
	 */
	public static <Type> String generateDot(BinaryNode<Type> root) {
		String dot = "digraph Tree {\n\tnode [shape=record]\n";
		dot += root.generateDot();
		return dot + "}";
	}
}