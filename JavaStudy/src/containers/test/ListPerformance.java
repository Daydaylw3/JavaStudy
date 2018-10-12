package containers.test;
/*
 * 17.10.2 对List的选择
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import com.dayday.util.CountingIntegerList;

public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = 
			new ArrayList<Test<List<Integer>>>();
	static List<Test<LinkedList<Integer>>> qTests = 
			new ArrayList<Test<LinkedList<Integer>>>();
	static {
		tests.add(new Test<List<Integer>>("add"){
			@Override
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
		tests.add(new Test<List<Integer>>("get") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++) {
					list.get(rand.nextInt(listSize));
				}
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("set") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = list.size();
				for(int i = 0; i < loops; i++)
					list.set(rand.nextInt(listSize), 47);
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("iteradd") {
			@Override
			int test(List<Integer> list, TestParam tp) {
				final int LOOPS = 1000000;
				int half = list.size() / 2;
				ListIterator<Integer> it = list.listIterator(half);
				for(int i = 0; i < LOOPS; i ++)
					it.add(47);
				return LOOPS;
			}
		});
		tests.add(new Test<List<Integer>>("insert") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i < loops; i++)
					list.add(5, 47);
				return loops;
			}
		});
		tests.add(new Test<List<Integer>>("remove") {
			int test(List<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 5)
						list.remove(5);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("addFirst") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addFirst(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("addLast") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					for(int j = 0; j < size; j++)
						list.addLast(47);
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 0)
						list.removeFirst();
				}
				return loops * size;
			}
		});
		qTests.add(new Test<LinkedList<Integer>>("rmLast") {
			int test(LinkedList<Integer> list, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					list.clear();
					list.addAll(new CountingIntegerList(size));
					while(list.size() > 0)
						list.removeLast();
				}
				return loops * size;
			}
		});
	}
	static class ListTester extends Tester<List<Integer>> {
		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}
		@Override protected List<Integer> initialize(int size){
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}
		public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
			new ListTester(list, tests).timedTest();
		}
	}
	public static void main(String[] args) {
		if(args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, tests.subList(1, 3)) {
			@Override protected List<Integer> initialize(int size){
				Integer[] ia = new Integer[size];
				for(int i = 0; i < size; i++)
					ia[i] = i;
				return Arrays.asList(ia);
			}
		};
		arrayTest.setHeadLine("Array as List");
		arrayTest.timedTest();
		Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		Tester.fieldWidth = 10;
		if(args.length > 0)
			Tester.defaultParams = TestParam.array(args);
		
		ListTester.run(new ArrayList<Integer>(), tests);
		ListTester.run(new LinkedList<Integer>(), tests);
		ListTester.run(new Vector<Integer>(), tests);
		Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(new LinkedList<Integer>(), qTests);
		qTest.setHeadLine("Queue tests");
		qTest.timedTest();
	}
}
/*
 * 随着尺寸的增大
 * ArrayList的iteradd(), insert()以及remove()性能受到的影响较大
 * LinkedList的set(), get()性能受到的影响较大
 * 应该避免使用Vector, 它只存在于支持遗留代码的类库中
 * 最佳的做法可能是将ArrayList作为默认首选, 只有需要额外功能,或者当程序的性能
 * 因为经常从表中间进行插入和删除操作而变差时, 才去选择LinkedList
--- Array as List ---
 size     get     set
   10      11      12
  100      11      12
 1000      11      12
10000      10      12
--------------------------- ArrayList ---------------------------
 size       add       get       set   iteradd    insert    remove
   10        61        12        12        36       182       137
  100        15        12        11        15       183        40
 1000        26        13        12        37       124        48
10000         8        12        22       319       829       378
--------------------------- LinkedList ---------------------------
 size       add       get       set   iteradd    insert    remove
   10        53        25        26        14       210        71
  100         9        39        38        23        58        35
 1000        10       355       354        42        38        13
10000        33      3888      3910         8        40        15
----------------------------- Vector -----------------------------
 size       add       get       set   iteradd    insert    remove
   10        75        13        14        20       189        99
  100         8        13        13        16       188        17
 1000         6        13        16        39       177        56
10000         6        17        13       309       835       372
---------------- Queue tests ----------------
 size  addFirst   addLast   rmFirst    rmLast
   10        55        48        71        70
  100        23        26        36        38
 1000        28        34        33        11
10000         7         8        11         9
*/