package socket.keepalive;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
		client.start();
	}
	
	private String remoteIp;
	
	private int remotePort;
	
	private long lastReceiveTime;
	
	private Socket socket;
	
	public MyClient(String ip, int port) {
		remoteIp = ip;
		remotePort = port;
	}
	
	public void start() throws UnknownHostException, IOException, InterruptedException {
		socket = new Socket(remoteIp, remotePort);
		new Thread(new SendMsg()).start();
	}
	
	class SendMsg implements Runnable {
		@Override
		public void run() {
			DataOutputStream out;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				out.write("helo server".getBytes());
				out.flush();
				TimeUnit.MICROSECONDS.sleep(1000);
				out.write("helo server again".getBytes());
				out.flush();
//				socket.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class SendHeartBeat implements Runnable {
		
		@Override
		public void run() {
			if (socket != null) {
				try {
					OutputStream os = socket.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);
					while (socket != null) {
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
									System.out.println("received");
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
