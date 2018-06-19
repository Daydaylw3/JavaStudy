package generics;

import java.util.List;

interface SuperPower {}
interface XRayVision extends SuperPower {
	void seeThroughWalls();
}
interface SuperHearing extends SuperPower {
	void hearSubtleNoises();
}
interface SuperSmell extends SuperPower {
	void trackBySmell();
}
//每个超级英雄都持有一种超能力
//而不是继承一种超能力
class SuperHero<POWER extends SuperPower> {
	POWER power;
	SuperHero(POWER power) {
		this.power = power;
	}
	//超级英雄要用超能力来做事
	POWER getPower() { return power; }
}

class SuperSleuth<POWER extends XRayVision>
extends SuperHero<POWER> {
	SuperSleuth(POWER power) {
		super(power);
	}
	void see() { power.seeThroughWalls(); }
}

class CanineHero<POWER extends SuperHearing & SuperSmell> 
extends SuperHero<POWER> {
	CanineHero(POWER power) {
		super(power);
	}
	void hear() { power.hearSubtleNoises(); }
	void smell() { power.trackBySmell(); }
}

class SuperHearSmell implements SuperHearing, SuperSmell {
	@Override
	public void trackBySmell() {	}
	@Override
	public void hearSubtleNoises() {}
}

class DogBoy extends CanineHero<SuperHearSmell> {
	DogBoy() {
		super(new SuperHearSmell());
	}
}

public class EpicBattle {
	static <POWER extends SuperHearing>
	void useSuperHearing(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
	}
	static <POWER extends SuperHearing & SuperSmell>
	void superFind(SuperHero<POWER> hero) {
		hero.getPower().hearSubtleNoises();
		hero.getPower().trackBySmell();
	}
	public static void main(String[] args) {
		DogBoy dogBoy = new DogBoy();
		useSuperHearing(dogBoy);
		superFind(dogBoy);
		List<? extends SuperHearing> audioBoys;
		//通配符被限定为单一边界
//		LIst<? extends SuperHearing & SuperSmell> dogBoys;
	}
}
