package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 可以这样来模拟transient
 * 将不需要反序列化的域放在未实现Serializable接口的父类中, 子类实现Serializable接口, 
 * 不过值得注意的是, 该情况下, 父类一定要有无参的构造方法
 * 
 * @ClassName serializable.Test
 * 
 * @author daydaylw3
 * @date May 28, 2019
 */
public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		B b = new B(1, 2);
		System.out.println(b);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/serializable/result.out")));
		oos.writeObject(b);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/serializable/result.out")));
		B b2 = (B) ois.readObject();
		System.out.println(b2);
	}
}

class A {
	private int n1;
	
	private int n2;
	
	public A() {}
	
	public A(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("n1 = " + n1);
		sb.append(", n2 = " + n2);
		return sb.toString();
	}
}

class B extends A implements Serializable {
	
	private int n3;
	
	public B(int n1, int n2) {
		super(n1, n2);
		n3 = 3;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(", n3 = " + n3);
		
		return sb.toString();
	}
}