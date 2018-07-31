package sort;

/**
 * 可以理解冒泡排序是一个大的数字慢慢往上浮
 * 也可以理解冒泡排序是从头开始两两比较，如果下面大于上面
 * 则交换顺序（物理上看就是大的数字往上浮），则每一轮遍历
 * 都可以将遍历的最大的数字浮到最高
 * 
 * 优化：针对 3 2 4 5 6 7 8 9这种序列，可以设置一个flag
 * 每一次遍历后看后面的序列是否被排序，若后续的序列已经排
 * 序成功，则终止
 * 
 * 鸡尾酒排序：双向的冒泡排序
 * */
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
