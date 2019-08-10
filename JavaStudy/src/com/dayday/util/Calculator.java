package com.dayday.util;

import java.util.HashMap;

public class Calculator {
	
	public static void main(String[] args) {
//		hanoi(20, 'A', 'B', 'C');
//		Node n1 = new Node("A", null),
//				n2 = new Node("*", null),
//				n3 = new Node("B", null),
//				n4 = new Node("+", null),
//				n5 = new Node("C", null),
//				n6 = new Node("+", null),
//				n7 = new Node("D", null),
//				n8 = new Node("*", null),
//				n9 = new Node("E", null);
//		n1.setNext(n2);
//		n2.setNext(n3);
//		n3.setNext(n4);
//		n4.setNext(n5);
//		n5.setNext(n6);
//		n6.setNext(n7);
//		n7.setNext(n8);
//		n8.setNext(n9);
//		System.out.println(n1);
		System.out.print(countExp("1*5 +6*9*5+6"));
//		int[] n = new int[] {
//				1, 2, 3, 4
//		};
//		for (int in : n)
//			System.out.print(in + " ");
//		System.out.println();
//		int i = 1;
//		n[i - 1] = n[++i] + n[i];
//		for (int in : n)
//			System.out.print(in + " ");
	}
	
	private static HashMap<String, Integer> opr = new HashMap<>(8);
	
	static {
		opr.put("(", 5);
		opr.put("（", 5);
		opr.put("^", 4);
		opr.put("*", 3);
		opr.put("/", 3);
		opr.put("+", 2);
		opr.put("-", 2);
		opr.put(")", 1);
		opr.put("）", 1);
	}
	
	public static double countExp(String exp) {
		return countPostfixExp(getPostfixExp(getInfixExp(preDealExp(exp))));
	}
	
	/**
	 * 计算后缀表达式
	 * */
	private static double countPostfixExp(Node head) {
		/*
		 * 1,创建数字栈
		 * 2,如果为数字,直接入栈
		 * 3,如果为操作符,将栈顶两个元素出栈,计算后入栈
		 * */
		Node[] numStack = new Node[10];
		int top = -1, size = 0;
		Node tmp = head;
		while (tmp != null) {
			Node next = tmp.next;
			tmp.next = null;
			if (!isOpr(tmp)) {
				numStack[++top] = tmp;
				size ++;
			} else {
				numStack[top - 1] = new Node(String.valueOf(countExp(numStack[top - 1], tmp, numStack[top--])), null);
				size --;
			}
			tmp = next;
		}
		if (size != 1)
			throw new RuntimeException("表达式有误");
		return Double.valueOf(numStack[top].value);
	}
	
	/**
	 * 将中缀转后缀
	 * */
	private static Node getPostfixExp(Node head) {
		// 操作符栈
		Node[] oprStack = new Node[3];
		// 指针
		int top = 0;
		Node tmp = head.next;
		Node postfixExp = head;
		postfixExp.next = null;
		while (tmp != null) {
			/*
			 * 1.若为数值,则入队末尾
			 * 2.若为操作符,则与操作符栈中栈顶元素比较优先级
			 *   2.1.若优先级大于栈顶,则入栈
			 *   2.2.若优先级不大于栈顶,则栈顶元素出栈入队列末尾
			 * 3.若为空,则将操作符栈元素出栈
			 * */
			Node next = tmp.next;
			tmp.next = null;
			if (!isOpr(tmp)) {
				// 是数字
				postfixExp.addLast(tmp);
			} else {
				if (comparePriority(oprStack[top], tmp)) {
					oprStack[oprStack[top] == null ? top : ++top] = tmp;
				} else {
					postfixExp.addLast(oprStack[top]);
					oprStack[top] = tmp;
				}
			}
			if (next == null) {
				// 出栈所有的操作符
				while (top >= 0)
					postfixExp.addLast(oprStack[top--]);
			}
			tmp = next;
		}
		return postfixExp;
	}
	
