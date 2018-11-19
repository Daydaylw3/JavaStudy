package io;
/*
 * 18.10.3 视图缓冲器
 * ByteBuffer通过一个被“包装”过的8字节数组产生
 * 然后通过不同的基本类型的视图缓冲器显示了出来
 * */

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class ViewBuffer {
	public static void main(String[] args) {
		// 用二进制表示, bb内数据就是:
		// 00000000,00000000,00000000,00000000,00000000,00000000,00000000,01100001
		// 0, 0, 0, 0, 0, 0, 0, 97
		ByteBuffer bb = ByteBuffer.wrap(new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a' });
		bb.rewind();
		System.out.print("Byte Buffer ");
		while(bb.hasRemaining())
			System.out.print(bb.position() + " -> " + bb.get() + ", ");
		System.out.println();
		// char:
		// 一个char两个字节: 
		// 0000000000000000,0000000000000000,0000000000000000,0000000001100001
		// , , , a
		CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
		System.out.print("Char Buffer ");
		while(cb.hasRemaining())
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		System.out.println();
		// float:
		// 一个float四个字节
		// 00000000000000000000000000000000, 00000000000000000000000001100001
		// 0.0, 1.36E-43
		FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
		System.out.print("Float Buffer ");
		while(fb.hasRemaining())
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		System.out.println();
		// int:
		// 一个int四个字节
		// 00000000000000000000000000000000, 00000000000000000000000001100001
		// 0, 97
		IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
		System.out.print("Int Buffer ");
		while(ib.hasRemaining())
			System.out.print(ib.position() + " -> " + ib.get() + ", ");
		System.out.println();
		// long:
		// 一个long八个字节
		// 0000000000000000000000000000000000000000000000000000000001100001
		// 97
		LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
		System.out.print("Long Buffer ");
		while(lb.hasRemaining())
			System.out.print(lb.position() + " -> " + lb.get());
		System.out.println();
		// short:
		// 一个short两个字节
		// 0000000000000000,0000000000000000,0000000000000000,0000000001100001
		// 0, 0, 0, 97
		ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
		System.out.print("Short Buffer ");
		while(sb.hasRemaining())
			System.out.print(sb.position() + " -> " + sb.get());
		System.out.println();
		// double:
		// 一个double八个字节
		// 0000000000000000000000000000000000000000000000000000000001100001
		// 4.8E-322
		DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
		System.out.print("Double Buffer ");
		while(db.hasRemaining())
			System.out.print(db.position() + " -> " + db.get());
		System.out.println();
	}
}
