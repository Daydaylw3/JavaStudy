package sort;

public class CountingSort implements Sort {

	@Override
	public void sort(int[] array) {
		if(array.length == 0)
			return;
		int min = array[0], max = array[0];
		//找到最大和最小的数字
		for(int i = 1; i < array.length; i++) {
			if (array[i] > max)
				max = array[i];
			if(array[i] < min)
				min = array[i];
		}
		//对所有的计数累加
		int[] count = new int[max - min + 1];
		for(int i = 0; i < array.length; i++) {
			  count[array[i] - min]++;
		}
		//回写 
		int index = 0, i = 0;
		while(index < array.length) {
			if(count[i] != 0) {
				array[index] = i + min;
				count[i] --;
				index ++;
			}else {
				i++;
			}
		}
	}

}
