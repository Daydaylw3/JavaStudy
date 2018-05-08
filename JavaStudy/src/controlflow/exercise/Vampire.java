package controlflow.exercise;
/**
 * 练习10：
 * 		吸血鬼数字指的是指数位数为偶数的数字，可以由一对数字相乘得到，而这对数字各包含
 * 乘积的一半位数的数字，其中从最初的数字中选取的数字可以任意排序，以两个0结尾的数字
 * 是不允许的，例如，下列数字都是吸血鬼数字：
 * 1260 = 21 * 60
 * 1827 = 21 * 87
 * 2187 = 27 * 81
 * 		写一个程序，找出4位数的所有吸血鬼数字
 * */
public class Vampire {

	public void find() {
		int count = 0, times = 0;
		for(int num1 = 10; num1 < 100; num1++) {
			for(int num2 = num1 + 1; num2 < 100; num2++) {	//不存在ab * ab =为吸血鬼数字
				if(num1 * num2 % 100 == 0 || (num1 * num2 - num1 - num2) % 9 != 0)
					continue;
				int[] target = {
						num1 / 10,
						num1 % 10,
						num2 / 10,
						num2 % 10
				};
				if (compare(target, num1 * num2)) {
					count++;
					System.out.println("Vampire: " + num1 * num2 + " = " + num1 + " * " + num2);
				}
				times++;
			}
		}
		System.out.println("find " + count + "\nfind " + times + " times");
	}
	
	private static boolean compare(int[] target, int num) {
		int temp = num;
		int[] tar = new int[4];
		for(int i = 0; i < 4; i++) {
			tar[i] = target[i];
		}
		boolean flag = true;
		for(int i = 0; i < 4; i++) {
			int j = temp % 10;	//取4位数的一个数字
			temp = temp / 10;
			//将这个数字和target数组中所有数字比对
			for(int k = 0; k < 4; k++) {
				if(j == tar[k]) {
					tar[k] = -1;
					flag = true;
					break;
				}
				flag = false;
			}
			//若有一个数字与所有的数字不同，则返回false
			if(!flag) {
				return false;
			}
//			return flag;
		}
		return flag;
	}
	
	public static	 void main(String[] args) {
//		System.out.println(compare(new int[] {9,3,4,7}, 4371));
		new Vampire().find();
//		java.util.Arrays.equals(a, a2);
	}

}
