package controlflow.exercise;

import java.util.Random;

/**
 * 写一个程序，产生25个int类型的随机数。对于每一个随机数，使用if-else语句来将其分类
 * 为大于、小于，或者等于紧随它而随机生成的值。
 * */
class RandomNmuberClassify {
	Random rand = new Random(47);
	public void getRandAndClassify(){
		int[] num = new int[26];
		for(int i = 0; i < 26; i++) 
			num[i] = rand.nextInt(50);
		for(int i = 0, j = 0; j < 25; i++, j++) {
			
		}
	}
}

public class Exercise2 {

	public static void main(String[] args) {

	}

}
