package generics;

/**
 * 创建一个new T()的尝试无法实现，一方面是因为擦除，另一
 * 方面是因为编译器不能验证T具有默认的构造器；Java中的解
 * 决方案是传递一个工厂对象，并使用它来创建新的实例
 * 
 * 最便利的工厂对象就是Class对象，因此如果使用类型标签，就
 * 可以使用newInstance()来创建这个类型的新对象
 * */
class ClassAsFactory<T> {
	T x;
	public ClassAsFactory(Class<T> clazz) {
		try {
			x = clazz.newInstance();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
class Employee {}

public class InstantiateGenericType {

	public static void main(String[] args) {
		ClassAsFactory<Employee> factory = new ClassAsFactory<Employee>(Employee.class);
		System.out.println("new ClassAsFactory<Employee>(Employee.class) success");
		//这个可以编译，但是会因为new ClassAsFactory<Integer>(Integer.class)而失败
		//因为Integer没有任何默认的构造函数，这个错误也不是在编译期捕获的
		try {
			ClassAsFactory<Integer> fi = new ClassAsFactory<Integer>(Integer.class);
		}catch(Exception e) {
			System.out.println("new ClassAsFactory<Integer>(Integer.class) failed");
		}
	}

}
