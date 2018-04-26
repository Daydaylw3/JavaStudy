package study;

import java.util.Map;
import java.util.TreeMap;

public class CalculateMoney {

	public static void main(String[] args) {
		int x = 0, y = 0, z = 14;
		Map<Integer> result = new TreeMap<Integer>();
		for (; 813 * x + 375 * y + 163 * z < 2563; x++) {
			for(; 813 * x + 375 * y + 163 * z < 2563; y ++) {
				for(; 813 * x + 375 * y + 163 * z < 2563; z ++) {
//					System.out.println("x = " + x + ", y = " + y + ", z = " + z + ", total = " + (813 * x + 375 * y + 163 * z));
					if(813 * x + 375 * y + 163 * z < 2563 && 813 * x + 375 * y + 163 * z >= 2400)
						System.out.println("x = " + x + ", y = " + y + ", z = " + z +
								", total = " + (813 * x + 375 * y + 163 * z) + ", money = " + (128 * x + 68 * y + 30 * z));
				}
				z = 0;
			}
			y = 0;
			z = 0;
		}
		
	}

}
