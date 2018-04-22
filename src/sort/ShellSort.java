package sort;

public class ShellSort implements Sort {

	@Override
	public void sort(int[] array) {
		if(array.length == 0)
			return;
		int len = array.length;
		int gap = len / 2;
		int temp;
		
		while(gap > 0) {
			for(int i = gap; i < len; i ++) {
				temp = array[i];
				int preIndex = i - gap;
				while(preIndex >= 0 && array[preIndex] > temp) {
					array[preIndex + gap] = array[preIndex];
					preIndex -= gap;
				}
				array[preIndex + gap] = temp;
			}
			gap = gap / 2;
		}
		
	}

}
