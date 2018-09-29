package containers;
/*
 * 17.9.1 理解hashCode()
 * */
import java.util.Map.Entry;

public class MapEntry<K, V> implements Entry<K, V> {
	private K key;
	private V value;
	public MapEntry(K k, V v) { 
		key = k;
		value = v;
	}
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = value;
		this.value = value;
		return oldValue;
	}
	
	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode()) ^
				(value == null ? 0 : value.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MapEntry)) return false;
		MapEntry me = (MapEntry)obj;
		return
				(key == null ? me.getKey() == null : key.equals(me.getKey())) &&
				(value == null ? me.getValue() == null : value.equals(me.getValue()));
	}
	
	@Override
	public String toString() {
		return key + "=" + value;
	}
}
