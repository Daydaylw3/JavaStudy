package io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Letter implements Comparable{
	public String toString() {
		return this.getClass().toString().
				substring(this.getClass().toString().indexOf(".") + 1, 
						this.getClass().toString().length());
	}

	@Override
	public int compareTo(Object o) {
		return -1;
	}
}
class Aa extends Letter{}
class Bb extends Letter{}
class Cc extends Letter{}
class Dd extends Letter{}

public class ListFeatures {

	static List<Letter> getLetterList(int count) {
		Random rand = new Random(47);
		List<Letter> letters = new ArrayList<Letter>(count);
		for(int i = 0; i < count; i++) {
			int tmp = rand.nextInt();
//			System.out.println(tmp);
			switch(tmp % 4) {
			case 0: letters.add(new Aa());
			break;
			case 1: letters.add(new Bb()); break;
			case 2: letters.add(new Cc()); break;
			case 3: letters.add(new Dd()); break;
			default: letters.add(new Aa()); break;
			}
		}
		return letters;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Letter> letters = getLetterList(7);
		int count = 0;
		System.out.println(++count + " : " + letters);
		Cc c = new Cc();
		letters.add(c);
		System.out.println(++count + " : " + letters);
		
		System.out.println(++count + " : " + letters.contains(c));
		letters.remove(c);
		Letter l = letters.get(2);
		System.out.println(++count + " : " + l + " " + letters.indexOf(l));
		Aa a = new Aa();
		System.out.println(++count + " : " + letters.indexOf(a));
		System.out.println(++count + " : " + letters.remove(a));
		//7
		System.out.println(++count + " : " + letters.remove(l));
		//8
		System.out.println(++count + " : " + letters);
		letters.add(3, new Bb());
		//9
		System.out.println(++count + " : " + letters);
		List<Letter> sub = letters.subList(1, 5);
		System.out.println("subList : " + sub);
		//10
		System.out.println(++count + " : " + letters.containsAll(sub));
		Collections.sort(sub);
		System.out.println("sorted subList : " + sub);
		//11	顺序是不重要的 在 containsAll()方法中
		System.out.println(++count + " : " + letters.containsAll(sub));
		Collections.shuffle(sub, new Random(47));
		System.out.println("shuffled subList : " + sub);
		//12
		System.out.println(++count + " : " + letters.containsAll(sub));
		//copy
		List<Letter> copy = new ArrayList<Letter>(letters);
		sub = Arrays.asList(letters.get(1), letters.get(4));
		System.out.println("sub : " + sub);
		copy.retainAll(sub);
		//13
		System.out.println(++count + " : " + copy);
		copy = new ArrayList<Letter>(letters);
		copy.remove(2);
		//14
		System.out.println(++count + " : " + copy);
		copy.removeAll(sub);
		//15
		System.out.println(++count + " : " + copy);
		copy.set(1, new Dd());
		//16
		System.out.println(++count + " : " + copy);
		copy.addAll(2, sub);
		///17		从中间插入整个Collection
		System.out.println(++count + " : " + copy);
		//18
		System.out.println(++count + " : " + letters.isEmpty());
		letters.clear();
		//19
		System.out.println(++count + " : " + letters);
		//20
		System.out.println(++count + " : " + letters.isEmpty());
		//21
		letters = getLetterList(4);
		Object[] o = letters.toArray();
		System.out.println(++count + " : " + letters);
		System.out.println(++count + " : " + o[3] );
	}

}
