package concurrency;

/**
 * @ClassName concurrency.Exercise2
 * @Description 遵循generic/Fibonaci.java的形式, 创建一个任务, 它可以
 * 产生由n个斐波纳契数字组成的序列, 其中n是通过任务的构造器而提供的. 使用线
 * 程创建大量的这种任务并驱动它们.
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class Exercise2 {
	public static void main(String[] args) {
		for (int i = 0; i < 18; i++) {
			new Thread(new ConcurrencyFibonaci(i)).start();
		}
	}
}

class ConcurrencyFibonaci implements Runnable {
	private int n;
	
	public ConcurrencyFibonaci(int n) {
		this.n = n;
	}
	
	private int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			System.out.print("fib#" + n + "." + i + " = " + fib(i) + ", ");
		}
		System.out.println("fib#" + n + " = " + fib(n) + ", ");
	}
}
