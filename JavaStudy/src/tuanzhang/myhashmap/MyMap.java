package tuanzhang.myhashmap;

//对外暴露快速存取的方法
public interface MyMap<K, V> {
	public V put(K k, V v);
	public V get(K k);
	
	//内部定义了一个内部接口Entry
	interface Entry<K, V>{
		public K getKey();
		public V getValue();
	}
}
