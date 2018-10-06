package containers.test;
/*
 * 17.10.1 性能测试框架
 * */
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(
			10, 5000, 100, 5000, 1000, 5000, 10000, 500);
	//如果需要执行特殊的初始化, 那么可以覆盖此方法
	protected C initialize(int size) { return container; }
	protected C container;
	private String headline = "";
	private List<Test<C>> tests;
	private static String stringField() {
		return "%" + fieldWidth + "s";
	}
	private static String numberField() {
		return "%" + fieldWidth + "d";
	}
	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParams;
	public Tester(C container, List<Test<C>> tests) {
		this.container = container;
		this.tests = tests;
		if(container != null)
			headline = container.getClass().getSimpleName();
	}
	public Tester(C container, List<Test<C>> tests, TestParam[] paramList) {
		this(container, tests);
		this.paramList = paramList;
	}
	public void setHeadLine(String newHeadline) {
		headline = newHeadline;
	}
	public static <C> void run(C cntnr, List<Test<C>> tests) {
		new Tester<C>(cntnr, tests).timedTest();
	}
	public static <C> void run(C cntnr, List<Test<C>> tests, TestParam[] paramList) {
		new Tester<C>(cntnr, tests, paramList).timedTest();
	}
	private void displayHeadline() {
		//计算宽度并填充"-"
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() - 1;
		StringBuilder head = new StringBuilder(width);
		for(int i = 0; i < dashLength / 2; i ++)
			head.append("-");
		head.append(" " + headline + " ");
		for(int i = 0; i < dashLength / 2; i ++)
			head.append("-");
		System.out.println(head);
		//打印纵列头
		System.out.format(sizeField, "size");
		for(Test test : tests)
			System.out.format(stringField(), test.name);
		System.out.println();
	}
	public void timedTest() {
		displayHeadline();
		for(TestParam param : paramList) {
			System.out.format(sizeField, param.size);
			for(Test<C> test : tests) {
				C kontainer = initialize(param.size);
				long start = System.nanoTime();
				
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps;
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}
}
