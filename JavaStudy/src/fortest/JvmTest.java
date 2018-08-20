package fortest;

public class JvmTest {
	public static void main(String[] args) {
		//通过命令行方式运行：java -Xmx20m JvmTest
		System.out.println(Runtime.getRuntime().maxMemory()/1000);
	}
}
