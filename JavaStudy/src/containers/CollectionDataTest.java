package containers;
/*
 * 17.2.1 初始化LinkedHashSet的一个示例
 * */

import java.util.LinkedHashSet;
import java.util.Set;

import com.dayday.util.CollectionData;
import com.dayday.util.Generator;

class Government implements Generator<String>{
	String[] foundation = ("strange women lying in ponds").split(" ");
	private int index;
	@Override
	public String next() {
		return foundation[index++];
	}
	
}

public class CollectionDataTest {

	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<String>(
				new CollectionData<String>(new Government(), 5));
		System.out.println(set);
		set.addAll(CollectionData.list(new Government(), 5));
		System.out.println(set);
	}

}
