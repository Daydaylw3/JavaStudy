package com.dayday.util;

import java.util.HashMap;

public class Calculator {
	
	public static void main(String[] args) {
		// 测试
		System.out.println(getPostfixExp(getInfixExp(preDealExp("(a+b)*(c+d)"))));
		System.out.println(getPostfixExp(getInfixExp(preDealExp("a+b*(c+d)"))));
		System.out.println(getPostfixExp(getInfixExp(preDealExp("a+b*(c+d*（e-f）)"))));
		System.out.println(getPostfixExp(getInfixExp(preDealExp("((a+b)*c)*d+e"))));
		System.out.println(countExp("1+4+8+9*10*13/6"));
		System.out.println(countExp("1+2+3+4/7*3"));
		System.out.println(countPostfixExp(getPostfixExp(getInfixExp(preDealExp("10-2*4-2")))));
		System.out.println(countPostfixExp(getPostfixExp(getInfixExp(preDealExp("()10-2*4-2")))));	// )1024*-(2-
	}
	
	private static HashMap<String, Integer> opr = new HashMap<>(8);
	
	static {
		opr.put("(", 5);
		opr.put("^", 4);
		opr.put("*", 3);
		opr.put("/", 3);
		opr.put("÷", 3);
		opr.put("+", 2);
		opr.put("-", 2);
		opr.put(")", 1);
	}
	
	public static double countExp(String exp) {
		return countPostfixExp(getPostfixExp(getInfixExp(preDealExp(exp))));
	}
	
	/**
	 * 计算后缀表达式
	 * */
	public static double countPostfixExp(Node head) {
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
				numStack[top - 1] = new Node(countExp(numStack[top - 1], tmp, numStack[top--]), null);
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
	public static Node getPostfixExp(Node head) {
		// 操作符栈
		Node[] oprStack = new Node[10];
		// 指针
		int top = 0;
		Node tmp = head;
		Node postfixExp = new Node("head", null);
		while (tmp != null) {
			/*
			 * 1.若c为数值,则入队末尾
			 * 2.若c为操作符
			 * 	2.1.c为"(",入栈
			 * 	2.2.c优先级高于栈顶元素,入栈
			 * 	2.3.c为")",则将操作符出栈入队列至最顶的"(",且"("和")"不入队列
			 * 	2.2.c不为")",则将操作符栈顶依次出栈入队列至栈顶元素优先级小于c,或者栈顶元素为"(",
			 * 		然后将c入栈
			 * */
			Node next = tmp.next;
			tmp.next = null;
			if (!isOpr(tmp)) {
				postfixExp.addLast(tmp);
			} else if (isRightParenthesis(tmp)) {
				while (!isLeftParenthesis(oprStack[top])) {
					postfixExp.addLast(oprStack[top]);
					oprStack[top--] = null;
				}
				oprStack[top] = null;
				if (top > 0) top--;
			} else if (isLeftParenthesis(oprStack[top]) || isUpperPriority(oprStack[top], tmp)) {
				oprStack[oprStack[top] == null ? top : ++top] = tmp;
			} else {
				while (top >= 0 && !isLeftParenthesis(tmp) && isNotUpperPriority(oprStack[top], tmp)) {
					postfixExp.addLast(oprStack[top]);
					oprStack[top--] = null;
				}
				oprStack[++top] = tmp;
			}
			tmp = next;
		}
		while (top >= 0)
			postfixExp.addLast(oprStack[top--]);
		return postfixExp.next;
	}
	
	/**
	 * 将表达式转化为中缀
	 * */
	public static Node getInfixExp(String exp) {
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
	 * <br>4, 没有中文的小括号
	 * 
	 * @param exp
	 * @return
	 */
	public static String preDealExp(String exp) {
		// 先去掉所有空格
		StringBuilder tmp = new StringBuilder(exp), newExp = new StringBuilder();
		while (tmp.indexOf(" ") >= 0) tmp.deleteCharAt(tmp.indexOf(" "));
		while (tmp.indexOf("（") >= 0) tmp.replace(tmp.indexOf("（"), tmp.indexOf("（") + 1, "(");
		while (tmp.indexOf("）") >= 0) tmp.replace(tmp.indexOf("）"), tmp.indexOf("）") + 1, ")");
		for (int i = 0; i < tmp.length(); i++) {
			char c;
			if (isOpr(c = tmp.charAt(i))) {
				newExp.append(" " + c + " ");
			} else {
				newExp.append(c);
			}
		}
		while (newExp.indexOf("  ") >= 0) newExp.replace(newExp.indexOf("  "), newExp.indexOf("  ") + 2, " ");
		return newExp.toString().trim();
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
			case "÷":
				return Double.valueOf(num1.value) / Double.valueOf(num2.value);
			case "^":
				return Math.pow(Double.valueOf(num1.value), Double.valueOf(num2.value));
			default:
				throw new IllegalArgumentException("不正确的操作符");
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
	 * 判断是否为左括号
	 * @param n
	 * @return
	 */
	private static boolean isLeftParenthesis(Node n) {
		if (n == null) return false;
		return "(".equals(n.value);
	}
	
	/**
	 * 判断是否为左括号
	 * @param n
	 * @return
	 */
	private static boolean isLeftParenthesis(char c) {
		return "(".equals(String.valueOf(c));
	}
	
	/**
	 * 判断是否为右括号
	 * @param n
	 * @return
	 */
	private static boolean isRightParenthesis(Node n) {
		return ")".equals(n.value);
	}
	
	/**
	 * 判断是否为右括号
	 * @param n
	 * @return
	 */
	private static boolean isRightParenthesis(char c) {
		return ")".equals(String.valueOf(c));
	}
	
	/**
	 * <code>cur</code> > <code>pre</code> ?
	 * 
	 * @param pre 被比较操作符
	 * @param cur 当前操作符
	 * @return true 高于
	 * 			false 不高于
	 * */
	private static boolean isUpperPriority(Node pre, Node cur) {
		return pre == null ? true : opr.get(cur.value) > opr.get(pre.value);
	}
	
	/**
	 * <code>pre</code> >= <code>cur</code> ?
	 * 
	 * @param pre
	 * @param cur
	 * @return true 是
	 * 			false 否
	 */
	private static boolean isNotUpperPriority(Node pre, Node cur) {
		return pre == null ? false : opr.get(pre.value) >= opr.get(cur.value);
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
		
		public Node(double value, Node next) {
			this.value = String.valueOf(value);
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
