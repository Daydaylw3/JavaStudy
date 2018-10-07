package containers.test;
/*
 * 17.10.5 对Map的选择
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class MapPerformance {
	static List<Test<Map<Integer, Integer>>> tests = new ArrayList<Test<Map<Integer, Integer>>>();
	static {
		tests.add(new Test<Map<Integer, Integer>>("put"){
			@Override
			int test(Map<Integer, Integer> map, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					map.clear();
					for(int j = 0; j < size; j++)
						map.put(j, j);
				}
				return loops * size;
			}
		});
		tests.add(new Test<Map<Integer, Integer>>("get"){
			@Override
			int test(Map<Integer, Integer> map, TestParam tp) {
				int loops = tp.loops;
				int span = tp.size * 2;
				for(int i = 0; i < loops; i++)
					for(int j = 0; j < span; j++)
						map.get(j);
				return loops * span;
			}
		});
		tests.add(new Test<Map<Integer, Integer>>("iterate"){
			@Override
			int test(Map<Integer, Integer> map, TestParam tp) {
				int loops = tp.loops * 10;
				for(int i = 0; i < loops; i++) {
					Iterator it = map.entrySet().iterator();
					while(it.hasNext())
						it.next();
				}
				return loops * map.size();
			}
		});
	}
	public static void main(String[] args) {
		if(args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		Tester.fieldWidth = 14;
		Tester.run(new TreeMap<Integer, Integer>(), tests);
		Tester.run(new HashMap<Integer, Integer>(), tests);
		Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
		Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
		Tester.run(new WeakHashMap<Integer, Integer>(), tests);
		Tester.run(new Hashtable<Integer, Integer>(), tests);
	}
}

/*
 * 
------------------- TreeMap -------------------
 size           put           get       iterate
   10           227            49            23
  100            61            26             5
 1000            60            38             5
10000            83            49             7
------------------- HashMap -------------------
 size           put           get       iterate
   10           109            57            37
  100            50             5             5
 1000            12             4             4
10000            12             5             4
---------------- LinkedHashMap ----------------
 size           put           get       iterate
   10           258            38            12
  100            16             7             5
 1000            30            10             4
10000            17             9             5
--------------- IdentityHashMap ---------------
 size           put           get       iterate
   10            84            29            18
  100            17            33            11
 1000            72            75            12
10000            83            82            14
----------------- WeakHashMap -----------------
 size           put           get       iterate
   10           100            36            17
  100            24             6             8
 1000            21             8            11
10000            20             8            44
------------------ Hashtable ------------------
 size           put           get       iterate
   10            68            18            16
  100            26            18             6
 1000            19            17             6
10000            26            20             6
*/
