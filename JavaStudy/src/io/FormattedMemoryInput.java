package io;
/*
 * 18.6.3 格式化的内存输入
 * */
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FormattedMemoryInput {

	public static void main(String[] args) throws IOException {
		try {
			//要读取格式化数据, 可以使用DataInputStream, 它是一个面向字节的I/O类,
			//因此必须使用InputStream类而不是Reader类
			DataInputStream in = 
					new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("src/io/FormattedMemoryInput.java").getBytes()));
			while(true)
				System.out.print((char)in.readByte());
		}catch (EOFException e) {
			System.out.println("end of stream");
		}
	}

}
