package generics;

import java.util.ArrayList;
import java.util.List;

public class WhyUsingGenericity {

	public static void main(String[] args) {
		List arrayList = new ArrayList();
		arrayList.add("helo");
		arrayList.add(123);
		
		for(int i = 0; i < arrayList.size(); i++) {
			String item = (String)arrayList.get(i);
//			Log.d("泛型测试, item = " + item);
			System.out.println("泛型测试, item = " + item);
		}
	}

}
