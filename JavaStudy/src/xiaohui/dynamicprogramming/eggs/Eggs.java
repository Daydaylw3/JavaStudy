package xiaohui.dynamicprogramming.eggs;

import java.util.Arrays;

public class Eggs {

	public static int getMinSteps(int eggNum, int floorNum) {
		if(eggNum <= 0 || floorNum <= 0)
			return -1;
		if(eggNum == 1)
			return floorNum;
		if(floorNum == 1)
			return 1;
		
		//备忘录，存储鸡蛋数量一定的情况下，floorNum层楼条件下的最优尝试次数
		int[] preFloor = new int[floorNum + 1];
		int[] curFloor = new int[floorNum + 1];
		
		//初始化
		for(int i = 0; i <= floorNum; i++)
			preFloor[i] = i;
		curFloor[0] = 0;
		curFloor[1] = 1;
		
		for(int i = 0; i < preFloor.length; i++)
			System.out.print(preFloor[i] + " ");
		System.out.println();
		
		//计算 计算 eggNum - 1 轮出结果
		for(int n = 2; n <= eggNum; n++) {
			for(int m = 1; m <= floorNum; m++) {
				int k = Integer.MAX_VALUE;
				for(int x = 1; x <= m; x++) {
					k = getMinNum(k, 1 + getMaxNum(curFloor[m - x], preFloor[x - 1]));
				}
				curFloor[m] = k;
			}
			preFloor = Arrays.copyOf(curFloor, floorNum + 1);
			for(int i = 0; i < preFloor.length; i++)
				System.out.print(preFloor[i] + " ");
			System.out.println();
		}
		return curFloor[floorNum];
	}
	
	private static int getMinNum(int x1, int x2) {
		return x1 < x2 ? x1 : x2;
	}
	
	private static int getMaxNum(int x1, int x2) {
		return x1 > x2 ? x1 : x2;
	}
	
	public static void main(String[] args) {
		System.out.println(getMinSteps(2,100));
	}
}
