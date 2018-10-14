package containers;
/*
 * 17.11.2 设定Collection或Map为不可修改
 * */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.dayday.util.Countries;


public class ReadOnly {
	static Collection<String> data = new ArrayList<String>(Countries.names(3));
	
	public static void main(String[] args) {
		Collection<String> c = Collections.unmodifiableCollection(new ArrayList<String>(data));
		System.out.println(c);
		//! c.add("one");
		
		List<String> a = Collections.unmodifiableList(new ArrayList<String>(data));
		ListIterator<String> lit = a.listIterator();
		System.out.println(lit.next());
		//! lit.add("one");
		
		Set<String> s = Collections.unmodifiableSet(new HashSet<String>(data));
		System.out.println(s);
		//! s.add("one");
		
		// for a SortedSet: 
		Set<String> ss = Collections.unmodifiableSortedSet(new TreeSet<String>(data));
		System.out.println(ss);
		//! ss.add("one");
		
		Map<String, String> m = Collections.unmodifiableMap(new HashMap<String, String>(Countries.capitals(3)));
		System.out.println(m);
		//! m.put("Ralph", "Howdy");
		
		//for a SortedMap
		Map<String, String> mm = Collections.unmodifiableSortedMap(new TreeMap<String, String>(Countries.capitals(3)));
		System.out.println(mm);
		//! mm.put("Ralph", "Howdy");
	}
}
