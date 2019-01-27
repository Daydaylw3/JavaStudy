package io.serialized;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 18.12.3 使用"持久性"
 * */
public class RecoverCADState {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = 
				new ObjectInputStream(new FileInputStream("src/io/serialized/CADState.out"));
		// read in the same order they were written
		List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>)in.readObject();
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>)in.readObject();
		System.out.println(shapes);
	}
}
