package containers;
/*
 * 17.9 散列与散列码
 * */
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SpringDetector {
	public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception{
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog, Prediction> map = 
				new HashMap<Groundhog, Prediction>();
		for(int i = 0; i < 10; i ++)
			map.put(ghog.newInstance(i), new Prediction());
		System.out.println(" --- map ---\n" + map);
		Groundhog gh = ghog.newInstance(3);
		System.out.println("Looking up prediction for " + gh);
		if(map.containsKey(gh))
			System.out.println(map.get(gh));
		else
			System.out.println("key not found: " + gh);
	}
	public static void main(String[] args) throws Exception{
		detectSpring(Groundhog.class);
		detectSpring(Groundhog2.class);
	}
}
