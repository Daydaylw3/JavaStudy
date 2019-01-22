package fortest;

public class CodeAndEncode {
	public static void main(String[] args) {
		String yan = "严";
		byte[] byteYan = yan.getBytes();
		System.out.println(byteYan);
		for (byte c : byteYan) {
			System.out.println(c);
		}
		byte[] t = {104,97,108,111};
		String test = new String(t);
		System.out.println(test);
		byte b = 65;
//		System.out.println(b);
		String s = "helo";
		byte[] helo = s.getBytes();
		for (byte c : helo) {
			System.out.println(c);
		}
		char c = 'a';
		helo[1] = (byte)c;
		for (byte c1 : helo) {
			System.out.println(c1);
		}
		System.out.println("-------");
		char ccc = (char)1;
		
		helo[1] = (byte)ccc;
		for (byte c1 : helo) {
			System.out.println(c1);
		}
		char r = '匦';
		byte rr = (byte)r;
		System.out.println(rr);
		System.out.println(Integer.toHexString(r));
	}
}
