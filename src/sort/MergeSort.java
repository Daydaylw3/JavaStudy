package sort;

import java.util.Arrays;

public class MergeSort implements Sort {

	@Override
	public void sort(int[] array) {
		if(array.length < 2)
			return;
		
		int mid = array.length / 2;
		int[] left = Arrays.copyOfRange(array, 0, mid);
		int[] right = Arrays.copyOfRange(array, mid, array.length);
	
		sort(left);		//递归
		sort(right);	//递归
		sort(left, right, array);
	}
	
	private void sort(int[] left, int[] right, int[] result) {
		if(result.length != left.length + right.length)
			return;
		for(int index = 0, l = 0, r = 0; index < result.length; index ++) {
			if(l >= left.length) {
				result[index] = right[r++];
			}else if(r >= right.length) {
				result[index] = left[l++];
			}else if(left[l] > right[r]) {
				result[index] = right[r++];
			}else {
				result[index] = left[l++];
			}
		}
	}

}
