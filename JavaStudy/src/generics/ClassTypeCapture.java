package generics;


/**
 * 有时候可以绕过擦除丢失类型信息的问题，但是有时必须
 * 通过引入类型标签来对擦除进行补偿，这意味着需要显式
 * 的传递类型的Class的对象，以便可以再类型表达式中使用
 * */
class Building {}
class House extends Building {}

public class ClassTypeCapture<T> {
	Class<T> type;
	public ClassTypeCapture(Class<T> clazz) { type = clazz; }
	public boolean f(Object arg) {
		return type.isInstance(arg);
	}
	public static void main(String[] args) {
		ClassTypeCapture<Building> ctt1 = 
				new ClassTypeCapture<Building>(Building.class);
		System.out.println(ctt1.f(new Building()));
		System.out.println(ctt1.f(new House()));
		ClassTypeCapture<House> ctt2 = 
				new ClassTypeCapture<House>(House.class);
		System.out.println(ctt2.f(new Building()));
		System.out.println(ctt2.f(new House()));
	}
}
