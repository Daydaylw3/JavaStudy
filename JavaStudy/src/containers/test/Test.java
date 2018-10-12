package containers.test;
/*
 * 17.10.1 性能测试框架
 * */
public abstract class Test<C> {
	String name;
	public Test(String name) { this.name = name; }
	//Override this method for different tests.
	//Returns actual number of repetitions of test.
	abstract int test(C container, TestParam tp);
}
