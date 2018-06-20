package fortest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWildcardCharacter {
	public static void main(String[] args) {
		//1、以"?"声明的集合，不能往此集合中添加元素，所以他只能作为生产者（只能被迭代）
		List<?> list1 = Arrays.asList("helo");
		//通配符声明的集合，获取的元素都是Object类型
		List<Object> list2 = new ArrayList<Object>();
		list2.addAll(list1);
		// 只能以Object迭代元素
		for(Object name : list2)
			System.out.println(name);
		//2、以"? extends T"声明的集合，不能往此集合中添加元素，所以他只能作为生产者（只能被迭代）
		//相比于"?"声明，"? extends T"声明能更轻松地迭代元素
		List<? extends String> list3 = Arrays.asList("helo");
		List<String> list4 = new ArrayList<String>();
		list4.addAll(list3);
		// 能更精确地确认元素类型
		for(String s : list4)
			System.out.println(s);
		//3、以"? super T"声明的集合，能添加元素，所以他能作为消费者（消费其他通配符集合）
		List<? super String> list5 = new ArrayList();
		List<String> list6 = Arrays.asList("helo");
		//可以直接添加泛型元素
		list5.addAll(list6);
		List<? extends String> list7 = Arrays.asList("helo");
		//也可以直接添加通配符泛型元素
		list5.addAll(list7);
		//只能获取到Object元素
		for(Object s : list5)
			System.out.println(s);
	}
}
