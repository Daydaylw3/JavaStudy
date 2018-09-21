package hollis.autoboxingandautounboxing;
/*
 * 自动拆装箱还提供了一个和缓存有关的功能
 * */
public class AutoBoxingAndCache {
	public static void main(String[] args) {
		Integer integer1 = 3;
		Integer integer2 = 3;
		
		if(integer1 == integer2) 
			System.out.println("integer1 == integer2");
		else 
			System.out.println("integer1 != integer2");
		Integer integer3 = 300;
		Integer integer4 = 300;
		
		if(integer3 == integer4)
			System.out.println("integer3 == integer4");
		else
			System.out.println("integer3 != integer4");
		
	}
}
