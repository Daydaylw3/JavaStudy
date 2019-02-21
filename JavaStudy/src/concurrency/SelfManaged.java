package concurrency;

/**
 * @ClassName concurrency.SelfManaged
 * @Description 21.2.9 编码的变体<p>
 * 
 * @author dayday
 * @date 2019年2月20日
 */
public class SelfManaged implements Runnable {
	
	private int countDown = 5;
	private Thread t = new Thread(this);
	
	/*
	 * 注意，start()是在构造器中调用的。应该意识到，在构
	 * 造器中启动线程可能会变得很有问题，因为另一个任务可
	 * 能会在构造器结束之前开始执行，这意味着该任务能够访
	 * 问处于不稳定状态的对象。这是优选Executor而不是显
	 * 式地创建Thread对象的另一个原因。
	 * */
	public SelfManaged() {
		t.start();
	}
	
	@Override
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + ") ";
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.print(this);
			if (-- countDown == 0) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SelfManaged();
		}
	}
}
