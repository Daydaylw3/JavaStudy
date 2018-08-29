package com.dayday.util;
/*
 * 17.2.1 一种Generator解决方案
 * 
 * CollectionData是适配器设计模式的一个实例，它将Generator适配到Collection的构造器上
 * */

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> gen, int quantity) {
		for(int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}
	
	public static <T> CollectionData<T>
	list(Generator<T> gen, int quantity){
		return new CollectionData<T>(gen, quantity);
	}
}
