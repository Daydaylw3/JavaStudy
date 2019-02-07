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
		byte bb = 65;
//		System.out.println(b);
		String s = "helo";
		byte[] helo = s.getBytes();
		for (byte c : helo) {
			System.out.println(c);
		}
		char cc = 'a';
		helo[1] = (byte)cc;
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
		
		System.out.println("---- 作业 ----");
		String name = "谭乐天";
		char[] cName = name.toCharArray();
		for (char n : cName) {
			System.out.print(Integer.toHexString(n));
		}
		System.out.println();
		byte bbbbbb = (byte)0x12;
		// e = 101
		
		byte[] result = "e".getBytes();
		for (byte b : result) {
			System.out.println(b);
		}
		result = new byte[] {
				0x46, 0x47, 0x48, 0x49
		};
		for (byte b : result) {
			System.out.println(b);
		}
		byte b1 = (byte)0x7f;
		System.out.println(b1);
		readme3();
	}
	
	public static byte[] hexString2ByteArray(String hexString) {
		byte[] result = hexString.getBytes();
		
		return result;
	}
	
	public static void readme3() {
		System.out.println("---- readme3 ----");
		byte[] readme3 = new byte[] {
				0x73, (byte)0xdc, (byte)0x73, (byte)0xbb, 
				(byte)0x67, (byte)0x8d, (byte)0x63, (byte)0x44, 
				(byte)0xab, (byte)0x9f, (byte)0x8d, (byte)0x56
		};
		for (byte b : readme3) {
			System.out.println(b);
		}
	}
}
