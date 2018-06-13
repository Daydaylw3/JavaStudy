package generics;

class GenericBase<T> {
	private T element;
	public void set(T el) { element = el; }
	public T get() { return element; }
}

class Derived1<T> extends GenericBase<T>{}

//去除传参时也要传递带泛型的参数 的警告
//@SuppressWarnings("rawtypes")
class Derived2 extends GenericBase {} 

//error: 不得在超类中使用通配符
//class Derived3 extends GenericBase<?>{}

public class ErasureAndInheritance {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Derived2 d2 = new Derived2();
		Object obj = d2.get();
		d2.set(obj);	//warning here, so need @SuppressWarnings
	}

}
