package generics;

/**
 * 泛型数组
 * 
 * 因为有了擦除，数组的运行时类型就只能是Object[]，如果我们立即
 * 将其转型为T[]，那么在编译器该数组的实际类型就将丢失， 而编译
 * 器可能会错过某些潜在的错误检查
 * */
public class GenericArray<T> {
	private T[] array;
	@SuppressWarnings("unchecked")
	public GenericArray(int sz) {
		array = (T[])new Object[sz];
	}
	public void put(int index, T item) {
		if(index < array.length)
			array[index] = item;
	}
	public T get(int index) {
		if(index < array.length)
			return array[index];
		else
			throw new RuntimeException();
	}
	public T[] rep() { return array; }
	public static void main(String[] args) {
		GenericArray<Integer> gai = 
				new GenericArray<Integer>(10);
		//运行时报错:ClassCaseException
//		Integer[] ia = gai.rep();
		Object[] oa = gai.rep();
		
	}
}
