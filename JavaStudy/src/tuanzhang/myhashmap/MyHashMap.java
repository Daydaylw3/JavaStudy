package tuanzhang.myhashmap;

public class MyHashMap<K, V> implements MyMap<K, V> {
	//数组默认初始化长度
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;	//16
	//阈值比例
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private int defaultInitSize;
	private float defaultLoadFactor;
	
	//Map中Entry的数量
	private int entryUseSize;
	//数组
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
	
	@Override
	public V put(K k, V v) {
		V oldValue = null;
		//是否需要扩容？
		//扩容完毕 肯定需要重新散列
		if(entryUseSize >= defaultInitSize * defaultLoadFactor) {
			resize(2 * defaultInitSize);
		}
		return null;
	}

	@Override
	public V get(K k) {
		return null;
	}
	
	private void resize(int i) {
		Entry[] newTable = new Entry[i];
		
		defaultInitSize = i;
		entryUseSize = 0;
		rehash(newTable);
	}
	
	private void rehash(Entry<K, V>[] newTable) {
		//得到原来老的Entry集合  注意遍历单链表
	}

}
