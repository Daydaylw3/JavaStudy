package typeinfo.toys;

public class GenericToyTest {

	public static void main(String[] args) throws Exception {
		Class<FancyToy> ftClass = FancyToy.class;
		FancyToy fancyToy = ftClass.newInstance();
		
		Class<? super FancyToy> up = ftClass.getSuperclass();	//返回的是基类
//		Class<Toy> up2 = ftClass.getSuperclass();	//can't compile
		
		//only product Object
		Object obj = up.newInstance();
	}

}
