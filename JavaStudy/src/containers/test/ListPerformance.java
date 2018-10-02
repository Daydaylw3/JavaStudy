package containers.test;
/*
 * 17.10.2 对List的选择
 * */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = 
			new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = 
			new ArrayList<Test<LinkedList<Integer>>>();
	static {
		tests.add(new Test<List<Integer>>("add"){
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < listSize; j ++)
						list.add(j);
				}
				return loops * listSize;
			}
		});
	}
}
