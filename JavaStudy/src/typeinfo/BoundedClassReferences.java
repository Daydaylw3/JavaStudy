package typeinfo;


/*
 * 为了创建一个Class引用，它被限定为某种类型，或者
 * 该类型的任何子类型，可以将通配符与extends关键字结合
 * */
public class BoundedClassReferences {

	public static void main(String[] args) {
		Class<? extends Number> bounded = int.class;
		bounded = double.class;
		bounded = Number.class;
		//or any other derived from Number
	}

}
