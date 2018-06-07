package com.dayday.util;

import java.util.HashSet;
import java.util.Set;

public class Sets {
	/**
	 * 将两个参数合并
	 * */
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}
	
	/**
	 * 只包含两个参数共有的部分
	 * */
	public static <T> Set<T> intersection(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}
	
	/**
	 * 从superset中移除subset包含的元素
	 * */
	public static <T> Set<T> difference(Set<T> superset, Set<T> subset){
		Set<T> result = new HashSet<T>(superset);
		result.removeAll(subset);
		return result;
	}
	
	/**
	 * 包含了除了交集之外的所有元素
	 * */
	public static <T> Set<T> complement(Set<T> a, Set<T> b){
		return difference(union(a, b), intersection(a, b));
	}
}
