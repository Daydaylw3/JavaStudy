package typeinfo;


/*
 * 泛型类引用只能赋值为指向其声明的类型，但是普通的
 * 类引用可以被重新赋值指向任何其他的Class对象
 * 
 * 通过使用泛型，可以让编译器强制执行额外的类型检查
 * */
public class GenericClassReferences {

	public static void main(String[] args) {
		Class intClass = int.class;
		Class<Integer> genericIntClass = int.class;
		genericIntClass = Integer.class;
		intClass = double.class;
//		genericIntClass = double.class;	//illegal
	}

}
