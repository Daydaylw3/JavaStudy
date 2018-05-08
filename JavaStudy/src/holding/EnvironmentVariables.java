package holding;

import java.util.Map;

//可以显示操作系统环境变量
public class EnvironmentVariables {

	public static void main(String[] args) {
		for(Map.Entry entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

}
