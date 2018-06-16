package generics;

/*
 * 从Erased.java中知道，不能创建泛型数组，但是有时候
 * 你还是想创建泛型类型的数组，有趣的是， 可以按照编
 * 译器喜欢的方式来定义一个引用
 * */
public class ArrayOfGenericReference {
	static Generic<Integer>[] gia;
}
/*
 * 编译器将接受这个程序，并且不会产生任何的警告，但是，永远都
 * 不能创建这个确切类型的数组
 * */
