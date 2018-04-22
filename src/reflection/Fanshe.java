package reflection;

/*
 * 注意：在运行期间，一个类，只有一个Class对象产生。
 * 三种方式常用第三种，第一种对象都有了还要反射干什么。
 * 第二种需要导入类的包，依赖太强，不导包就抛编译错误。
 * 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
 * */
public class Fanshe {
	public static void main(String[] args) {
		Student stu1 = new Student();		//这个new产生了一个Student对象，也产生了一个Class对象。
		Class stuClazz = stu1.getClass();		//获取Class对象
		System.out.println(stuClazz.getName());
		
		Class stuClazz2 = Student.class;
		System.out.println(stuClazz2 == stuClazz);
		
		try {
			Class stuClazz3 = Class.forName("reflection.Student");
			System.out.println(stuClazz3 == stuClazz);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
