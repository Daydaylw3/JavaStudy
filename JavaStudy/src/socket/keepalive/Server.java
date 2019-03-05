package socket.keepalive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName socket.keepalive.Server.java
 * @Description c/s 架构服务端对象
 * 
 * @author dayday
 * @date 2019年3月4日
 */
public class Server {
	/**
	 * 要处理客户端发来的对象, 并返回一个对象, 可实现该接口
	 * */
	public interface ObjectAction {
		Object doAction(Object rev, Server server);
	}
	
	public static final class DefaultObjectAction implements ObjectAction {

		@Override
		public Object doAction(Object rev, Server server) {
			System.out.println("处理并返回: " + rev);
			return rev;
		}
		
	}
	
	public static void main(String[] args) {
		int port = 65432;
		Server server = new Server(port);
		server.start();
	}
	
	private int port;
	
	private volatile boolean running = false;
	
	private long receiveTimeDelay = 3000;
	
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<>();
	
	private Thread connWatchDog;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() {
		if (running) {
			return;
		}
		running = true;
		connWatchDog = new Thread(new ConnWatchDog());
		connWatchDog.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		if (running) {
			running = false;
		}
		if (connWatchDog != null) {
			connWatchDog.stop();
		}
	}
	
	public void addActionMap(Class<Object> cls, ObjectAction action) {
		actionMapping.put(cls, action);
	}
	
	class ConnWatchDog implements Runnable {
		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(port, 5);
				while (running) {
					Socket s = serverSocket.accept();
					new Thread(new SocketAction(s)).start();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				Server.this.stop();
			}
		}
	}
	
	class SocketAction implements Runnable {
		Socket s;
		boolean run = true;
		long lastReceiveTime = System.currentTimeMillis();
		
		public SocketAction(Socket s) {
			this.s = s;
		}
		
		@Override
		public void run() {
			while (run) {
				try {
					InputStream in = s.getInputStream();
					if (in.available() > 0) {
						ObjectInputStream ois = new ObjectInputStream(in);
						Object rev = ois.readObject();
						System.out.println("接收: \t" + rev);
						ObjectAction oa = actionMapping.get(rev.getClass());
						oa = oa == null ? new DefaultObjectAction() : oa;
						Object out = oa.doAction(rev, Server.this);
						if (out != null) {
							ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
							oos.writeObject(out);
							oos.flush();
						} else {
							Thread.sleep(10);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					overThis();
				}
			}
		}
		
		private void overThis() {
			if (run) {
				run = false;
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("关闭: " + s.getRemoteSocketAddress());
		}
	}
}
