package generics;

/**
 * 泛型数组
 * 
 * 对比GenericArray.java，转型挪了地方，内部表示是Object[]而不是T[]
 * 当你调用get()，内部试图将Object转型为T，这是正确的；但是你调用
 * rep()，则还是尝试将Object[]转型为T[]，这仍旧是不正确的。
 * 因此，没有办法推翻底层的数组类型，他只能是Object[]
 * */
public class GenericArray2<T> {
	private Object[] array;
	public GenericArray2(int sz) {
		array = new Object[sz];
	}
	public void put(int index, T item) {
		if(index < array.length)
			array[index] = item;
	}
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if(index < array.length)
			return (T)array[index];
		else
			throw new RuntimeException();
	}
	@SuppressWarnings("unchecked")
	public T[] rep() {
		return (T[])array;
	}
	public static void main(String[] args) {
		GenericArray2<Integer> gai = 
				new GenericArray2<Integer>(10);
		for(int i = 0; i < 10; i++)
			gai.put(i, new Integer(i));
		for(int i = 0; i < 10; i++)
			System.out.print(gai.get(i) + " ");
		System.out.println();
		try {
			Integer[] ia = gai.rep();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
