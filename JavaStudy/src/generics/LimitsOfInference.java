package generics;

import java.util.List;
import java.util.Map;

import com.dayday.util.New;

/*
 * 类型推断只对赋值操作有效，其他时候
 * 并不起作用。——看上去并不是这样的
 * jdk8可以根据方法的声明来推断泛型的
 * 类型
 * */
public class LimitsOfInference {
	static void f(Map<Number, List<? extends Number>> test) {}
	static void f(List<? extends Number> test) {
		System.out.println(test.get(0).getClass());
	}
	public static void main(String[] args) {
		Map map = New.map();
		map.put("String", "value");
		f(map);
		f(New.map());
		List list = New.list();
		list.add(1);
		list.add("String");
		f(list);
		List<String> listString = New.list();
//		can't compile
//		f(listString);
	}
}
