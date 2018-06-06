package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 可变参数列表
 * */
public class GenericVarargs {
	static <T> List<T> makeList(T... args) {
		List<T> list = new ArrayList<T>();
		for(T t : args)
			list.add(t);
		return list;
	}
	public static void main(String[] args) {
		List<String> ls = makeList("A");
		System.out.println(ls);
		ls = makeList("a beautiful girl is in my heart".split(" "));
		System.out.println(ls);
	}
}
