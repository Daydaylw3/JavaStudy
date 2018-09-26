package com.dayday.util;
/*
 * 17.7.2 双向队列
 * */

import java.util.LinkedList;

public class Deque<T> {
	private LinkedList<T> deque = new LinkedList<T>();
	/**
	 * 往队列头添加元素
	 * */
	public void addFirst(T e) { deque.addFirst(e); }
	/**
	 * 往队列尾添加元素
	 * */
	public void addLast(T e) { deque.addLast(e); }
	/**
	 * 返回队列头元素
	 * 不删除元素
	 * */
	public T getFirst() { return deque.getFirst(); }
	/**
	 * 返回队列尾元素
	 * 不删除元素
	 * */
	public T getLast() { return deque.getLast(); }
	/**
	 * 删除并返回队列头元素
	 * */
	public T removeFirst() { return deque.removeFirst(); }
	/**
	 * 删除并返回队列尾元素
	 * */
	public T removeLast() { return deque.removeLast(); }
	/**
	 * 返回队列容量大小
	 * */
	public int size() { return deque.size(); }
	
	@Override
	public String toString() {
		return deque.toString();
	}
}
