package io.serialized;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/*
 * 18.12.1 寻找类
 * */
public class FreezeAlien {
	public static void main(String[] args) throws Exception {
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("X.file"));
		Alien quellek = new Alien();
		out.writeObject(quellek);
		out.close();
	}
}
