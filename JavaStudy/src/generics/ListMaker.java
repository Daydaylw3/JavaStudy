package generics;

import java.util.ArrayList;
import java.util.List;

/*
 * 如果我们要创建一个容器，而不是数组，则
 * 就有所不同（对比ArrayMaker.java）
 * 编译器不会给出任何警告，尽管我们知道在
 * create()内部的new ArrayList<T>中的<T>被
 * 移除了——在运行时，这个类的内部没有任何
 * <T>。但是如果将这个表达式改成
 * new ArrayList()，编译器就会给出警告
 * */
public class ListMaker<T> {
	List<T> create() { return new ArrayList<T>(); }
	List<T> create2() { return new ArrayList(); }
	public static void main(String[] args) {
		ListMaker<String> listMaker = new ListMaker<String>();
		List<String> listString = listMaker.create();
		listString = listMaker.create2();
	}
}
