package tuanzhang.classinitialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableStudent implements Serializable {
	private int id;
	public SerializableStudent() {
		System.out.println("test if jvm will invoke constructor");
		id = 123;
	}
	public SerializableStudent(Integer id) { this.id = id; }
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
		oos.writeObject(new SerializableStudent(111));
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("tempFile")));
		SerializableStudent stu = (SerializableStudent)ois.readObject();
	}
}
