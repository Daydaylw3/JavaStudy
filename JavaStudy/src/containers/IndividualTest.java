package containers;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import typeinfo.pets.Individual;

public class IndividualTest {
	public static void main(String[] args) {
		Set<Individual> set = 
				new TreeSet<Individual>();
		Random rand = new Random(47);
		for(int i = 0; i < 10; i ++) {
			set.add(new Individual("helo" + i));
		}
		set.add(new Individual("helo1"));
		System.out.println(set);
	}
}
