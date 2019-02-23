package concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.DaemonThreadPoolExecutor
 * @Description 21.2.8 后台线程<p>
 * 每个静态的ExecutorService创建方法都被重载为接受一个ThreadFactory对
 * 象, 而这个对象将被用来创建新的线程: 
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
	
	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, 
				TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(), 
				new DaemonThreadFactory());
	}
	
}
