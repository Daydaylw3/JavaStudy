package strings;

import java.util.Formatter;

//友情提示：要切换一个正常点的字体
public class Receipt {
	private double total = 0;
	private Formatter f = new Formatter(System.out);
	//练习4.令所有宽度都由一个常亮控制
	private final int WIDTH = 15;
	public void printTitle() {
		f.format("%-" + WIDTH +".15s %5s %10s\n", "Item", "Qty", "Price");
		f.format("%-" + WIDTH +".15s %5s %10s\n", "----", "---", "-----");
	}
	public void print(String name, int qty, double price) {
		f.format("%-" + WIDTH + "." + WIDTH + "s %5d %10.2f\n", name, qty, price);
		total += price;
	}
	public void printTotal() {
		f.format("%-" + WIDTH + "s %5s %10s\n", "", "", "");
		f.format("%-" + WIDTH + "s %5s %10.2f\n", "Tax", "", total*0.06);
		f.format("%-" + WIDTH + "s %5s %10s\n", "", "", "-----");
		f.format("%-" + WIDTH + "s %5s %10.2f\n", "Total", "", total * 1.06);
	}
	public static void main(String[] args) {
		Receipt receipt = new Receipt();
		receipt.printTitle();
		receipt.print("dayday", 4, 4);
		receipt.print("Visan", 2, 5);
		receipt.print("dayday love visan", 10, 520);
		receipt.printTotal();
	}
}
