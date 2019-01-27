package io.serialized;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 18.12.2 序列化的控制
 */
public class Blip3 implements Externalizable {

	private int i;
	private String s;
	// 反序列化时只会调用默认的构造方法
	public Blip3() {
		System.out.println("Blip3 constructor");
	}
	public Blip3(String x, int a) {
		System.out.println("Blip3(String x, int a)");
		s = x;
		i = a;
	}
	
	@Override
	public String toString() {
		return s + i;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip3.writeExternal");
		// you must do this
		out.writeObject(s);
		out.writeInt(i);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip3.readExternal");
		// you must do this
		s = (String)in.readObject();
		i = in.readInt();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("contructing objects: ");
		Blip3 b3 = new Blip3("a string ", 47);
		System.out.println(b3);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/io/serialized/Blip3.out"));
		System.out.println("saving objects: ");
		o.writeObject(b3);
		o.close();
		// now get it back
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/serialized/Blip3.out"));
		System.out.println("Recovering b3: ");
		b3 = (Blip3)in.readObject();
		System.out.println(b3);
	}
	
}
