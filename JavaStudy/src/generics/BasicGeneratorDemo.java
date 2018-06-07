package generics;

import com.dayday.util.BasicGenerator;
import com.dayday.util.Generator;

public class BasicGeneratorDemo {

	public static void main(String[] args) {
		Generator<CountedObject> gen = 
				BasicGenerator.create(CountedObject.class);
		for(int i = 0; i < 5; i++)
			System.out.println(gen.next());
	}
}
