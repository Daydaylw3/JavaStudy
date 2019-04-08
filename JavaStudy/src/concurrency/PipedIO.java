package concurrency;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName concurrency.PipedIO
 * @Description 21.5.5 任务间使用管道进行输入/输出</br>
 * 管道基本上是一个阻塞队列。通过输入/输出在线程间进行通信通常很有用, 
 * 提供线程功能的类库以“管道”的形式对线程间的输入/输出提供了支持。它们
 * 在Java输入/输出类库中的对应物就是PipedWriter类(允许任务向管道写)
 * 和PipedReader类 (允许不同任务从同一个管道中读取)。这个模型可以看成
 * 是"生产者-消费者"问题的变体，这里的管道就是一一个封装好的解决方案。
 * 
 * @author daydaylw3
 * @date Apr 9, 2019
 */
public class PipedIO {
	public static void main(String[] args) throws Exception{
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}

class Sender implements Runnable {
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();
	
	public PipedWriter getPipedWriter() {
		return out;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				for(char c = 'A'; c <= 'z'; c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		} catch (IOException e) {
			System.out.println(e + " Sender write exception");
		} catch (InterruptedException e) {
			System.out.println(e + " Sender sleep interrupted");
		}
	}
}

class Receiver implements Runnable {
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				System.out.print("Read: " + (char)in.read() + ", ");
			}
		} catch (IOException e) {
			System.out.println(e + " Receiver read exception");
		}
	}
}
