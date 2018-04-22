package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Thread是一个Runnable接口的实现类，被我们使用的时候抽象成一个执行具体任务的线程
 * 我们去实现Runnable的类被我们使用的时候抽象成一个具体的任务
 * 我们继承Thread类，是为了给线程增添更多的属性
 * 我们实现Runnable方法，是为了定义一个任务所要完成的工作
 * Thread类中有start方法，可以启动一个线程，执行run()方法之中的操作
 * Runnable接口只有一个run()方法，实现了Runnable接口创建的线程，也要依托在Thread的对象上，调用Thread的start()方法，才能启动线程
 * */
public class Thread4Study {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i ++) {
			exec.execute(new Run(i));
		}
		exec.shutdown();
	}

}

class Run implements Runnable {
	
	private int id ;
	
	public Run(int id) {
		this.id = id;
		System.out.println(id + " started");
	}
	
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.println(id + " running");
			Thread.yield();
		}
		System.out.println(id + " end");
	}
}
