package containers;
/*
 * 17.10.3 微基准测试的危险
 * {RunByHand}
 * 需要手工添加参数: "lower" or "upper"
 * */
public class RandomBounds {
	static void usage() {
		System.out.println("Usage: ");
		System.out.println("\tRandomBounds lower");
		System.out.println("\tRandomBounds upper");
		System.exit(1);
	}
	public static void main(String[] args) {
		if(args.length != 1) usage();
		if(args[0].equals("lower")) {
			while(Math.random() != 0.0)
				;	//Keep trying
			System.out.println("Produced 0.0 !!");
		} else if(args[0].equals("upper")) {
			while(Math.random() != 1.0)
				;	//Keep trying
			System.out.println("Produced 1.0 !!");
		} else 
			usage();
	}
}
/*
 * 在两种情况中, 都需要手工终止程序,因此看上去好像Math.random()永远都不会产生
 * 0.0或者1.0  但是这正是这类试验产生误导的所在
 * */
