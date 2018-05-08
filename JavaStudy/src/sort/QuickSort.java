package sort;

public class QuickSort implements Sort {

	@Override
	public void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(new QuickSort().partition(new int[]{
			1,2,1,4,3,2
		}, 0, 5));
	}
	
	private void sort(int[] array, int start, int end) {
		int smallIndex = partition(array, start, end);
		if(smallIndex > start)
			sort(array, start, smallIndex - 1);
		if(smallIndex < end)
			sort(array, smallIndex + 1, end);
	}
	
	/*
	 * 
	 * */
	private int partition(int[] array, int start, int end) {
		int pivot = (int)(start + Math.random() * (end - start + 1));
		int smallIndex = start - 1;
		swap(array, pivot, end);
		for(int i = start; i <= end; i++) {
			if(array[i] <= array[end]) {
				smallIndex ++;
				if(i > smallIndex)
					swap(array, i, smallIndex);
			}
		}
		return smallIndex;
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
