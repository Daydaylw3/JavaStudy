package generics;

public class GenericHolder<T> {
	private T obj;
	public void set(T t) { obj = t; }
	public T get() { return obj; }
	public static void main(String[] args) {
		GenericHolder<String> gh = 
				new GenericHolder<String>();
		gh.set("item");
		String s = gh.get();
	}
}

/*
 * 
 *	反编译代码
 Compiled from "GenericHolder.java"
public class generics.GenericHolder<T> {
  public generics.GenericHolder();
    Code:
       0: aload_0
       1: invokespecial #12                 // Method java/lang/Object."<init>":()V
       4: return

  public void set(T);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #23                 // Field obj:Ljava/lang/Object;
       5: return

  public T get();
    Code:
       0: aload_0
       1: getfield      #23                 // Field obj:Ljava/lang/Object;
       4: areturn

  public static void main(java.lang.String[]);
    Code:
       0: new           #1                  // class generics/GenericHolder
       3: dup
       4: invokespecial #31                 // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: ldc           #32                 // String item
      11: invokevirtual #34                 // Method set:(Ljava/lang/Object;)V
      14: aload_1
      15: invokevirtual #36                 // Method get:()Ljava/lang/Object;
      18: checkcast     #38                 // class java/lang/String
      21: astore_2
      22: return
} 
 *
 */