package com.dayday.util;
/*
 * 17.2.3 使用Abstract类
 * */
import java.util.AbstractList;
import java.util.ArrayList;

public class CountingIntegerList extends AbstractList<Integer>{
	private int size;
	private int[] array;
	public CountingIntegerList(int size) {
		this.size = size < 0 ? 0 : size;
		array = new int[size];
	}
	
	@Override
	public Integer get(int index) {
		return Integer.valueOf(index);
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean add(Integer i) {
		size ++;
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(new CountingIntegerList(30).toString());
		System.out.println(new CountingIntegerList(30).add(30));
		System.out.println(new ArrayList<Integer>(30).add(30));
	}
}
