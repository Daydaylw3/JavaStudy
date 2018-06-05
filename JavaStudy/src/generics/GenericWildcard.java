package generics;

/**
 * 泛型通配符
 * 我们知道Ingeter是Number的一个子类，同时在GenericityCharacter.java中也验证过Generic<Ingeter>
 * 与Generic<Number>实际上是相同的一种基本类型。在使用Generic<Number>作为形参的方法中，能否使用
 * Generic<Ingeter>的实例传入呢？在逻辑上类似于Generic<Number>和Generic<Ingeter>是否可以看成具有
 * 父子关系的泛型类型呢？
 * */

/**
 * Generic<Integer>不能被看作为Generic<Number>的子类。由此可以看出:同一种泛型可以对应多个版本（因
 * 为参数类型是不确定的），不同版本的泛型类实例是不兼容的
 * */

/**
 * 类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参再直白点的意思就
 * 是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真
 * 实的类型。
 * */
public class GenericWildcard {
	public static void showKeyValue(Generic<Number> obj) {
		System.out.println("泛型测试，key value is " + obj.getKey());
	}
	public static void showKeyValue2(Generic<?> obj) {
		System.out.println("泛型测试，key value is " + obj.getKey());
	}
	public static void main(String[] args) {
		Generic<Integer> gInteger = new Generic<Integer>(123);
		Generic<Number> gNumber = new Generic<Number>(456);
		
		showKeyValue(gNumber);
		
		// showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer> 
		// cannot be applied to Generic<java.lang.Number>
//		showKeyValue(gInteger);
		
		showKeyValue2(gInteger);
	}

}
