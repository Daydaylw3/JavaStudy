package innerclasses;

public class InnerClassTest {

	public static void main(String[] args) {
		Outer out = new Outer();
//		Inner in = out.new privateInner();	不可访问
		Inner in = out.new publicInner();
	}

}

interface Inner{
	
}

class Outer {
	private class privateInner implements Inner {
		
	}
	public class publicInner implements Inner {
		
	}
}
