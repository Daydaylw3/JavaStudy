package concurrency;

/**
 * @ClassName concurrency.MainThread
 * @Description 21.2.1 定义任务
 * 
 * @see concurrency.LiftOff
 * 
 * @author daydaylw3
 * @date Feb 11, 2019
 */
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}
}
