package hollis.singletonpattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSingleton implements Serializable {
	private volatile static SerializableSingleton instance;
	private SerializableSingleton() {}
	public static SerializableSingleton getInstance() {
		if(instance == null) {
			synchronized(SerializableSingleton.class) {
				if(instance == null)
					instance = new SerializableSingleton();
			}
		}
		return instance;
	}
	//防止反序列化创建新的对象
	private Object readResolve() {
		return instance;
	}
}

//对比类，这类无法防止序列化与反序列化破坏单例
class SerializableSingleton2 implements Serializable {
	private volatile static SerializableSingleton2 instance;
	private SerializableSingleton2() {}
	public static SerializableSingleton2 getInstance() {
		if(instance == null) {
			synchronized(SerializableSingleton.class) {
				if(instance == null)
					instance = new SerializableSingleton2();
			}
		}
		return instance;
	}
}

class SerializableSingletonTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//Write
		ObjectOutputStream oop1 = new ObjectOutputStream(new FileOutputStream("tempFile1"));
		ObjectOutputStream oop2 = new ObjectOutputStream(new FileOutputStream("tempFile2"));
		oop1.writeObject(SerializableSingleton.getInstance());
		oop2.writeObject(SerializableSingleton2.getInstance());
		//Read
		File file1 = new File("tempFile1");
		File file2 = new File("tempFile2");
		ObjectInputStream oip1 = new ObjectInputStream(new FileInputStream(file1));
		ObjectInputStream oip2 = new ObjectInputStream(new FileInputStream(file2));
		SerializableSingleton newInstance = (SerializableSingleton)oip1.readObject();
		SerializableSingleton2 newInstance2 = (SerializableSingleton2)oip2.readObject();
		System.out.println(newInstance == SerializableSingleton.getInstance());
		System.out.println(newInstance2 == SerializableSingleton2.getInstance());
	}
}