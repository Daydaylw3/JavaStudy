package containers;

import java.util.Iterator;

import com.dayday.util.Generator;
import com.dayday.util.MapData;
import com.dayday.util.Pair;



/*
 * 17.2.2 使用MapData的示例
 * */
class Letters implements Generator<Pair<Integer, String>>,
	Iterable<Integer>{
	private int size = 9;
	private int number = 1;
	private char letter = 'A';
	
	@Override
	public Pair<Integer, String> next(){
		return new Pair<Integer, String>(number++, "" + letter++);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return number < size;
			}

			@Override
			public Integer next() {
				return number++;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}

public class MapDataTest {

	public static void main(String[] args) {
//		Pair Generator:
		System.out.println(MapData.map(new Letters(), 11));
//		an Iterable and a single value
		System.out.println(MapData.map(new Letters(), "Pop"));
	}

}
