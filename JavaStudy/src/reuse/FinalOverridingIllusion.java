package reuse;

class WithFinals {
	private final void f() {
		System.out.println("WithFinals.f()");
	}
	private void g() {
		System.out.println("WithFinals.g()");
	}
}
class OverridingPrivate extends WithFinals{
	private final void f() {
		System.out.println("OverridingPrivate.f()");
	}
	private void g() {
		System.out.println("OverridingPrivate.g()");
	}
}
class OverridingPrivate2 extends OverridingPrivate {
	public final void f() {
		System.out.println("OverridingPrivate2.f()");
	}
//	@Override
	public void g() {
		System.out.println("OverridingPrivate2.g()");
	}
}
public class FinalOverridingIllusion {
	public static void main(String[] args) {
		OverridingPrivate2 op2 = new OverridingPrivate2();
		op2.f();
		op2.g();
		//但是你不能调用这些方法
		OverridingPrivate op = op2;
//		op.f();	The method f() from the type OverridingPrivate is not visible
//		op.g();
		//同样
		WithFinals wf = op2;
//		wf.f();
//		wf.g();
	}
}
