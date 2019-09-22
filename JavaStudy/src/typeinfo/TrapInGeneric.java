package typeinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName typeinfo.TrapInGeneric.java
 * @Description 说是泛型中的陷阱，其实是Java集合、数组
 * 与泛型中的陷阱
 * 
 * @author dayday
 * @date 2019年3月29日
 */
public class TrapInGeneric {
	public static void main(String[] args) {
//		List<Integer> t1 = new ArrayList<>();
//		// 编译通过
//		List t2 = t1;
//		// 编译失败
//		List<Object> t3 = t1;
		
//		List<Object> t1 = new ArrayList<>();
//		List<?> t2 = t1;
//		// 编译通过
//		t2.remove(0);
//		t2.clear();
//		// 编译不通过
//		t2.add(new Object());
		
//		List<Animal> animals = new ArrayList<>();
//		List<Cat> cats = new ArrayList<>();
//		List<RedCat> redCats = new ArrayList<>();
//		// 编译通过
//		List<? extends Cat> extendsCat = redCats;
//		// 编译不通过
//		extendsCat = animals;
//		// 编译不通过
//		extendsCat.add(new Animal());
//		extendsCat.add(new Cat());
//		extendsCat.add(new RedCat());
//		// 编译通过
//		extendsCat.add(null);
		
//		List<Animal> animals = new ArrayList<>();
//		List<Cat> cats = new ArrayList<>();
//		List<RedCat> redCats = new ArrayList<>();
//		// 编译通过
//		List<? super Cat> superCat = animals;
//		// 编译不通过，因为只能接受Cat及其父类的集合
//		superCat = redCats;
//		// 编译不通过，只能添加Cat及其Cat的子类
//		superCat.add(new Animal());
//		// 编译通过
//		superCat.add(new Cat());
//		superCat.add(new RedCat());
//		superCat.add(null);
		
//		String[] arr = {"one", "two", "three",};
//		List<String> list2 = Arrays.asList(arr);
////		list.add("four");
//		arr[0] = "four";
//		System.out.println(list2.get(0));
//		
//		List<String> list = new ArrayList<>(Arrays.asList(arr));
		
		// 此时list.size() == 0
		List<String> list = new ArrayList<>();
		String[] arr = new String[1], arrBak = arr;
		System.out.println(list.size());
		System.out.println(list.size() > arr.length);
		list.toArray(arr);
		System.out.println(arr);
	}
}

class Animal {
	
}
class Cat extends Animal {
	
}
class RedCat extends Cat {
	
}