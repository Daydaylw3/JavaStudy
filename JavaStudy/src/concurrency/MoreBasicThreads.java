package concurrency;

/**
 * @ClassName concurrency.MoreBasicThreads
 * @Description 21.2.2 Thread类<p>
 * 你可以很容易的去添加更多的线程去驱动更多的任务
 * 
 * @see concurrency.LiftOff
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
		System.out.println("waiting for liftOff!");
	}
}
