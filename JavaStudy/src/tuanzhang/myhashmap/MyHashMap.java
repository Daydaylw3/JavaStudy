package tuanzhang.myhashmap;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K, V> implements MyMap<K, V> {
	//数组默认初始化长度
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;	//16
	//阈值比例
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int defaultInitSize;
	private float defaultLoadFactor;
	
	//Map中Entry的数量 aka 衡量是否要重新散列的因素
	private int entryUseSize;
	//数组 aka 桶
	private Entry<K, V>[] table = null;
	
	/* ---------- 构造方法 ------------ */
	//门面模式
	public MyHashMap() { this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); }
	
	public MyHashMap(int defaultInitCapacity, float defaultLoadFactor) {
		if(defaultInitCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " +
					defaultInitCapacity);
		}
		
		if(defaultLoadFactor < 0 || Float.isNaN(defaultLoadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: " +
					defaultLoadFactor);
		}
		
		this.defaultInitSize = defaultInitCapacity;
		this.defaultLoadFactor = defaultLoadFactor;
		
		table = new Entry[this.defaultInitSize];
	}
	
	/* -------------- 实现Entry --------------- */
	class Entry<K, V> implements MyMap.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		public Entry() {}
		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
	}
	
	/**
	 * 将<K, V>放进数组中
	 * 若原来对应的K已存在，则更新对应的V，并返回原V
	 * */
	@Override
	public V put(K k, V v) {
		V oldValue = null;
		//是否需要扩容？
		//扩容完毕 肯定需要重新散列
		if(entryUseSize >= defaultInitSize * defaultLoadFactor) {
			resize(2 * defaultInitSize);
		}
		//得到hash值，计算出数组中的位置
		int index = hash(k) & (defaultInitSize - 1);
		if(table[index] == null) {
			table[index] = new Entry<K, V>(k, v, null);
			++entryUseSize;
		}else {	//需要遍历单链表
			Entry<K, V> entry = table[index];
			Entry<K, V> e = entry;
			while(e != null) {
				//比较key是否相同，若相同则更新
				if(k == e.getKey() || k.equals(e.getKey())) {
					oldValue= e.value;
					e.value = v;
					return oldValue;
				}
				e = e.next;
			}
			table[index] = new Entry<K, V>(k, v, entry);
			++entryUseSize;
		}
		return oldValue;
	}

	/**
	 * 根据给定的K值返回相应的V
	 * */
	@Override
	public V get(K k) {
		int index = hash(k) & (defaultInitSize - 1);
		
		if(table[index] == null) {
			return null;
		} else {
			Entry<K, V> entry = table[index];
			do {
				if(k == entry.getKey() || k.equals(entry.getKey())) {
					return entry.value;
				}
				entry = entry.next;
			}while(entry != null);
		}
		return null;
	}
	
	//想要散列均匀，就要进行二进制的位运算
	final int hash(K k) {
		int hashCode = k.hashCode();
		hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
		return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
	}
	
	private void resize(int i) {
		Entry[] newTable = new Entry[i];
		
		defaultInitSize = i;
		entryUseSize = 0;
		rehash(newTable);
	}
	
	private void rehash(Entry<K, V>[] newTable) {
		//得到原来老的Entry集合  注意遍历单链表
		List<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();
		for(Entry<K, V> entry : table) {
			if(entry != null) {
				do {
					entryList.add(entry);
					entry = entry.next;
				}while(entry != null);
			}
		}
		//覆盖旧的引用
		if(newTable.length > 0) {
			table = newTable;
		}
		//所谓重新hash，就是重新put entry到hashmap
		for(Entry<K, V> entry : entryList) {
			put(entry.getKey(), entry.getValue());
		}
	}
}
