package strings;

import java.util.ArrayList;
import java.util.List;

/*
 * InfiniteRecursion类的修复版
 * */
public class RecoverInfiniteRecursion {
	
	public String toString() {
		return "InfiniteRecursion address: " + super.toString() + "\n";
	}
	
	public static void main(String[] args) {
		List<RecoverInfiniteRecursion> v = 
				new ArrayList<RecoverInfiniteRecursion>();
		for(int i = 0; i < 5; i++)
			v.add(new RecoverInfiniteRecursion());
		System.out.println(v);
	}

}
