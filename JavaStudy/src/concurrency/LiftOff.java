package concurrency;

/**
 * @ClassName concurrency.LiftOff
 * @Description 21.2.1 定义任务
 * 
 * @see concurrency.MainThread
 * @see concurrency.BasicThreads
 * @see concurrency.MoreBasicThreads
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class LiftOff implements Runnable {
	protected int countDown = 10;	// default
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff() {
		
	}
	
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
	}
	
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}
}
