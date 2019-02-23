package concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName concurrency.DaemonThreadFactory
 * @Description 21.2.8 后台线程<p>
 * 通过编写定制的ThreadFactory可以定制由Executor创建
 * 的线程属性(后台、优先、名称)
 * 
 * @see concurrency.DaemonFromFactory
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class DaemonThreadFactory implements ThreadFactory {
	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
}
