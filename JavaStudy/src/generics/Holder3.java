package generics;

//通常我们只希望容器来存储一种类型的对象
//泛型的主要目的之一就是用来指定容器要持
//有什么类型的对象，由编译器来保证类型的正确性
public class Holder3<T> {
	public T a;
	public Holder3(T a) {
		this.a = a;
	}
	public T get() { return a; }
	public void set(T a) { this.a = a; }
	public static void main(String[] args) {
		Holder3<Automobile> h = new Holder3<Automobile>(new Automobile());
		Automobile a = (Automobile)h.get();
//		h.set("helo");		//Error
	}

}
