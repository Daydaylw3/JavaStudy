package containers;
/*
 * 17.8.3 LinkedHashMap
 * LinkedHashMap是以键值的插入顺序进行遍历的，但是还
 * 可以在构造器中设定LinkedHashMap，使之采用LRU算法
 * */
import java.util.LinkedHashMap;

public class LinkedHashMapDemo {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> linkedMap = 
				new LinkedHashMap<Integer, String>(10);
		for(int i = 0; i < 10; i++)
			linkedMap.put(i, Character.toString((char)('A' + i)) + i);
		System.out.println(" ----- 未指定使用LRU算法 -----\n" + linkedMap);
		for(int i = 0; i < linkedMap.size() - linkedMap.size() / 2; i ++)
			linkedMap.get(i);
		System.out.println(" ----- 访问过0-" + (linkedMap.size() - linkedMap.size() / 2 - 1) + 
				"位元素后 -----\n" + linkedMap);
		linkedMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
		for(int i = 0; i < 10; i++)
			linkedMap.put(i, Character.toString((char)('A' + i)) + i);
		System.out.println(" ----- 指定使用LRU算法 -----\n" + linkedMap);
		for(int i = 0; i < linkedMap.size() - linkedMap.size() / 2; i ++)
			linkedMap.get(i);
		System.out.println(" ----- 访问过0-" + (linkedMap.size() - linkedMap.size() / 2 - 1) + 
				"位元素后 -----\n" + linkedMap);
		linkedMap.get(0);
		System.out.println(" ----- 访问过0号元素后 -----\n" + linkedMap);
	}
}
