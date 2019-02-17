package thread.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 在本例中，可以被撤销的类不是Runnable类型的，而所有依赖于IntGenerator对象的EvenChecker任务将测试它
 * 以查看它是否已经被撤销，通过这种方式，共享公共资源（IntGenerator）的任务可以观察该资源的终止信号
 * 这可以消除所谓的竞争条件，即两个或者更多的任务竞争响应某个条件，因此产生冲突或者不一致结果的情况
 * 这里，通过使任务依赖于非任务对象，可以消除潜在的竞争条件。
 * */
public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;
	public EvenChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}
	
	
	@Override
	public void run() {
		while(!generator.isCanceled()) {
			int val = generator.next();
			if(val % 2 != 0) {
				System.out.println(val + " not even !");
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, count));
		}
		exec.shutdown();
	}
	public static void test(IntGenerator gp) {
		test(gp, 10);
	}

}
