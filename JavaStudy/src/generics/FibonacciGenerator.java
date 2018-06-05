package generics;

import com.dayday.util.Generator;

/**
 * 斐波那契数生成器
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数列系数就由之前的两数相加
 * */
public class FibonacciGenerator implements Generator<Integer> {
	private int count = 0;
	
	@Override
	public Integer next() {
		return fib(count++);
	}
	private int fib(int n) {
		if(n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	public static void main(String[] args) {
		FibonacciGenerator gen = new FibonacciGenerator();
		for(int i = 0; i < 18; i ++)
			System.out.print(gen.next() + " ");
	}
}
