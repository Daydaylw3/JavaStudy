package reuse;

class Gizmo {
	public void spin() {}
}

public class FinalArguments {
	void with(final Gizmo g) {
//		g = new Gizmo();	The final local variable g cannot be assigned. It must be blank and not using a compound assignment
	}
	void without(Gizmo g) {
		g = new Gizmo();
		g.spin();
	}
//	void f(final int i) { i++; }	The final local variable i cannot be assigned. It must be blank and not using a compound assignment
	int g(final int i) { return i + 1; }
	
}
