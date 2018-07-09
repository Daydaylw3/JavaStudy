package xiaohui.dynamicprogramming.eggs;

public class Eggs {

	public static int getMinSteps(int eggNum, int floorNum) {
		if(eggNum < 1 || floorNum < 1)
			return 0;
		if(eggNum == 1)
			return floorNum;
		if(floorNum == 1)
			return 1;
		
		//备忘录，存储鸡蛋数量一定的情况下，floorNum层楼条件下的最优尝试次数
		int[][] cache = new int[3][floorNum + 1];
		int[] preFloor = new int[floorNum + 1];
		int[] curFloor = new int[floorNum + 1];
		
		//初始化
		for(int i = 0; i <= floorNum; i++)
			preFloor[i] = i;
		curFloor[0] = 0;
		curFloor[1] = 1;
		
		//计算
		//从二个鸡蛋开始往上增加
		for(int n = 2; n <= eggNum; n++) {
			for(int m = 1; m <= floorNum; m++) {
				int tmp = Integer.MAX_VALUE;
				int k;
				for(int x = 1; x <= m; x++) {
					//k 为 F(N, M-X)+1 和 F(N-1, X-1)+1的最大值
					//tmp为各种X的情况下的最小值
					//扔鸡蛋的楼层从1到j枚举一遍，如果当前算出的尝试次数
					//小于上一次算出的尝试次数，则取代上一次的尝试次数
					k = getMaxNum((cache[1][x - 1] + 1), (cache[2][m - x] + 1));
					tmp = getMinNum(k, tmp);
				}
				cache[1][m] = tmp;
			}
		}
		return cache[eggNum][floorNum];
	}
	
	private static int getMinNum(int x1, int x2) {
		return x1 < x2 ? x1 : x2;
	}
	
	private static int getMaxNum(int x1, int x2) {
		return x1 > x2 ? x1 : x2;
	}
	
	public static void main(String[] args) {
		System.out.println(getMinSteps(2, 2));
	}
}
