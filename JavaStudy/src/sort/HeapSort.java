package sort;

public class HeapSort implements Sort {
	
	private int len;
		
	@Override
	public void sort(int[] array) {
		len = array.length;
		if(len == 0)
			return ;
		heapSort(array);
	}

	private void heapSort(int[] array) {
		//1.构建一个最大堆
		buildMaxHeap(array);
		while(len > 0) {
			//2.循环将堆首位（最大值）与末位交换，然后重新调整最大堆
			swap(array, 0, len - 1);
			len --;
			adjustHeap(array, 0);
		}
	}
	
	private void buildMaxHeap(int[] array) {
		//从最后一个非叶子节点开始向上构建最大堆
		for(int i = (len - 1) / 2; i >= 0; i--)
			adjustHeap(array, i);
	}
	
	private void adjustHeap(int[] array, int i) {
		int maxIndex = i;
		//如果有左子树，且左子树大于父节点，则将最大指针指向左子树
		if(i * 2 < len && array[i * 2] > array[maxIndex])
			maxIndex = i * 2;
		//如果有右子树，且右子树大于父节点，则将最大指针指向右子树
		if(i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
			maxIndex = i * 2 + 1;
		//如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
		if(maxIndex != i) {
			swap(array, maxIndex, i);
			adjustHeap(array, maxIndex);
		}
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
