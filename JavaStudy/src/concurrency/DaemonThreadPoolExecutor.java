package concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.DaemonThreadPoolExecutor
 * @Description 21.2.8 后台线程<p>
 * @Todo 用Mac补上注释
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
