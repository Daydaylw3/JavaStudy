package socket.keepalive;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName socket.keepalive.MyServer.java
 * @Description 
 * 
 * @author dayday
 * @date 2019年3月10日
 */
public class MyServer {
	
	private ServerSocket serverSocket;
	
	private long lastReceiveTime;
	
	public MyServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void start() throws IOException {
		while (true) {
			System.out.println("waiting for next client...");
			Socket socket = serverSocket.accept();
			new Thread(new RecMsg(socket)).start();
		}
	}
	
	class RecMsg implements Runnable {
		Socket s;
		public RecMsg(Socket s) {
			this.s = s;
		}
		@Override
		public void run() {
			// 为什么一定要加这一句? 
			while (true) {
				try {
					DataInputStream in = new DataInputStream(s.getInputStream());
					if (in.available() > 0) {
						byte[] b = new byte[512];
						in.read(b);
						System.out.println(Thread.currentThread().getName() + ":  " + new String(b));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MyServer server = new MyServer(65432);
		server.start();
	}
	
	class SendHeartBeat implements Runnable {
		
		private Socket socket;
		
		public SendHeartBeat(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			if (socket != null) {
				try {
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					while (true) {
						dos.write("heart beat".getBytes());
						dos.flush();
						TimeUnit.MILLISECONDS.sleep(2000);
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private class ReceiveHeartBeat implements Runnable {
		private Socket socket;
		
		public ReceiveHeartBeat(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			if (socket != null) {
				try {
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					byte[] b = new byte[1024];
					while (System.currentTimeMillis() - lastReceiveTime < 5000) {
						if (dis.available() > 0) {
							dis.read(b);
							String rec = new String(b);
							if ("heart beat".equals(rec)) {
								lastReceiveTime = System.currentTimeMillis();
							}
						}
					}
					dis.close();
					socket.close();
					socket = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
