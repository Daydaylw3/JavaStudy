package reflection;

import java.lang.reflect.Method;

public class MethodClass {

	public static void main(String[] args) throws Exception{
		//1.
		Class clazz = Class.forName("reflection.Student");
		
		//2.获取所有公共方法
		System.out.println("***************获取所有的”公有“方法*******************");  
		Method[] methodArray = clazz.getMethods();
		for(Method m : methodArray) {
			System.out.println(m);
		}
		
		//3.获取所有方法（包括受保护的，默认的，私有的）
		System.out.println("***************获取所有的方法，包括私有的*******************");  
		methodArray = clazz.getDeclaredMethods();
		for(Method m : methodArray) {
			System.out.println(m);
		}
		
		//4.获取共有的show1()方法
		System.out.println("***************获取公有的show1()方法*******************");  
		Student stu = (Student)clazz.getConstructor().newInstance();
		Method m = clazz.getMethod("show1", String.class);
		System.out.println(m);
		m.invoke(stu, "刘德华");
		
		//5.获取私有show4()方法
		System.out.println("***************获取私有的show4()方法******************");  
		m = clazz.getDeclaredMethod("show4", int.class);
		System.out.println(m);
		m.setAccessible(true);
		System.out.println(m.invoke(stu, 47));
	}

}
