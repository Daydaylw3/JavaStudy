package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/*
 * 17.3 Collection的功能方法
 * */
public class CollectionMethods {
	public static void main(String... args) {
		Collection<String> c;
		c = new ArrayList<String>(Arrays.asList("A B C D E F G".split(" ")));
		c.add("F");
		System.out.println(c);
		Collection b = new ArrayList<String>(Arrays.asList("B C A".split(" ")));
		System.out.println(b);
		c.retainAll(b);
		System.out.println(c);
	}
}
