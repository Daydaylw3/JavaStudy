package concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName concurrency.Exercise5
 * @Description 修改练习2, 使得计算所有斐波那契数字的值的总和的任务成为Callable. 创建
 * 多个任务并显示结果
 * 
 * @author daydaylw3
 * @date Feb 12, 2019
 */
public class Exercise5 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		for (int i = 0; i < 18; i++) {
			results.add(exec.submit(new CallableFibonaci(i)));
		}
		for (Future<Integer> fi : results) {
			try {
				System.out.println(fi.get());
			} catch (InterruptedException ex) {
				System.out.println(ex);
				return ;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
		}
	}
	
}

class CallableFibonaci implements Callable<Integer> {
	private int n;
	
	public CallableFibonaci(int n) {
		this.n = n;
	}
	
	private int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	@Override
	public Integer call() {
		int result = 0;
		for (int i = 0; i < n; i++) {
			result += fib(i);
		}
		return result;
	}
}