package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("src/io/test.txt")));
		out.writeDouble(3.14);
		out.writeInt(3);
		out.writeUTF("that was pi");
		out.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("src/io/test.txt")));
		System.out.println(in.readDouble());
		System.out.println(in.readInt());
		System.out.println(in.readUTF());
	}
}
