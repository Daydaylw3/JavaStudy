package containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/*
 * 17.6.1 SortedSet
 * SortedSet中的元素可以保证处于排序状态
 * */
public class SortedSetDemo {
	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<String>();
		Collections.addAll(sortedSet, "B C A D E F G H I J K".split(" "));
		System.out.println(sortedSet);
		String low = sortedSet.first();
		String tail = sortedSet.last();
		System.out.println("low: " + low + ", tail: " + tail);
		Iterator<String> iterator = sortedSet.iterator();
		for(int i = 0; i <= 6; i++) {
			if(i == 3) low = iterator.next();
			if(i == 6) tail = iterator.next();
			else iterator.next();
		}
		System.out.println("low: " + low + ", tail: " + tail);
		System.out.println(sortedSet.subSet(low, tail));
		System.out.println(sortedSet.headSet(tail));
		System.out.println(sortedSet.tailSet(low));
	}
}
