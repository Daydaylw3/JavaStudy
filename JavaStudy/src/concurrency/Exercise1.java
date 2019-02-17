package concurrency;

/**
 * @ClassName concurrency.Exercise1
 * @Description 练习1: 实现一个Runnable. 在run()内部打印一个消息, 然后调用yield(). 
 * 重复这个操作三次, 然后从run()中返回, 在构造器中放置一条启动消息, 并且放置一条在任务终止
 * 时的关闭消息. 使用线程创建大量的这种任务并驱动它们
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class Exercise1 {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new Thread(new ConcurrencyExerc1()).start();
		}
	}
}

class ConcurrencyExerc1 implements Runnable {
	public static int count = 0;
	private int id = count++;
	
	public ConcurrencyExerc1() {
		System.out.print("new c#" + id + ", ");
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.print("c#" + id + " " + i + ", ");
			Thread.yield();
		}
		System.out.print("c#" + id + " done. ");
	}
}
/*
 * new c#0, new c#1, c#0 0, new c#2, c#0 1, c#1 0, new c#3, 
 * c#2 0, new c#4, c#3 0, c#0 2, new c#5, c#4 0, c#1 1, new c#6, 
 * c#5 0, c#6 0, new c#7, new c#8, c#7 0, new c#9, c#2 1, new c#10, 
 * c#8 0, new c#11, c#9 0, c#10 0, new c#12, new c#13, c#11 0, c#12 0, 
 * new c#14, new c#15, c#3 1, c#14 0, c#0 done. c#13 0, c#4 1, c#1 2, 
 * new c#16, c#5 1, c#15 0, c#6 1, new c#17, c#7 1, c#16 0, c#2 2, new c#18, 
 * c#17 0, new c#19, c#18 0, c#8 1, c#9 1, c#10 1, c#19 0, c#3 2, c#12 1, c#11 1, 
 * c#13 1, c#14 1, c#1 done. c#4 2, c#5 2, c#15 1, c#7 2, c#17 1, c#2 done. c#8 2, 
 * c#16 1, c#6 2, c#18 1, c#9 2, c#10 2, c#3 done. c#19 1, c#11 2, c#12 2, c#13 2, 
 * c#4 done. c#14 2, c#5 done. c#15 2, c#7 done. c#8 done. c#17 2, c#16 2, c#6 done. 
 * c#18 2, c#19 2, c#9 done. c#13 done. c#10 done. c#12 done. c#11 done. c#15 done. 
 * c#14 done. c#17 done. c#16 done. c#18 done. c#19 done. 
 * */