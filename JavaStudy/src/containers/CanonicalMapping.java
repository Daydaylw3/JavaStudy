package containers;
/*
 * 17.12.1 WeakHashMap
 * 在运行时配置VM最大堆最小堆, 效果会比较明显
 * Preference-->Java-->InstalledJREs-->Edit-->Default VM arguments-->输入-Xms2m -Xmx2m
 * */

import java.util.WeakHashMap;


class Element {
	private String ident;
	public Element(String id) { ident = id; }
	@Override public String toString() { return ident; }
	@Override public int hashCode() { return ident.hashCode(); }
	@Override public boolean equals(Object obj) {
		return obj instanceof Element &&
				ident.equals(((Element)obj).ident);
	}
	@Override
	protected void finalize() {
		System.out.println("Finalizing " + 
				getClass().getSimpleName() + " " + ident);
	}
}

class Key extends Element {
	public Key(String id) { super(id); }
}

class Value extends Element {
	public Value(String id) { super(id); }
}

public class CanonicalMapping {
	public static void main(String[] args) {
		int size = 1000;
		if(args.length > 0)
			size = new Integer(args[0]);
		Key[] keys = new Key[size];
		WeakHashMap<Key, Value> map = new WeakHashMap<Key, Value>();
		for(int i = 0; i < size; i++) {
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if(i % 3 == 0)
				keys[i] = k;	// Save as 'real' references
			map.put(k, v);
		}
		System.gc();	// 并不一定会触发gc
	}
}
