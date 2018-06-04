package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 假设我们需要一个持有特定类型对象的列表，每次调用其上的select()方法
 * 时，可以随机的选取一个元素，如果希望以此构建一个可以应用于各种类型的
 * 对象的工具，就需要用到泛型
 * */
public class RandomList<T> {
	List<T> storage = new ArrayList<T>();
	Random rand = new Random(47);
	public void add(T t) { storage.add(t); }
	public T select() { return storage.get(rand.nextInt(storage.size())); }
	public static void main(String[] args) {
		RandomList<String> randList = new RandomList<String>();
		for(String s : ("the quick brown fox jumped over " + 
				"the lazy brown dog").split(" "))
			randList.add(s);
		
		for(int i = 0; i < 11; i++)
			System.out.print(randList.select() + " ");
	}

}
