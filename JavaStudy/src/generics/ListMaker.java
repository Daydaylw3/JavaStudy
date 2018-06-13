package generics;

import java.util.ArrayList;
import java.util.List;

/*
 * 如果我们要创建 
 * */
public class ListMaker<T> {
	List<T> create() { return new ArrayList<T>(); }
	public static void main(String[] args) {
		ListMaker<String> listMaker = new ListMaker<String>();
		List<String> listString = listMaker.create();
	}
}
