package edu.iastate.cs228.hw4;

import static org.junit.Assert.assertTrue;

import edu.iastate.cs228.hw4.CodeMap.MapEntry;

public class MsgTree<E> {
	
	public char payLoadChar;
	public MsgTree root;
	public MsgTree left;
	public MsgTree right;
	public String encodingString;
	
	
	private static int staticCodeIdx = 0;
	private static int staticCharIdx = 0;
	private static String codeString = "";
	private static CodeMap<Integer, Character> cMap = new CodeMap();

	/*
	 * constructor building tree from a string
	 */
	public MsgTree(String encodingString) {
		this.encodingString = encodingString;
		/*
		 * check char at 1
		 * make char at  ind 1 static char idc: ^, increment
		 */
		char c = encodingString.charAt(staticCharIdx);
		if (c=='^') {
			this.payLoadChar = c;
			staticCharIdx++;
			this.right = new MsgTree(encodingString);
			staticCharIdx++;
			this.left = new MsgTree(encodingString);
		}
		else if (c!='^') {
			this.payLoadChar = c;
		}
		this.root=this;
	}
	
	
	
	/*
	 * constructor for a single node with null children
	 */
	public MsgTree(char payloadChar) {
		this.payLoadChar = payloadChar;
		this.left = null;
		this.right = null;
		this.root = this;
	}
	
	public MsgTree() {
		this.payLoadChar = '^';
		this.left = null;
		this.right = null;
		this.root = this;
	}
	
	public boolean hasLeft() {
		if (this.left==null) {
			return false;
		}
		return true;
	}
	
	public boolean hasRight() {
		if (this.right==null) {
			return false;
		}
		return true;
	}
	
	/*
	 * method to print characters and their binary codes
	 */
	public static void printCodes(MsgTree node, String code) {
		char c = code.charAt(staticCodeIdx);
		char data = node.payLoadChar;
		
		 if (data=='^') {
			if (c == '1' && node.hasRight()) {
				codeString+='1';
				staticCodeIdx++;
				printCodes(node.right(), code);
			} else if (c == '0' && node.hasLeft()) {
				codeString+='0';
				staticCodeIdx++;
				printCodes(node.left(), code);
			} 
		}
//		 Integer codeInt = Integer.parseInt(codeString);
		 Character cc = 'c';
		 cMap.put(codeString, data);
		 System.out.println(codeString);
		 codeString="";
//		 if (codeInt<code.length()) {
//			 
//		 }
		 
		 
	}
	
//	public static CodeMap.MapEntry printCodesRec(MsgTree root, String code) {
//		codeString+=
//		if (code==1) {
//			
//		}
//	}

	public MsgTree<?> getRoot() {
		return this.root;
	}
	
	public static int height(MsgTree<?> node) {
		if (node==null) {
			return -1;
		}
		int leftHeight = height(node.left());
		int rightHeight = height(node.right());
		return 1+Math.max(leftHeight, rightHeight);
	}
	
	public MsgTree<E> right() {
		return this.right;
	}
	
	public MsgTree<E> left() {
		return this.left;
	}
	
	public char getData() {
		return this.payLoadChar;
	}
	
	public void setLeft(MsgTree<E> left) {
		this.left = left;
	}
	
	public void setRight(MsgTree<E> right) {
		this.right = right;
	}
	
	public void setData(char data) {
		this.payLoadChar = data;
	}
	
	public boolean isLeaf() {
		if (this.left==null && this.right == null) {
			return true;
		}
		return false;
	}
	
//	public int size() {
//		return 1+ sizeRec(this);
//	}
	
	
	public int size() {
		return this.encodingString.length();
	}
	
//	public int sizeRec(MsgTree<E> node) {
//		if (node==null) { return 0; }
//		return sizeRec(node.left()) + sizeRec(node.right());
//	}
	

	public boolean containsNode(char value) {
		return containsNodeRec(this, value);
	}
	
	
	
	public boolean containsNodeRec(MsgTree current, char value) {
		if (current==null) {
			return false;
		}
		if (value==current.getData()) {
			return true;
		}
		return (containsNodeRec(current.left, value) ||  containsNodeRec(current.right, value))==true;
	}
	
	public static void setIdx() {
		staticCharIdx = 0;
	}
	
	public static void main(String[] args) {
		MsgTree trr = new MsgTree("^a^xy");
		System.out.println(trr.containsNode('^'));
		System.out.println(trr.containsNode('a'));
		System.out.println(trr.containsNode('x'));
		System.out.println(trr.containsNode('y'));
		printCodes(trr.root, "01011");
		
		
		
	}
	
	
	
	
	
	
	
	
	//END
}
