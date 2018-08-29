package containers;

import com.dayday.util.Generator;

class Government implements Generator<String>{
	String[] foundation = ("strange women lying in ponds").split(" ");
	private int index;
	@Override
	public String next() {
		return foundation[index++];
	}
	
}

public class CollectionDataTest {

	public static void main(String[] args) {

	}

}
