package com.dayday.util;

/**
 * 可以为任何类构造一个Generator
 * 这个类必须具备两个特点：
 * 1）必须声明为public
 * 2）必须具备默认的构造器
 * */
public class BasicGenerator<T> implements Generator<T> {
	private Class<T> clazz;
	private BasicGenerator(Class<T> clazz) { this.clazz = clazz; }
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
	 * 输入：想要生成的类型
	 * 输出：BasicGenerator对象
	 * */
	public static <T> Generator<T> create(Class<T> clazz) {
		return new BasicGenerator<T>(clazz);
	}

}
