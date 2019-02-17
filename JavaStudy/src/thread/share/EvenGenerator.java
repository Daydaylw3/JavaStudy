package thread.share;

/**
 * 一个任务有可能在另一个任务执行第一个对currentEvenValue的递增操作之后，但是没有执行第二个操作之前
 * 调用next()方法。这个值将处于一个“不恰当”状态。
 * */
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	@Override
	public int next() {
		++currentEvenValue;	//Danger point
		++currentEvenValue;
//		System.out.println(currentEvenValue);
		return currentEvenValue;
	}
	public static void main(String[] args) {
		
		EvenChecker.test(new EvenGenerator());
	}
}
