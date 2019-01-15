package fortest;

import java.io.UnsupportedEncodingException;

public class StringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String("汉".getBytes("UTF-8"), "GBK"));
		byte[] bs = "汉".getBytes("GBK");
		for (byte b : bs) {
			System.out.println(b);
		}
//		byte[] name = "乐天".getBytes("UTF-8");
////		System.out.println(HexString(name));
//		char c = '汉';
//		getBytes就是得到ascii码
		
	}
}
