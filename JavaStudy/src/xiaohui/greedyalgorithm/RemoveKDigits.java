package xiaohui.greedyalgorithm;

public class RemoveKDigits {
	
	public static String removeKDigits(String num, int k) {
		String numNew = num;
		StringBuilder sb = new StringBuilder(numNew);
		for (int i = 0, j = 0; i < k; i++, j++) {
			// 从左至右遍历 找到第一个比自己右侧数字大的数字 并删除
			for (; j < numNew.length() - i - 1; j++) {
				if (numNew.charAt(j) > numNew.charAt(j + 1)) {
//					System.out.print(j + " ");
					sb.deleteCharAt(j - i);
					break;
				}
			}
		}
		// 若找不到，则说明已经排过序，删除最后一个数字
		numNew = sb.substring(0, numNew.length() - k);
		return numNew;
	}
	
	public static String removeKDigitsNew(String num, int k) {
		// 132425
		if (num.length() <= k) {
			return "0";
		}
		char[] stack = new char[num.length() - k];
		int top = 0;
		stack[top] = num.charAt(0);
		for (int i = 1; i < num.length(); i++) {
			if (k > 0 && stack[top] > num.charAt(i)) {
				// 若栈顶元素大于，栈顶出栈
				stack[top] = num.charAt(i);
				k --;
			} else if (top < stack.length -1) {
				// 不大于，则直接入栈
				stack[++top] = num.charAt(i);
			}
		}
		
		return new String(stack);
	}
	
	public static void main(String[] args) {
		System.out.println(removeKDigits("132425", 2));
		System.out.println(removeKDigits("12", 1));
		System.out.println(removeKDigits("541270936", 1));
		System.out.println(removeKDigitsNew("132425", 2));
		System.out.println(removeKDigitsNew("12", 1));
		System.out.println(removeKDigitsNew("541270936", 1));
		System.out.println(removeKDigitsNew("312", 2));
		System.out.println(removeKDigitsNew("123", 2));
		System.out.println(removeKDigitsNew("321", 2));
		System.out.println(removeKDigitsNew("321", 4));
	}
	
}
