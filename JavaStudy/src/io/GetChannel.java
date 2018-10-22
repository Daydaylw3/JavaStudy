package io;
/*
 * 18.10 新I/O
 * */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws Exception {
		// Write a file
		FileChannel fc = 
				new FileOutputStream("src/io/text/data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		fc.close();
		// Add to the end of the file
		fc = new RandomAccessFile("src/io/text/data.txt", "rw").getChannel();
		fc.position(fc.size());	// Move to the end
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		// Read the file
		fc = new FileInputStream("src/io/text/data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		// 一旦调用read()来告知FileChannel向ByteBuffer存储字节
		// 就必须调用缓冲器上的flip()，让它做好让别人读取字节的准备
		fc.read(buff);
		buff.flip();
		while(buff.hasRemaining())
			System.out.print((char)buff.get());
	}
}
