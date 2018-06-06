package generics;

import java.util.ArrayList;
import java.util.Collection;

import com.dayday.util.Generator;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;

/*
 * 用于Generator的泛型方法
 * */
public class Generators {
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
		for(int i = 0; i < n; i++)
			coll.add(gen.next());
		return coll;
	}
	public static void main(String[] args) {
		System.out.println(fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 6));
		System.out.println(fill(new ArrayList<Integer>(), new FibonacciGenerator(), 10));
	}
}
