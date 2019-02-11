package thread.cooperation;

import java.util.Random;
import java.util.concurrent.*;

class IOPipe extends LinkedBlockingQueue<Character>{}

class SenderInExercise30 implements Runnable {
	private IOPipe out;
	private Random rand = new Random(47);
	public SenderInExercise30(IOPipe out) {
		this.out = out;
	}
	@Override
	public void run() {
		try {
			while(true) {
				for(char c = 'A'; c <= 'z'; c++) {
					out.put(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		}catch(InterruptedException e) {
			System.out.println(e + " Sender sleep interrupted");
		}
	}
}

class ReceiverInExercise30 implements Runnable {
	private IOPipe in;
	public ReceiverInExercise30(IOPipe in) {
		this.in = in;
	}
	@Override
	public void run() {
		try {
			while(true) {
				System.out.print("Read: " + (char)in.take() + ", ");
			}
		}catch(InterruptedException e) {
			System.out.println(e + " Receiver interrupted");
		}
	}
}

public class Exercise30 {
	public static void main(String[] args) throws Exception{
		IOPipe pipe = new IOPipe();
		SenderInExercise30 sender = new SenderInExercise30(pipe);
		ReceiverInExercise30 receiver = new ReceiverInExercise30(pipe);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}
