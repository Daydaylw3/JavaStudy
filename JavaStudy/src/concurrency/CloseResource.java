package concurrency;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.CloseResource
 * @Description 21.4.3 中断</br>
 * 通过关闭任务在其上发生阻塞的底层资源来中断阻塞
 * 在shutdownNow()被调用之后以及在两个输入流上调用close()之前的延迟强调的是
 * 一旦底层资源被关闭，任务将解除阻塞。有一点很有趣，interrupt()看起来发生在关
 * 闭Socket而不是关闭System.in的时刻
 * 
 * @author daydaylw3
 * @date Apr 1, 2019
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream socketInput = 
				new Socket("localhost", 8080).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MILLISECONDS.sleep(2000);
		System.out.println("Shutting down all threads");
		exec.shutdownNow();		// 线程池对所有的线程调用interrupt()
		TimeUnit.MILLISECONDS.sleep(1);
		System.out.println("Closing " + socketInput.getClass().getName());
		socketInput.close();		// 释放阻塞的线程
		TimeUnit.MILLISECONDS.sleep(1);
		System.out.println("Closing " + System.in.getClass().getName());
		System.in.close();	// 释放阻塞的线程
	}

}
