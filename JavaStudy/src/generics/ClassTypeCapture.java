package generics;

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
