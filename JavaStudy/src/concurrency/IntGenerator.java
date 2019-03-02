package concurrency;

/**
 * @ClassName concurrency.IntGenerator
 * @Description 21.3.1 不正确地访问资源
 * 
 * @see concurrency.EvenChecker
 * @see concurrency.EvenGenerator
 * @see concurrency.SynchronizedEvenGenerator
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel() {
		canceled = true;
	}
	public boolean isCanceled() {
		return canceled;
	}
}
