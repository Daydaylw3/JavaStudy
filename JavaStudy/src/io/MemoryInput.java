package io;
/*
 * 18.6.2 从内存中输入
 * */

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
	public static void main(String[] args) throws IOException{
		StringReader in = new StringReader(BufferedInputFile.read("src/io/MemoryInput.java"));
		int c;
		while((c = in.read()) != -1)
			System.out.print((char)c);
	}
}
