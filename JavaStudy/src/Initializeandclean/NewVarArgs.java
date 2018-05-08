package Initializeandclean;

public class NewVarArgs {
	static void printArray(Object... args) {
		for(Object obj : args)
			System.out.print(obj + " ");
		System.out.println();
	}
	public static void main(String[] args) {
		//可以放置单个元素
		printArray(new Integer(47), new Float(3.14), new Double(11.11));
		printArray(47, 3.14F, 11.11);
		printArray("one", "two", "three");
		printArray(new A(), new A(), new A());
		//或者传入一个数组
		printArray((Object[])new Integer[] {1,2,3,4,5});
		printArray();	//空数组也是可以的
	}

}
