package shenwu;

public class GiftBag {
	private int price;
	private int normalGold;
	private int limitGold;
	private int number;
	public GiftBag(int p, int normal, int limit) {
		price = p;
		normalGold = normal;
		limitGold = limit;
		number = 0;
	}
	protected GiftBag(int p, int normal, int limit, int n) {
		price = p;
		normalGold = normal;
		limitGold = limit;
		number = n;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNormalGold() {
		return normalGold;
	}
//	public void setNormalGold(int normalGold) {
//		this.normalGold = normalGold;
//	}
	public int getLimitGold() {
		return limitGold;
	}
//	public void setLimitGold(int limitGold) {
//		this.limitGold = limitGold;
//	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
