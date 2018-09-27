package containers;
/*
 * 17.8 Map
 * */
public class AssociativeArray<K, V> {
	private Object[][] pairs;
	private int index;
	public AssociativeArray(int length) {
		pairs = new Object[length][2];
	}
	public void put(K key, V value) {
		if(index >= pairs.length)
			throw new ArrayIndexOutOfBoundsException();
		pairs[index++] = new Object[] { key, value };
	}
	@SuppressWarnings("unchecked")
	public V get(K key) {
		if(key == null)
			throw new RuntimeException();
		for(int i = 0; i < index; i++) 
			if(key.equals(pairs[i][0])) 
				return (V)pairs[i][1];
		return null;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < index; i++) {
			result.append(pairs[i][0].toString());
			result.append(" : ");
			result.append(pairs[i][1].toString());
			if(i < index - 1)
				result.append("\n");
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		AssociativeArray<String, String> map = 
				new AssociativeArray<String, String>(4);
		map.put("helo", "world");
		map.put("Rng", "niubi");
		map.put("Edg", "jiayou");
		map.put("Ig", "gogogo");
		try {
			map.put("skt", "liangliang");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("to many objects");
		}
		System.out.println(map);
		System.out.println(map.get("Rng"));
	}
}
