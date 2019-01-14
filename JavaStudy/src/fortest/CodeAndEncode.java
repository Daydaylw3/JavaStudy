package fortest;

public class CodeAndEncode {
	public static void main(String[] args) {
		String yan = "严";
		byte[] byteYan = yan.getBytes();
		byte b = 65;
//		System.out.println(b);
		String s = "helo";
		byte[] helo = s.getBytes();
		for (byte c : helo) {
			System.out.println(c);
		}
		char c = 'h';
		helo[1] = (byte)c;
		for (byte c1 : helo) {
			System.out.println(c1);
		}
	}
}
