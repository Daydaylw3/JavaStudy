package generics;

import java.util.List;


/*
 * 超类通配符
 * 
 * 因为apples中装的元素是Apple或Apple的某个父类，我们无法
 * 确定是哪个具体类型,但是可以确定的是Apple和Apple的子类是
 * 和这个“不确定的类”兼容的，因为它肯定是这个“不确定类型”的
 * 子类,也就是说我们可以往集合中添加Apple或者Apple子类的对
 * 象
 * */
public class SuperTypeWildcards {
	static void writeTo(List<? super Apple> apples) {
		apples.add(new Apple());
		apples.add(new Jonathan());
//		apples.add(new Fruit());	//Error
	}
}
