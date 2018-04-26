package io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TestEOF {

	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(
				new ByteArrayInputStream(
						BufferedInputFile.read("/Users/daydaylw3/eclipse-workspace/JavaProject/src/io/TestEOF.java").getBytes()
						)
				);
		while( in.available() != 0)
			System.out.print((char)in.readByte());
	}
}
