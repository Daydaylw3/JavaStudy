package shenwu;

import java.util.ArrayList;


/**
 * 这个类是为了实现这样的需求：
 * 礼包1：充值rmb 256 得 1875金；礼包2：充值rmb 128 得 813金；
 * 礼包3：充值rmb 68 得375金；礼包4：充值rmb 30 得163金
 * 若想得到 2400金，则最省钱的情况下要花多少 rmb ??? 如何搭配礼包的购买???
 * 
 * 20180427 by tanlt
 * 暂时只实现了穷举法
 * 
 * 20180502 by tanlt
 * 期待实现限时数量
 * 尝试通过贪心算法实现
 * */
public class CalculateMoney {
	
	public static final int ACost = 648;
	public static final int BCost = 258;
	public static final int CCost = 128;
	public static final int DCost = 68;
	public static final int ECost = 30;
	public static final int FCost = 12;
	
	public static final int AGet = 5000;
	public static final int BGet = 1875;
	public static final int CGet = 812;
	public static final int DGet = 375;
	public static final int EGet = 162;
	public static final int FGet = 62;
	
	
	/*
	 * for语句的顺序:
	 * for(state1; state2; state3){
	 * 		state4;
	 * }
	 * 1、先state1
	 * 2、判断state2
	 * 3、为假跳出for循环；为真state4，然后state3
	 * 4、然后2、3步骤循环直至state2为假
	 * */
	public static void count(int target) {
		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
		ArrayList<GiftBag1> cl = new ArrayList<GiftBag1>();
		CalculateMoney calcu = new CalculateMoney();
		for (; AGet * a + BGet * b + CGet * c + DGet * d + EGet * e  + FGet * f < (FGet + target); a++) {
			int tempA = AGet * a;
			for(; tempA + BGet * b + CGet * c + DGet * d + EGet * e  + FGet * f < (FGet + target); b++) {
				int tempB = tempA + BGet * b;
				for(; tempB + CGet * c + DGet * d + EGet * e  + FGet * f < (FGet + target); c++) {
					int tempC = tempB + CGet * c;
					for(; tempC + DGet * d + EGet * e  + FGet * f < (FGet + target); d++) {
						int tempD = tempC + DGet * d;
						for(; tempD + EGet * e  + FGet * f < (FGet + target); e++) {
							int tempE = tempD + EGet * e;
							for(; tempE + FGet * f < (FGet + target); f++) {
								if(tempE + FGet * f >= target) {
									//符合要求
									GiftBag1 ca = calcu.new GiftBag1(a, b, c, d, e, f, tempE + FGet * f, 
											ACost * a + BCost * b + CCost * c + DCost * d + ECost * e  + FCost * f);
									cl.add(ca);
								}
							}
							f = 0;
						}
						e = 0;
					}
					d = 0;
				}
				c = 0;
			}
			b = 0;
		}
		
		calcu.sort(cl);
		for(GiftBag1 c1 : cl) {
			if(c1.getAll() < 7)
				System.out.println("花费:" + c1.getRmb() + "元" + 
						", 购买礼包数:" + c1.getAll() + 
						(c1.getA() > 0 ? (", 648元礼包=" + c1.getA()) : "") + 
						(c1.getB() > 0 ? (", 258元礼包=" + c1.getB()) : "") + 
						(c1.getC() > 0 ? (", 128元礼包=" + c1.getC()) : "") + 
						(c1.getD() > 0 ? (", 68元礼包=" + c1.getD()) : "") + 
						(c1.getE() > 0 ? (", 30元礼包=" + c1.getE()) : "") + 
						(c1.getF() > 0 ? (", 12元礼包=" + c1.getF()) : "") +
						", gold = " + c1.getGold());
		}
	}
	
	public static void count(int target, int limitTimes) {
		ArrayList<GiftBag> bag = new ArrayList<GiftBag>();
		bag.add(new GiftBag("限时648", 648, 5000));
		bag.add(new GiftBag("限时258", 258, 1875));
		bag.add(new GiftBag("限时128", 128, 812));
		bag.add(new GiftBag("限时68", 68, 375));
		bag.add(new GiftBag("限时30", 30, 162));
		bag.add(new GiftBag("限时12", 12, 62));
		bag.add(new GiftBag("普通648", 648, 4000));
		bag.add(new GiftBag("普通258", 258, 1500));
		bag.add(new GiftBag("普通128", 128, 650));
		bag.add(new GiftBag("普通68", 68, 300));
		bag.add(new GiftBag("普通30", 30, 130));
		bag.add(new GiftBag("普通12", 12, 50));
		
	}
	private static int remain(ArrayList<GiftBag> bag, int target) {
		int remain = target;
		for(GiftBag b : bag)
			remain -= (b.getGold() * b.getNumber());
		return remain;
	}
	private static int getMin(int n1, int n2) {
		return n1 >= n2 ? n2 : n1;
	}
	private static int getMin(int n1, int n2, int... n) {
		int min = getMin(n1, n2);
		for(int i : n) {
			if(i < min)
				min = i;
		}
		return min;
	}
	
	public static void main(String[] args) {
		count(998+1980-268-62);
	}
	
	public void sort(ArrayList<GiftBag1> array) {
		if(array.size() == 0)
			return ;
		for(int i = 0; i < array.size(); i ++) {
			for(int j = 0; j < array.size() - i - 1; j ++) {
				//这里是比较因子
				double count1 = 1.5 * array.get(j).getRmb() + 23.5 * array.get(j).getAll();
				double count2 = 1.5 * array.get(j + 1).getRmb() + 23.5 * array.get(j + 1).getA();
				if (count1 > count2) {
					GiftBag1 temp1 = array.get(j);
					GiftBag1 temp2 = array.get(j + 1);
					array.set(j, temp2);
					array.set(j + 1, temp1);
				}
			}
		}
	}
	
	public class GiftBag1{
		private int a, b, c, d, e, f;
		private int gold;
		private int rmb;
		
		GiftBag1(int a, int b, int c, int d, int e, int f, int gold, int rmb){
			this.a = a; this.b = b; this.c = c; this.d = d; this.e = e; this.f = f;
			this.gold = gold;
			this.rmb = rmb;
		}
		public int getAll() { return a + b + c + d + e + f; }
		
		public int getA() { return a; }
		
		public void setA(int a) { this.a = a; }
		
		public int getB() { return b; }
		
		public void setB(int b) { this.b = b; }
		
		public int getC() { return c; }
		
		public void setC(int c) { this.c = c; }
		
		public int getD() { return d; }
		
		public void setD(int d) { this.d = d; }
		
		public int getE() { return e; }
		
		public void setE(int e) { this.e = e; }
		
		public int getF() { return f; }
		
		public void setF(int f) { this.f = f; }
		
		public int getGold() { return gold; }
		
		public void setGold(int gold) { this.gold = gold; }
		
		public int getRmb() { return rmb; }
		
		public void setRmb(int rmb) { this.rmb = rmb; }
	}

}
