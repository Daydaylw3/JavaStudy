package socket.keepalive;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName socket.keepalive.MyClient.java
 * @Description 
 * 
 * @author dayday
 * @date 2019年3月10日
 */
public class MyClient {
	
	public static void main(String[] args) throws Exception {
		MyClient client = new MyClient("127.0.0.1", 65432);
		client.conn();
	}
	
	private String ip;
	
	private int port;
	
	private Socket socket;
	
	public MyClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void conn() {
		try {
			socket = new Socket(ip, port);
			new Thread(new SendHeartBeat()).start();
			new Thread(new SendMsg()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class SendMsg implements Runnable {
		@Override
		public void run() {
			BufferedWriter writer = null;
			while (! socket.isClosed()) {
				try {
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					Scanner sc = new Scanner(System.in);
					writer.write(sc.nextLine() + "\n");
					writer.flush();
				} catch (IOException e) {
					try {
						if (writer != null) {
							writer.close();
						}
						socket.close();
					} catch (IOException ex) {
						// do nothing
					}
				}
			}
		}
	}
	
	class SendHeartBeat implements Runnable {
		@Override
		public void run() {
			while (! socket.isClosed()) {
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					TimeUnit.SECONDS.sleep(5);
					writer.write("heart beat" + "\n");
					writer.flush();
				} catch (IOException | InterruptedException e) {
					if (e instanceof IOException) {
						try {
							if (writer != null) {
								writer.close();
							}
							socket.close();
						} catch (IOException ex) {
							// do nothing
						}
					}
				}
			}
		}
	}
	
}
