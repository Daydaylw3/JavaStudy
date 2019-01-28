package io.serialized;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/*
 * 18.12.1 寻找类
 **/
public class ThawAlien {

	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/serialized/X.file"));
		Object mystery = in.readObject();
		System.out.println(mystery.getClass());
	}

}
