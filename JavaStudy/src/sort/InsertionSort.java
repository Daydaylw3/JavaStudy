package sort;

public class InsertionSort implements Sort {

	@Override
	public void sort(int[] array) {
		// 插入排序就是gap为1的希尔排序
		sort(array, 1);
	}
	private void sort(int[] array, int gap) {
		if(array.length == 0)
			return;
		while(gap > 0) {
			for(int i = gap; i < array.length; i++) {
				int current = array[i];
				int preIndex = i - gap;
				while(preIndex >= 0 && current < array[preIndex]) {
					array[preIndex + gap] = array[preIndex];
					preIndex -= gap;
				}
				array[preIndex + gap] = current;				
			}
			gap /= 2;
		}
	}

}
