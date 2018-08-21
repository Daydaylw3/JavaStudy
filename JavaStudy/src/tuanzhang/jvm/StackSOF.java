package tuanzhang.jvm;

/**
 * eclipse中设置每个线程的栈大小：
 * Preference-->Java-->InstalledJREs-->Edit-->Default VM arguments-->输入-Xss256k
 * 运行时，不断调用doSomething()方法，main线程不断创建栈帧并入栈，导致栈的深度越来越大，最终导致栈溢出。
 * */

public class StackSOF {
	private int stackLength = 1;
	public void doSth() {
		stackLength ++;
		doSth();
	}

	public static void main(String[] args) {
		StackSOF stackSOF=new StackSOF();
		try {
			stackSOF.doSth();
		} catch (Throwable e) {
			System.out.println("栈深度：" + stackSOF.stackLength);
			throw e;
		}
	}

}
