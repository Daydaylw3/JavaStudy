package generics;

//可以让一个类持有Object类型
public class Holder2 {
	private Object a;
	public Holder2(Object a) {
		this.a = a;
	}
	public Object get() { return a; }
	public void set(Object a) { this.a = a; }
	
	public static void main(String[] args) {
		Holder2 h = new Holder2(new Automobile());
		Automobile a = (Automobile)h.get();
		h.set("helo");
		String s = (String)h.get();
	}
}
