package com.dayday.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 17.2.3 使用Abstract类
 * */
public class Countries {
	public static final String[][] DATA = {
			{"ALGERIA", "Algiers"},	{"ANGOLA", "Luanda"},
			{"CHINA", "Beijing"}, {"ARGENTINA", "Buenos Aires"},
	};
	
	//Use AbstractMap by implementing entrySet()
	//FlyweightMap必须实现entrySet()方法
	private static class FlyweightMap
	extends AbstractMap<String, String>{
		
		@Override
		public Set<Map.Entry<String, String>> entrySet() {
			return entries;
		}
		private static Set<Map.Entry<String, String>> entries =
				new EntrySet(DATA.length);
		
		// Use AbstractSet by implementing size() & iterator()
		static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
			private int size;
			EntrySet(int size){
				if(size < 0)
					this.size = 0;
				else if(size > DATA.length)
					this.size = DATA.length;
				else
					this.size = size;
			}
			private class Iter
			implements Iterator<Map.Entry<String, String>>{
				private Entry entry = new Entry(-1);
				public boolean hasNext() {
					return entry.index < size - 1;
				}
				public void remove() {
					throw new UnsupportedOperationException();
				}
				@Override
				public Map.Entry<String, String> next() {
					entry.index ++;
					return entry;
				}
			}
			@Override
			public Iterator<Map.Entry<String, String>> iterator() {
				return new Iter();
			}

			@Override
			public int size() {
				return size;
			}
		}
		
		private static class Entry implements Map.Entry<String, String>{
			int index;
			Entry(int index) { this.index = index; }
			public boolean equals(Object o) {
				return DATA[index][0].equals(o);
			}
			public String getKey() {
				return DATA[index][0];
			}
			public String getValue() {
				return DATA[index][1];
			}
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
			public int hashCode() {
				return DATA[index][0].hashCode();
			}
		}
	}
	
	static Map<String, String> map = new FlyweightMap();
	public static Map<String, String> select(final int size){
		return new FlyweightMap() {
			public Set<Map.Entry<String, String>> entrySet() {
				return new EntrySet(size);
			}
		};
	}
	public static Map<String, String> capitals(int size){
		return select(size);
	}
	static List<String> names =
			new ArrayList<String>(map.keySet());
	public static List<String> names() { return names; }
	
	public static List<String> names(int size) {
		return new ArrayList<String>(select(size).keySet());
	}
	
	public static void main(String[] args) {
		System.out.println(capitals(2));
		System.out.println(names(1));
	}
}
