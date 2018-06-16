package generics;

/*
 * 考虑一个问题，所有的数组无论它持有什么类型，都具有相同的
 * 结构，那么看起来应该可以创建一个Object数组，并将其转型为
 * 所希望的数组类型
 * */
public class ArrayOfGeneric {
	static final int SIZE = 100;
	static Generic<Integer>[] gia;
	public static void main(String[] args) {
		//运行时出错: ClassCaseException
//		gia = (Generic<Integer>[])new Object[SIZE];
		gia = (Generic<Integer>[])new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());
		gia[0] = new Generic<Integer>(new Integer(0));
		//不能编译
//		gia[1] = new Object();
//		gia[2] = new Generic<Double>(new Double(2));
	}
}
/*
 * 数组将跟踪他们的实际类型，而这个类型是在数组被创建时确定的
 * 即使gia已经被转型为Generic<Integer>[]， 但是这个信息只存在于
 * 编译期。在运行时，它仍旧是Object数组
 * 
 * 成功创建泛型数组的唯一方式就是创建一个被擦除类型的新数组， 
 * 然后对其转型
 * T[] t = (T[])new Object[length];
 * */
