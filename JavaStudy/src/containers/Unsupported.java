package containers;
/*
 * 17.4.1 未获支持的操作
 * */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Unsupported {
	public static void test(String str, List<String> list) {
		System.out.println(" --- " + str + " --- ");
		Collection<String> c = list;
		Collection<String> sublist = list.subList(1, 3);
//		copy of the sublist
		Collection<String> c2 = new ArrayList<String>(sublist);
		try {
			c.retainAll(c2);
		}catch(Exception e) {
			System.out.println("retainAll(): " + e);
		}
		try {
			c.removeAll(c2);
		}catch(Exception e) {
			System.out.println("removeAll(): " + e);
		}
		try {
			c.clear();
		}catch(Exception e) {
			System.out.println("clear(): " + e);
		}
		try {
			c.add("x");
		}catch(Exception e) {
			System.out.println("add(): " + e);
		}
		try {
			c.remove("C");
		}catch(Exception e) {
			System.out.println("remove(): " + e);
		}
		try {
			list.set(0, "X");
		}catch(Exception e) {
			System.out.println("list.set(): " + e);
		}
	}
	public static void main(String... args) {
		List<String> list = Arrays.asList("A B C D E F G H I J K".split(" "));
//		应该把Arrays.asList()的结果作为构造器的参数传递给任何Collection，或者使用addAll()方法，或者
//		Collections.addAll()静态方法，这样可以生成允许使用所有方法的普通容器
		test("new ArrayList<String>()", new ArrayList<String>(list));
//		Arrays.asList() 底层产生的是固定尺寸的数组
		test("Arrays.asList()", list);
//		无法被修改
		test("unmodifiableList", Collections.unmodifiableList(new ArrayList<String>(list)));
	}
}
