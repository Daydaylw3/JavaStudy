package holding;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> priorityQueue = 
				new PriorityQueue<Integer>();
		Random rand = new Random(47);
		for(int i = 0; i < 10; i++)
			priorityQueue.offer(rand.nextInt(i + 10));
		QueueDemo.printQ(priorityQueue);
		
		List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
		priorityQueue = new PriorityQueue<Integer>(ints);
		//自动排序
		QueueDemo.printQ(priorityQueue);
		//排序策略设置为倒序
		priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.reverseOrder());
		priorityQueue.addAll(ints);
		QueueDemo.printQ(priorityQueue);
		
		String fact = "Education should eschew obfuscation";
		List<String> strings = Arrays.asList(fact.split(" "));
		PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
		//自动排序
		QueueDemo.printQ(stringPQ);
		//排序策略设置为倒序
		stringPQ = new PriorityQueue<String>(strings.size(), Collections.reverseOrder());
		stringPQ.addAll(strings);
		QueueDemo.printQ(stringPQ);
		
		Set<Character> charSet = new HashSet<Character>();
		for(char c : fact.toCharArray())
			charSet.add(c);
		PriorityQueue<Character> characterPQ = new PriorityQueue<Character>(charSet);
		//自动排序
		//set只允许一个元素出现一次
		QueueDemo.printQ(characterPQ);
	}

}
