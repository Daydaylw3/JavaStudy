package io;
/*
 * 18.10.1 转换数据
 * */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
	private static final int BSIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("src/io/text/data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("some text".getBytes()));
		fc.close();
		fc = new FileInputStream("src/io/text/data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		// flip()函数的作用是将写模式转变为读模式，即将写模式下的Buffer中内容的最后位置变为
		// 读模式下的limit位置，作为读越界位置，同时将当前读位置置为0，表示转换后重头开始读，
		// 同时再消除写模式下的mark标记
		buff.flip();
		// Dosen't work
		System.out.println("乱码: " + buff.asCharBuffer());
		// Decode using this system's default charset
		// rewind()在读写模式下都可用，它单纯的将当前位置置0，同时取消mark标记，仅此
		// 而已；也就是说写模式下limit仍保持与Buffer容量相同，只是重头写而已；读模式
		// 下limit仍然与rewind()调用之前相同
		buff.rewind();
		// 使用System.getProperty("file.encoding")发现默认字符集
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoded using " + encoding + ": " 
				+ Charset.forName(encoding).decode(buff));
		
		fc = new FileOutputStream("src/io/text/data2.txt").getChannel();
		// 使用UTF-16BE进行encode()
		fc.write(ByteBuffer.wrap("some text".getBytes("UTF-16BE")));
		fc.close();
		// try to read again
		fc = new FileInputStream("src/io/text/data2.txt").getChannel();
		// clear()方法用于写模式，其作用为情况Buffer中的内容，所谓清空是指写上限
		// 与Buffer的真实容量相同，即limit==capacity,同时将当前写位置置为最前端下标为0处
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		// use a CharBuff to write through
		fc = new FileOutputStream("src/io/text/data2.txt").getChannel();
		buff = ByteBuffer.allocate(24);	// more than needed
		buff.asCharBuffer().put("some text");
		fc.write(buff);
		fc.close();
		// read and display
		fc = new FileInputStream("src/io/text/data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}
}
