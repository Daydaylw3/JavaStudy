package socket.keepalive;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName socket.keepalive.KeepAlive.java
 * @Description 维持连接的心跳对象
 * 
 * @author dayday
 * @date 2019年3月4日
 */
public class KeepAlive implements Serializable {
	
	private static final long serialVersionUID = -6079906747951349075L;
	
	
	//
	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
				"\t维持连接包";
	}
}
