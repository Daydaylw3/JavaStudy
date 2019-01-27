package io.serialized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 18.12.2 序列化的控制
 * Externalizable替代方法
 */
public class SerialCtl implements Serializable {
	private String a;
	private transient String b;
	
	public SerialCtl(String aa, String bb) {
		a = "Not transient: " + aa;
		b = "Transient: " + bb;
	}
	
	@Override
	public String toString() {
		return a + "\n" + b;
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		// 如果我们使用默认写入对象的非transient部分, 那么必须调用defaultWriteObject()
		// 作为writeObject()中的第一个操作
		stream.defaultWriteObject();
		stream.writeObject(b);
	}
	
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		// 如果我们使用默认读入对象的非transient部分, 那么必须调用defaultReadObject()
		// 作为readObject()中的第一个操作
		stream.defaultReadObject();
		b = (String)stream.readObject();
	}
	
	public static void main(String[] args) throws Exception {
		SerialCtl sc = new SerialCtl("dayday", "visan");
		System.out.println("before: \n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(buf);
		o.writeObject(sc);
		// get it back
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
		SerialCtl sc2 = (SerialCtl)in.readObject();
		System.out.println("after: \n" + sc2);
	}
}
