package generics;

import java.util.Iterator;

/**
 * 想要一个实现了Iterable的Fibonacci生成器
 * 如果不去重写FibonacciGenerator.java，我
 * 们可以选择创建一个适配器，来实现所需的接口
 * */
public class IterableFibonacci extends FibonacciGenerator implements Iterable<Integer>{
	private int size;
	public IterableFibonacci(int size) {
		this.size = size;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				return size > 0;
			}

			@Override
			public Integer next() {
				size --;
				return IterableFibonacci.this.next();
			}
		};
	}
	
	public static void main(String[] args) {
		for(int i : new IterableFibonacci(18))
			System.out.print(i + " ");
		System.out.println();
		for(int i : new IterableFibonacci2(18))
			System.out.print(i + " ");
	}
}

/**
 * 用组合代替继承实现 适配器
 * */
class IterableFibonacci2 implements Iterable<Integer>{
	private int size;
	private FibonacciGenerator fibonacciGen;
	public IterableFibonacci2(int size) {
		this.size = size;
		fibonacciGen = new FibonacciGenerator();
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return size > 0;
			}

			@Override
			public Integer next() {
				size --;
				return fibonacciGen.next();
			}
			
		};
	}
	
}
