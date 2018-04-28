package study;

import java.util.ArrayList;


/**
 * 这个类是为了实现这样的需求：
 * 礼包1：充值rmb 256 得 1875金；礼包2：充值rmb 128 得 813金；
 * 礼包3：充值rmb 68 得375金；礼包4：充值rmb 30 得163金
 * 若想得到 2400金，则最省钱的情况下要花多少 rmb ??? 如何搭配礼包的购买???
 * 
 * 20180427 by tanlt
 * 暂时只实现了穷举法
 * */
public class CalculateMoney {
	public static final int U = 1875;
	public static final int X = 813;
	public static final int Y = 375;
	public static final int Z = 163;
	
	public static void main(String[] args) {
		int u = 0, x = 0, y = 0, z = 14;
		ArrayList<Calcu> c = new ArrayList<Calcu>();
		CalculateMoney calcu = new CalculateMoney();
		for(; U * u + X * x + Y * y + Z * z < (Z + 2400); u++) {
			for (; U * u + X * x + Y * y + Z * z <  (Z + 2400); x++) {
				for(; U * u + X * x + Y * y + Z * z <  (Z + 2400); y ++) {
					for(; U * u + X * x + Y * y + Z * z <  (Z + 2400); z ++) {
						if((U * u + X * x + Y * y + Z * z <  (Z + 2400)) && (U * u + X * x + Y * y + Z * z >= 2400)) {
							Calcu ca = calcu.new Calcu(u, x, y, z, (U * u + X * x + Y * y + 163 * z),  (258 * u + 128 * x + 68 * y + 30 * z));
							c.add(ca);
						}
					}
					z = 0;
				}
				y = 0;
				z = 0;
			}
			x = 0;
			y = 0;
			z = 0;
		}
		calcu.sort(c);
		for(Calcu c1 : c) {
			System.out.println("rmb = " + c1.getRmb() + 
					(c1.getU() > 0 ? (", 258元礼包 = " + c1.getU()) : "") + 
					(c1.getX() > 0 ? (", 128元礼包 = " + c1.getX()) : "") + 
					(c1.getY() > 0 ? (", 68元礼包 = " + c1.getY()) : "") + 
					(c1.getZ() > 0 ? (", 30元礼包 = " + c1.getZ()) : "") + 
					", gold = " + c1.getGold());
		}
	}
	
	public void sort(ArrayList<Calcu> array) {
		if(array.size() == 0)
			return ;
		for(int i = 0; i < array.size(); i ++) {
			for(int j = 0; j < array.size() - i - 1; j ++) {
				if (array.get(j).getRmb() > array.get(j + 1).getRmb()) {
					Calcu temp1 = array.get(j);
					Calcu temp2 = array.get(j + 1);
					array.set(j, temp2);
					array.set(j + 1, temp1);
				}
			}
		}
	}
	
	public class Calcu{
		private int u;
		private int x;
		private int y;
		private int z;
		private int gold;
		private int rmb;
		Calcu(int u, int x, int y, int z, int gold, int rmb){
			this.u = u;
			this.x = x;
			this.y = y;
			this.z = z;
			this.gold = gold;
			this.rmb = rmb;
		}
		public int getU() {
			return u;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public int getZ() {
			return z;
		}
		public int getGold() {
			return gold;
		}
		public int getRmb() {
			return rmb;
		}
		public void setU(int u) {
			this.u = u;
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setZ(int z) {
			this.z = z;
		}
		public void setGold(int gold) {
			this.gold = gold;
		}
		public void setRmb(int rmb) {
			this.rmb = rmb;
		}
	}

}
