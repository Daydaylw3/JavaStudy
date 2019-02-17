package concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName concurrency.DaemonThreadFactory
 * @Description 21.2.8 后台线程<p>
 * @Todo 用Mac补上注释
 * 
 * @see concurrency.DaemonFromFactory
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class DaemonThreadFactory implements ThreadFactory {
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
