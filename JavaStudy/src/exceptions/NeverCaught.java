package exceptions;

//ignoring runtimeexceptions
/*
 * runtimeexception（或者从他继承的任何异常）是一个特例，对于这种
 * 类型的异常，编译器不需要异常说明，其输出已经被报告给了System.err
 * */
public class NeverCaught {

	static void f() {
		throw new RuntimeException("from f()");
	}
	static void g() {
		f();
	}
	public static void main(String[] args) {
		g();
	}

}
