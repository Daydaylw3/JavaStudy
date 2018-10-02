package containers;
/*
 * 17.9.2 为速度而散列
 * */

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.dayday.util.Countries;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	static final int SIZE = 997;
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	
	/*
	 * 解释一下我理解的put()方法的逻辑：
	 * 1、根据key的hashCode找到buckets中的位置
	 * 2.1、若桶中不存在链表，表示还没有元素被散列至此，则创建
	 * 		新的LinkedList，并添加至链表末尾
	 * 2.2、若存在链表，则遍历该链表，若存在相同key，则更新
	 * 2.2、若不存在相同key，则添加进链表末尾
	 * */
	@Override
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<K, V>>();
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while(it.hasNext()) {
			MapEntry<K, V> iPair = it.next();
			if(iPair.getKey().equals(key)) {
				oldValue = iPair.getValue();
				it.set(pair);
				found = true;
				break;
			}
		}
		if(!found)
			buckets[index].add(pair);
		return oldValue;
	}
	
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null) return null;
		for(MapEntry<K, V> iPair : buckets[index])
			if(iPair.getKey().equals(key))
				return iPair.getValue();
		return null;
	}
	
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		for(LinkedList<MapEntry<K, V>> bucket : buckets) {
			if(bucket == null) continue;
			for(MapEntry<K, V> mpair : bucket)
				set.add(mpair);
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> m = new SimpleHashMap<String, String>();
		m.putAll(Countries.capitals(4));
		System.out.println(m);
		System.out.println(m.get("CHINA"));
		System.out.println(m.entrySet());
	}
}
