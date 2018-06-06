package generics;

import java.util.List;

import com.dayday.util.New;

/**
 * 显式的类型说明
 * 在泛型方法中，可以显式地指明类型
 * 在点操作符与方法名之间插入尖括号
 * 然后把类型置于尖括号内。
 * */
public class ExplicitTypeSpecification {
	static void f(List<? extends Number> number) {}
	public static void main(String[] args) {
		f(New.<Integer>list());
	}
}
