package io.serialized;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @ClassName io.serialized.TestSerialVersionUID
 * @Description 测试一下序列化Id的作用: 
 * 为了使旧的序列化文件可以被反序列化为被修改过对象
 * 
 * @author daydaylw3
 * @date 2019年2月7日
 */
public class TestSerialVersionUID {
	
	public static void main(String[] args) throws Exception {
		ObjectOutputStream out = 
				new ObjectOutputStream(new FileOutputStream("src/io/serialized/testSerialVersionUID.out"));
		A a1 = new A();
		out.writeObject(a1);
		out.flush();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/serialized/testSerialVersionUID.out"));
		A a2 = (A)in.readObject();
		System.out.println(a2);
	}
	
}

class A implements Serializable {
	
	private static final long serialVersionUID = -152501191730302395L;

	// 没有序列化id
	public String name;
	
	public int age;
	// 新添加的属性
	public String sex;
	
	public A() {
		name = "a";
		age = 10;
		sex = "male";
	}
	
	@Override
	public String toString() {
//		return name + " " + age;
		return name + " " + age + " " + sex;
	}
}