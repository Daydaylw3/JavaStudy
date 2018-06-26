package generics;

//为了在类型中使用了通配符的情况下禁用这类调用
//我们需要在参数列表中使用类型参数
public class Holder<T> {
	private T value;
	public Holder() {}
	public Holder(T val) { value = val; }
	public void set(T val) { value = val; }
	public T get() { return value; }
	public boolean equals(Object obj) {
		return value.equals(obj);
	}
	
	public static void main(String[] args) {
		Holder<Apple> Apple = new Holder<Apple>(new Apple());
		Apple d = Apple.get();
		Apple.set(d);
//		Holder<Fruit> Fruit = Apple;	//can't upcase
		Holder<? extends Fruit> fruit = Apple;	//OK
		Fruit p = fruit.get();
		d = (Apple)fruit.get();
		try {
			Orange o = (Orange)fruit.get();
		}catch (Exception e) {
			System.out.println(e);
		}
//		fruit.set(new Apple());
//		fruit.set(new Fruit());
		System.out.println(fruit.equals(d));
	}
}
