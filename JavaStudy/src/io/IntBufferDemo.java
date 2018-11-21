package io;
/*
 * 18.10.3 视图缓冲器
 * */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// view buffer
		IntBuffer ib = bb.asIntBuffer();
		// store an array or int
		ib.put(new int[] { 11, 42, 47, 99, 143, 811, 1016});
		//
		System.out.println(ib.get(3));
		ib.put(3, 1811);
		
		ib.flip();
		while(ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}
	}
}
