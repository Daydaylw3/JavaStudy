package io;
/*
 * 18.10.2 获取基本类型
 * 尽管ByteBuffer只能保存字节类型的数据, 但是它可以从其所容纳的字节中产生出
 * 各种不同基本类型值的方法: 
 * */

import java.nio.ByteBuffer;

public class GetData {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// Allocation automatically zeroes the ByteBuffer
		int i = 0;
		while(i++ < bb.limit())
			if(bb.get() != 0)
				System.out.println("nozero");
		System.out.println("i = " + i);
		bb.rewind();
		// Store and read a char array
		bb.asCharBuffer().put("Howdy");
		char c;
		while((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		bb.rewind();
		// Store and read a short
		bb.asShortBuffer().put((short)471142);
		System.out.println(bb.getShort());
		bb.rewind();
		// Store and read an int
		bb.asIntBuffer().put(99471142);
		System.out.println(bb.getInt());
		bb.rewind();
		// Store and read a long
		bb.asLongBuffer().put(99471142);
		System.out.println(bb.getLong());
		bb.rewind();
		// Store and read a float
		bb.asFloatBuffer().put(99471142);
		System.out.println(bb.getFloat());
		bb.rewind();
		// Store and read a double
		bb.asDoubleBuffer().put(99471142);
		System.out.println(bb.getDouble());
		bb.rewind();
	}
}
