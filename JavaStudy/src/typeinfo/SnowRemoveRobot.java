package typeinfo;

import java.util.Arrays;
import java.util.List;

public class SnowRemoveRobot implements Robot {
	private String name;
	
	public SnowRemoveRobot(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String model() {
		return "SnowBot Series 11";
	}

	@Override
	public List<Operation> operations() {
		return Arrays.asList(
			new Operation() {
	
				@Override
				public String description() {
					return name + " can shovel snow";
				}
	
				@Override
				public void command() {
					System.out.println(name + " is shoveling snow~");
				}
				
			},
			new Operation() {
	
				@Override
				public String description() {
					return name + " can chip ice";
				}
	
				@Override
				public void command() {
					System.out.println(name + " is chipping ice~");
				}
				
			}
		);
	}
	
	public static void main(String[] args) {
		Robot robot = new SnowRemoveRobot("kiki");
		Robot.Test.test(robot);
	}
	

}
