package socket.keepalive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName socket.keepalive.Client.java
 * @Description c/s 架构客户端对象, 持有该对象, 可以随时向服务端发送消息
 * 
 * @author dayday
 * @date 2019年3月4日
 */
public class Client {
	
	/**
	 * 处理服务端发回的对象, 可实现该接口
	 */
	public static interface ObjectAction {
		void doAction(Object obj, Client client);
	}
	
	public static final class DefaultObjectAction implements ObjectAction {

		@Override
		public void doAction(Object obj, Client client) {
			System.out.println("处理: \t" + obj.toString());
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "127.0.0.1";
		int port = 65432;
		Client client = new Client(serverIp, port);
		client.start();
	}
	
	/** 服务端ip */
	private String serverIp;
	/** 端口 */
	private int port;
	/** 会话 */
	private Socket socket;
	/** 连接状态 */
	private boolean running = false;
	/** 最后一次发送数据的时间 */
	private long lastSendTime;
	/** 用于保存接受消息对象类型及该类型消息处理的对象 */
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<>();
	
	public Client(String serverIp, int port) {
		this.serverIp = serverIp;
		this.port = port;
	}
	
	public void start() throws UnknownHostException, IOException {
		if (running) {
			return;
		}
		socket = new Socket(serverIp, port);
		System.out.println("本地端口: " + socket.getLocalPort());
		lastSendTime = System.currentTimeMillis();
		running = true;
		new Thread(new KeepAliveWatchDog()).start();
		new Thread(new ReceiveWatchDog()).start();
	}
	
	public void stop() {
		if (running) {
			running = false;
		}
	}
	
	public void addActionMap(Class<Object> cls, ObjectAction action) {
		actionMapping.put(cls, action);
	}
	
	public void sendObject(Object obj) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(obj);
		System.out.println("发送: \t" + obj);
		oos.flush();
	}
	
	class KeepAliveWatchDog implements Runnable {
		long checkDelay = 10;
		long keepAliveDelay = 2000;
		
		@Override
		public void run() {
			while (running) {
				if (System.currentTimeMillis() - lastSendTime > keepAliveDelay) {
					try {
						Client.this.sendObject(new KeepAlive());
					} catch (IOException ex) {
						ex.printStackTrace();
						Client.this.stop();
					}
					lastSendTime = System.currentTimeMillis();
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(checkDelay);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
						Client.this.stop();
					}
				}
			}
		}
	}
	
	class ReceiveWatchDog implements Runnable {
		
		@Override
		public void run() {
			while (running) {
				try {
					InputStream in = socket.getInputStream();
					if (in.available() > 0) {
						ObjectInputStream ois = new ObjectInputStream(in);
						Object obj = ois.readObject();
						System.out.println("接收: \t" + obj);
						ObjectAction oa = actionMapping.get(obj.getClass());
						oa = oa == null ? new DefaultObjectAction() : oa;
					} else {
						TimeUnit.MICROSECONDS.sleep(10);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					Client.this.stop();
				}
			}
		}
		
	}
}
