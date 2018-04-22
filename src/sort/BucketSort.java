package sort;

import java.util.ArrayList;

public class BucketSort implements Sort {

	@Override
	public void sort(int[] array) {
		if(array.length == 0)
			return;
		
	}
	
	private int[] BucketSort(int[] array, int bucketSize) {
		int min = array[0], max = array[0];
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max)
				max = array[i];
			if(array[i] < min)
				min = array[i];
		}
		int bucketCount = (max - min) / bucketSize + 1;
		//桶
		ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
		for(int i = 0; i < bucketCount; i++)
			bucketArr.add(new ArrayList<Integer>());
		for(int i = 0; i < array.length; i++) {
			bucketArr.get((array[i] - min) / bucketSize).add(array[i]);
		}
		for(int i = 0; i < bucketCount; i++) {
			//
			if (bucketCount == 1)
				bucketSize --;
//			int[] tmp = BucketSort(bucketArr.get(i), bucketSize);
		}
		return null;
	}
}
