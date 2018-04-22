package polymorphism;

class Useful {
	public void f() {}
	public void g() {}
}
class MoreUseful extends Useful {
	public void f() {}
	public void g() {}
	public void u() {}
	public void v() {}
	public void w() {}
}
public class RTTI {

	public static void main(String[] args) {
		Useful[] x = {
				new Useful(),
				new MoreUseful()
		};
		x[0].f();
		x[1].g();
		//	The method u() is undefined for the type Useful
		//x[1].u();
		((MoreUseful)x[1]).u(); //向下转型
		((MoreUseful)x[0]).u();	//ClassCastException
	}

}
