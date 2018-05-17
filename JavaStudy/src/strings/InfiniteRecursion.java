package strings;

public class InfiniteRecursion {
	public String toString() {
		/*
		 * 这里发生了自动类型转换，由InfiniteRecursion类型转换成String类型
		 * 因为编译器看到一个String对象后面跟着一个“+”，而后面跟着的对象不是String类型，所以尝试
		 * 着将this转换成一个String；转换方法是调用this上的toString()方法，于是就发生了递归调用
		 * */
		return " InfiniteRecursion address: " + this + "\n";
	}
	public static void main(String[] args) {
		//这里会得到一串非常长的异常
		System.out.println(new InfiniteRecursion());
	}
}
