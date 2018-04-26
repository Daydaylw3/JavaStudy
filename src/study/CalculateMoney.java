package study;

import java.util.ArrayList;

public class CalculateMoney {

	public static void main(String[] args) {
		int u = 0, x = 0, y = 0, z = 14;
		ArrayList<Calcu> c = new ArrayList<Calcu>();
		CalculateMoney calcu = new CalculateMoney();
//		Calcu ca = new CalculateMoney().new Calcu();
		for(; 1875 * u + 813 * x + 375 * y + 163 * z < 2563; u++) {
			for (; 1875 * u + 813 * x + 375 * y + 163 * z < 2563; x++) {
				for(; 1875 * u + 813 * x + 375 * y + 163 * z < 2563; y ++) {
					for(; 1875 * u + 813 * x + 375 * y + 163 * z < 2563; z ++) {
//					System.out.println("x = " + x + ", y = " + y + ", z = " + z + ", total = " + (813 * x + 375 * y + 163 * z));
						if(1875 * u + 813 * x + 375 * y + 163 * z < 2563 && 813 * x + 375 * y + 163 * z >= 2400) {
//						System.out.println("x = " + x + ", y = " + y + ", z = " + z +
//								", total = " + (813 * x + 375 * y + 163 * z) + ", rmb = " + (128 * x + 68 * y + 30 * z));
							Calcu ca = calcu.new Calcu(u, x, y, z, (1875 * u + 813 * x + 375 * y + 163 * z),  (258 * u + 128 * x + 68 * y + 30 * z));
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
			System.out.println("rmb = " + c1.getRmb() + ", 258元礼包 = " + c1.getU() + ", 128元礼包 = " + c1.getX() + ", 68元礼包 = " + c1.getY() + ", 30元礼包 = " + c1.getZ() + ", gold = " + c1.getGold());
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
