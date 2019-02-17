package concurrency;

/**
 * @ClassName concurrency.BasicThreads
 * @Description 21.2.2 Thread类<p>
 * 将Runnable对象转变为工作任务的传统方式是把它提交给一个Thread构造器
 * 
 * @see concurrency.LiftOff
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class BasicThreads {
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("waiting for liftoff");
	}
}
