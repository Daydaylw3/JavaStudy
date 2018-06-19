package tuanzhang.classinitialization;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
 * java.lang.relect.Constructor类里也有一个newInstance方法可以创建对象
 * 该方法和Class类中的newInstance方法很像,并且Constructor类的
 * newInstance方法更加强大些,我们可以通过这个newInstance方法调用有参的和
 * 私有的构造函数
 * 使用newInstance方法的这两种方式创建对象使用的就是Java的反射机制,事实上
 * Class的newInstance方法内部调用的也是Constructor的newInstance方法
 * */
public class Student {
	private int id;
	public Student(Integer id) { this.id = id; }
	public static void main(String[] args) 
			throws NoSuchMethodException, 
			SecurityException, 
			InstantiationException, 
			IllegalAccessException, 
			IllegalArgumentException, 
			InvocationTargetException {
		Constructor<Student> con = 
				Student.class.getConstructor(Integer.class);
		Student stu = con.newInstance(123);
	}
}
