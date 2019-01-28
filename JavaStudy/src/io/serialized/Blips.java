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
class Blip1 implements Externalizable {
	
	public Blip1() {
		System.out.println("Blip1 constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1.writeExternal");
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip1.readExternal");
	}
}

class Blip2 implements Externalizable {
	// not public 
	Blip2() {
		System.out.println("Blip2 constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2.writeExternal");
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2.readExternal");
	}
}
public class Blips {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("constructing objects: ");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/io/serialized/Blips.out"));
		System.out.println("saving objects: ");
		o.writeObject(b1);
		o.writeObject(b2);
		o.close();
		// now get them back
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/serialized/Blips.out"));
		System.out.println("recovering b1: ");
		b1 = (Blip1)in.readObject();
		// throws an exception 'caues Blip2 constructor not public
		System.out.println("recoring b2: ");
		b2 = (Blip2)in.readObject();
	}
}
