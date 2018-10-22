package io;
/*
 * 18.6.6 读写随机访问文件
 * 使用RandomAccessFile类，类似于组合使用了DataInputStream和DataOutputStream
 * 因为他们实现了同样的接口: DataInput和DataOutput
 * */
import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
	static String file = "src/io/rtest.dat";
	static void display() throws IOException{
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for(int i = 0; i < 7; i++)
			System.out.println("Value " + i + ": " + rf.readDouble());
		System.out.println(rf.readUTF());
		rf.close();
	}
	public static void main(String[] args) throws IOException{
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		for(int i = 0; i < 7; i++)
			rf.writeDouble(i * 1.414);
		rf.writeUTF("The end of the file");
		rf.close();
		display();
		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8);
		rf.writeDouble(47.0001);
		rf.close();
		display();
	}
}
