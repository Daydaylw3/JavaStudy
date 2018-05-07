package shenwu;

public class GiftBag {
	private String id;
	private int price;
	private int Gold;
	private int number;
	protected GiftBag(String id, int p, int g, int n) {
		this.id = id;
		price = p;
		Gold = g;
		number = n;
	}
	public GiftBag(String id, int p, int g) {
//		GiftBag(id, p, g, 0);
		this.id = id;
		price = p;
		Gold = g;
		number = 0;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getGold() {
		return Gold;
	}
	public void setGold(int Gold) {
		this.Gold = Gold;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
