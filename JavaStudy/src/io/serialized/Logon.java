package io.serialized;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 18.12.2 序列化的控制
 * transient 关键字
 */
public class Logon implements Serializable {
	private Date date = new Date();
	private String userName;
	private transient String password;
	
	public Logon(String name, String pwd) {
		userName = name;
		password = pwd;
	}
	
	@Override
	public String toString() {
		return "logon info: \n	  userName:  " + userName + "\n	  date:  " + date + "\n	  password:  " + password;
	}
	
	public static void main(String[] args) throws Exception {
		Logon a = new Logon("dayday", "lvvisan");
		System.out.println("logon a = " + a);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/io/serialized/Logon.out"));
		out.writeObject(a);
		out.close();
		TimeUnit.SECONDS.sleep(1);		// delay
		// get it back
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/serialized/Logon.out"));
		a = (Logon)in.readObject();
		System.out.println("logon a = " + a);
	}

}
