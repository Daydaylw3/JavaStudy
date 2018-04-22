package sort;

public class BubbleSort implements Sort{
	
	public void sort(int[] array) {
		if(array.length == 0)
			return ;
		for(int i = 0; i < array.length; i ++) {
			for(int j = 0; j < array.length - i - 1; j ++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		
	}
}
