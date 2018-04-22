package sort;

public class SelectionSort implements Sort {

	@Override
	public void sort(int[] array) {
		if(array.length == 0)
			return;
		for(int i = 0; i < array.length; i ++) {
			int minIndex = i;
			for(int j = i; j < array.length; j ++) {
				if(array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}
	
}
