package socket.keepalive;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName socket.keepalive.MyServer.java
 * @Description 
 * 
 * @author dayday
 * @date 2019年3月10日
 */
public class MyServer {
	
	private ServerSocket serverSocket;
	
	public MyServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void startServer() throws IOException {
		while (true) {
			System.out.println("waiting for next client...");
			Socket socket = serverSocket.accept();
			// 得到客户端的连接, 用一个线程来接收该socket的消息
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
			while (! s.isClosed()) {
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line;
					if ((line = reader.readLine()) != null && !"heart beat".equals(line)) {
						System.out.println(Thread.currentThread().getName() + " : " + line);
					}
					if ("bye".equals(line)) {
						reader.close();
						s.close();
					}
				} catch (IOException e) {
					try {
						reader.close();
						s.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MyServer server = new MyServer(65432);
		server.startServer();
	}
}
