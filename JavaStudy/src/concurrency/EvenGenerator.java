package concurrency;

/**
 * @ClassName concurrency.EvenGenerator
 * @Description 21.3.1 不正确地访问资源</br>
 * 一个任务有可能在另一个任务执行第一个对currentEvenValue的递增操作
 * 之后, 但是没有执行第二个操作之前调用next()方法. 这个值将处于一个"不
 * 恰当"状态
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	@Override
	public int next() {
		++currentEvenValue;	// Danger point
		Thread.yield();				// 加速了错误的产生
		++currentEvenValue;
//		System.out.println(currentEvenValue);
		return currentEvenValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
