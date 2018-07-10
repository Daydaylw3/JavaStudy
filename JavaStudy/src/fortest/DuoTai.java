package fortest;

import java.util.HashMap;
import java.util.Map;

/* 
 * 多态
 * 
 * 多态的三个必要条件
 * 1、继承
 * 2、重写
 * 3、父类引用指向子类对象
 * 
 * 当使用多态方式调用方法时，首先检查父类中是否有该方法
 * 如果没有，则编译错误；如果有，再去调用子类的同名方法
 * */
public class DuoTai {	
	class A {
		String a = "a";
		void f() {
			System.out.println(a);
		}
		void g() {
			f();
			Map map = new HashMap();
		}
	}
	class B extends A {
		String b = "b";
		@Override
		void f() {
			System.out.println(b);
		}
		void h() {
			f();
		}
	}
	public static void main(String[] args) {
		A tmp = new DuoTai().new B();
		tmp.g();
//		tmp.h();
	}
}

