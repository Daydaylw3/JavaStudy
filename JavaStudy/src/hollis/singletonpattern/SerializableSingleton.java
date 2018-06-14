package hollis.singletonpattern;

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
	
	private Object readResolve() {
		return instance;
	}
}
