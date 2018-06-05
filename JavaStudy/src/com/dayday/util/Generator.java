package com.dayday.util;

/**
 * 泛型可以应用于接口
 * 这是一种专门负责创建对象的类
 * 当使用生成器创建对象的时候，他不需要任何参数，也就是
 * 说，生成器无需额外的信息就知道如何创建新的对象
 * 一般而言，生成器只定义一个方法，该方法用以产生新的对象
 * */
public interface Generator<T> {
	T next();
}
