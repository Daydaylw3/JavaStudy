package tuanzhang.jvm;

/**
* eclipse中设置最大堆最小堆：
* Preference-->Java-->InstalledJREs-->Edit-->Default VM arguments-->输入-Xms20m -Xmx20m
* 运行时，不断在堆中创建OOMObject类的实例对象，且while执行结束之前，GC Roots(代码中的oomObjectList)到对
* 象(每一个OOMObject对象)之间有可达路径，垃圾收集器就无法回收它们，最终导致内存溢出。
*/

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> oomObjectList = new ArrayList<>();
		while (true) {
			oomObjectList.add(new OOMObject());
		}
	}

}
