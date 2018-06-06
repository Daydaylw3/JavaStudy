package com.dayday.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 一个工具类
 * 用来创建各种常用的容器对象
 * */
public class New {
	public static <K, V> Map<K, V> map() {
		return new HashMap<K, V>();
	}
	public static <T> List<T> list() {
		return new ArrayList<T>();
	}
	public static <T> LinkedList<T> lList() {
		return new LinkedList<T>();
	}
	public static <T> Set<T> set() {
		return new HashSet<T>();
	}
	public static <T> Queue<T> queue() {
		return new LinkedList<T>();
	}
}
