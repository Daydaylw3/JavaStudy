package generics;

import java.util.ArrayList;
import java.util.List;

/*
 * 从此例中可以看出来，即使编译期已经将
 * <T>擦除，但是编译器还是可以保证在编
 * 译期确保你放置到result中的对象具有T类
 * 型
 * */
public class FilledListMaker<T> {
	List<T> create(T t, int n){
		List<T> result = new ArrayList<T>();
		for(int i = 0; i < n; i++)
			result.add(t);
		return result;
	}
	public static void main(String[] args) {
		FilledListMaker<String> stringMaker = 
				new FilledListMaker<String>();
		List<String> list = stringMaker.create("helo", 4);
		System.out.println(list);
	}

}
