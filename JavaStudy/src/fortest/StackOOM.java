package fortest;

public class StackOOM {
	private static int threadNum = 0;
	public void doSth() {
		try {
			Thread.sleep(1000000000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		final StackOOM stackOOM = new StackOOM();
		try {
			while(true) {
				threadNum ++;
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						stackOOM.doSth();
					}
				});
				thread.start();
			}
		} catch(Throwable e) {
			System.out.println("目前活动线程数量：" + threadNum);
			throw e;
		}
	}

}
