package holding;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class ForEachCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection<String> cs = new LinkedList<String>();
		Collections.addAll(cs, "oh my lady gaga".split(" "));
		for(String s : cs)
			System.out.print(" '" + s + "' ");
	}

}
