package holding;

import java.util.Arrays;

public class ArrayIsNotIterable {
	static <T> void test(Iterable<T> ib) {
		for(T t : ib)
			System.out.print(t + " ");
	}
	public static void main(String[] args) {
		test(Arrays.asList(1, 2, 3));
		String[] strings = { "a", "b", "c" };
		
		for(String s : strings)
			System.out.print(s + " ");
		//数组可以作用于foreach，但是他不属于Iterable
//		test(strings);
		test(Arrays.asList(strings));
	}
}
