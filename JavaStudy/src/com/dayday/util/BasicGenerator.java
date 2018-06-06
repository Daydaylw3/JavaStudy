package com.dayday.util;

/**
 * 可以为任何类构造一个Generator
 * 该类要有默认的构造器
 * */
public class BasicGenerator<T> implements Generator<T> {
	private Class<T> clazz;
	public BasicGenerator(Class<T> clazz) { this.clazz = clazz; }
	@Override
	public T next() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据传入的Class产生一个BasicGenerator对象
	 * */
	public static <T> Generator<T> create(Class<T> clazz) {
		return new BasicGenerator<T>(clazz);
	}

}
