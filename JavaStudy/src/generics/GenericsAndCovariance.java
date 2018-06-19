package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//如果你想要在两个类型之间建立某种类型的向上转型的关系...
public class GenericsAndCovariance {
	public static void main(String[] args) {
		List<? extends Fruit> flist = new ArrayList<Apple>();
		List<? extends Fruit> flist2 = new ArrayList();
		List<? extends Fruit> flist3 = Arrays.asList(new Apple());
		//不能通过编译
//		flist.add(new Fruit());
//		flist.add(new Apple());
//		flist.add(new Object());
		flist.add(null);
//		flist2.add(new Fruit());
		Apple a = (Apple)flist3.get(0);
		Object b = flist3.get(0);
		Fruit f = flist.get(0);
		f = flist2.get(0);
//		Apple a = flist.get(0);
	}
}
