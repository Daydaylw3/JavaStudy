package holding;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {

	protected String[] words = ("and that is how we know the earth to be banana-shaped".split(" "));

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int index = 0;
			
			//通过hasNext()判断何时结束
			@Override
			public boolean hasNext() {
				return index < words.length;
			}
			//我觉得关键在这里，通过next()返回什么最重要
			@Override
			public String next() {
				return words[index++];
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}

	public static void main(String[] args) {
		for(String s : new IterableClass())
			System.out.print(s + " ");
	}
}
