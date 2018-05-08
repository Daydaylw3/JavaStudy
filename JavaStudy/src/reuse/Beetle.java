package reuse;

class Insect {
	private int i = 9;
	protected int j;
	Insect() {
		System.out.println("i = " + i + ", j = " + j);
		j = 39;
	}
	static {
		System.out.println("after or before x1");
	}
	private static int x1 = 
			printInit("static Insert.x1 initialized");
	public static int x3 = 
			printInit("static Insert.x3 initialized");
	
	static int printInit(String s) {
		System.out.println(s);
		return 47;
	}
	
}

public class Beetle extends Insect {
	private int k = printInit("Beetle.k initialized");
	public Beetle() {
		System.out.println("k = " + k);
		System.out.println("j = " + j);
	}
	private static int x2 = 
			printInit("static Beetle.x2 initialized");
	static {
		System.out.println("after or before x2");
	}

//	public static void main(String[] args) {
//		System.out.println("Beetle constructor");
//		Beetle b = new Beetle();
//	}

}
class BeetleTest {
	public static void main(String[] args) {
		System.out.println("Beetle constructor");
		Beetle b = new Beetle();
	}
}