	/**
	 * 将表达式转化为中缀
	 * */
	private static Node getInfixExp(String exp) {
		String[] tmp = exp.split(" ");
		Node result = new Node(tmp[0], null);
		Node tmpN = result;
		for (int i = 1, len = tmp.length; i < len; i++) {
			Node n = new Node(tmp[i], null);
			tmpN.next = n;
			tmpN = tmpN.next;
		}
		return result;
	}
	
	/**
	 * 预先处理表达式,使其符合切割规范
	 * <br>具体为:
	 * <br>1, 没有连续的两个空格
	 * <br>2, 数字和操作符中间有空格
	 * <br>3, "."符号后不能有空格
	 * 
	 * @param exp
	 * @return
	 */
	private static String preDealExp(String exp) {
		// 先去掉所有空格
		StringBuilder tmp = new StringBuilder(exp), newExp = new StringBuilder();
		while (tmp.indexOf(" ") >= 0) tmp.deleteCharAt(tmp.indexOf(" "));
		for (int i = 0; i < tmp.length(); i++) {
			char c;
			if (isOpr(c = tmp.charAt(i))) {
				newExp.append(" " + c + " ");
			} else {
				newExp.append(c);
			}
		}
		return newExp.toString();
	}
	
	
	/**
	 * 计算表达式
	 * @param num1
	 * @param opr
	 * @param num2
	 * @return
	 * @throws IllegalArgumentException 当操作符不在范围内
	 */
	private static double countExp(Node num1, Node opr, Node num2) {
		switch (opr.value) {
			case "+":
				return Double.valueOf(num1.value) + Double.valueOf(num2.value);
			case "-":
				return Double.valueOf(num1.value) - Double.valueOf(num2.value);
			case "*":
				return Double.valueOf(num1.value) * Double.valueOf(num2.value);
			case "/":
				return Double.valueOf(num1.value) / Double.valueOf(num2.value);
			case "^":
				return Math.pow(Double.valueOf(num1.value), Double.valueOf(num2.value));
			default:
				throw new IllegalArgumentException("正确的操作符");
		}
	}
	
	/**
	 * 判断是否为操作符
	 * */
	private static boolean isOpr(Node n) {
		return opr.containsKey(n.value);
	}
	
	/**
	 * 判断是否为操作符
	 * */
	private static boolean isOpr(char c) {
		return opr.containsKey(String.valueOf(c));
	}
	
	/**
	 * 比较操作符优先级
	 * 
	 * @param pre 被比较操作符
	 * @param cur 当前操作符
	 * @return true 大于
	 * 			false 不大于
	 * */
	private static boolean comparePriority(Node pre, Node cur) {
		return pre == null ? true : opr.get(cur.value) > opr.get(pre.value);
	}
	
	private static class Node {
		/** 值 */
		protected String value;
		/** 下节点 */
		protected Node next;
		
		public Node(String value, Node next) {
			this.value = value;
			this.next = next;
		}
		
		/**
		 * 将元素添加到该节点后
		 * @param n
		 */
		public void add(Node n) {
			if (n == null) 
				return;
			this.next = n;
		}
		
		/**
		 * 将元素添加到末尾
		 * @param n
		 */
		public void addLast(Node n) {
			if (n == null) 
				return;
			Node tmp = this;
			while (tmp.next != null)
				tmp = tmp.next;
			tmp.next = n;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		@Override
		public String toString() {
			return next == null ? 
					String.valueOf(value) : value + next.toString();
		}
		
	}
	
	public static void hanoi(int n, char from, char tmp, char to) {
		while (n == 1) {
			System.out.println(n + " : " + from + " -> " + to);
			return;
		}
		hanoi(n - 1, from, to, tmp);
		System.out.println(n + " : " + from + " -> " + to);
		hanoi(n - 1, tmp, from, to);
	}
	
}
