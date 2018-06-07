package com.dayday.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ContainerMethodDifferences {
	/**
	 * 返回给定类型的所有公有方法
	 * */
	static Set<String> methodSet(Class<?> clazz){
		Set<String> result = new TreeSet<String>();
		for(Method m : clazz.getMethods())
			result.add(m.getName());
		return result;
	}
	
	static void interfaces(Class<?> clazz) {
		System.out.print("Interfaces in " + clazz.getSimpleName());
		List<String> result = new ArrayList<String>();
		for(Class<?> c : clazz.getInterfaces())
			result.add(c.getSimpleName());
		System.out.println(result);
	}
	
	static Set<String> object = methodSet(Object.class);
	static { object.add("clone"); }
	static void difference(Class<?> superset, Class<?> subset) {
		System.out.print(superset.getSimpleName() +
				" extends " + subset.getSimpleName() + ", adds: ");
		Set<String>comp = Sets.difference(
				methodSet(superset), methodSet(subset));
		comp.removeAll(object);	//不显示'Object'方法
		System.out.println(comp);
		interfaces(superset);
	}
	public static void main(String[] args) {
		System.out.println(methodSet(Collection.class));
		interfaces(Collection.class);
		difference(Set.class, Collection.class);
	}
}
