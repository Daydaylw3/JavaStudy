package io;
/*
 * 18.6.3 格式化的内存输入
 * */
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class TestEOF {
	public static void main(String[] args) throws IOException {
		DataInputStream in = 
				new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("src/io/TestEOF.java").getBytes()));
		//	如果从DataInputStream用readByte()一次一个字节的读取字符
		//	那么任何字节的值都是合法的结果, 因此返回值不能用来检测输入是
		//	结束. 相反, 可以使用available()方法来查看还有多少可供存取
		//	的字符
		while( in.available() != 0)
			System.out.print((char)in.readByte());
	}
}
