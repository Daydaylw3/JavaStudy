package fortest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGenericArray {
	
	public static  void main(String[] args) {
		//Java并未禁止“声明一个泛型数组引用”
		List<Integer>[] genericArray = (List<Integer>[])new ArrayList[10];
//		genericArray[0] = new ArrayList<String>(Arrays.asList(new String[]{"Hello"}));
		
	}
}
