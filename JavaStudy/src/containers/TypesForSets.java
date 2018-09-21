package containers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * 17.6 Set和存储顺序
 * */
class SetType {
	int i;
	public SetType(int n) { i = n; }
	//所有在Set中存储的类都必须具有equals()方法
	@Override
	public boolean equals(Object o) {
		return o instanceof SetType && (i == ((SetType)o).i);
	}
	@Override
	public String toString() { return Integer.toString(i); }
}
class HashType extends SetType {
	public HashType(int n) {
		super(n);
	}
	//此方法对于放置到Set的散列实现中的对象来说是必需的
	public int hashCode() { return i; }
}
//如果一个对象被用于任何种类的排序容器中，那么必须实现Comparable接口
class TreeType extends SetType implements Comparable<TreeType>{
	public TreeType(int n) { super(n); }

	@Override
	public int compareTo(TreeType arg) {
		return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
	}
}
public class TypesForSets {
	static <T> Set<T> fill(Set<T> set, Class<T> type){
		try {
			for(int i = 0; i < 10; i++) {
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);	// try to add duplicates
		fill(set, type);
		System.out.println(set);
	}
	public static void main(String[] args) {
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);
		// things that dont work
		test(new HashSet<SetType>(), SetType.class);
		test(new HashSet<TreeType>(), TreeType.class);
		test(new LinkedHashSet<SetType>(), SetType.class);
		test(new LinkedHashSet<TreeType>(), TreeType.class);
		try {
			test(new TreeSet<SetType>(), SetType.class);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<HashType>(), HashType.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	 * Output: 
	 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
	 * [1, 4, 3, 1, 9, 4, 5, 7, 5, 2, 3, 4, 0, 6, 8, 9, 0, 5, 8, 6, 1, 8, 7, 3, 2, 2, 0, 7, 9, 6]
	 * [2, 7, 3, 4, 6, 2, 9, 6, 5, 7, 8, 4, 9, 3, 5, 3, 0, 1, 1, 8, 6, 5, 7, 2, 0, 9, 1, 8, 0, 4]
	 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * java.lang.ClassCastException: containers.SetType cannot be cast to java.lang.Comparable
	 * java.lang.ClassCastException: containers.HashType cannot be cast to java.lang.Comparable
	 * 
	 * 从输出中可以看出：HashSet以某种神秘的顺序保存所有的元素；LinkedHashSet按照元素
	 * 插入的顺序保存元素；TreeSet按照排序顺序维护元素；
	 * */
}
