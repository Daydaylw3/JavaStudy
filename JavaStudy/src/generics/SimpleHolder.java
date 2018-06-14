package generics;

public class SimpleHolder {
	private Object obj;
	public void set(Object obj) { this.obj = obj; }
	public Object get() { return obj; }
	public static void main(String[] args) {
		SimpleHolder sh = new SimpleHolder();
		sh.set("item");
		String s = (String)sh.get();
	}
}

/*
 * 反编译 javap -c SimpleHolder
Compiled from "SimpleHolder.java"
public class generics.SimpleHolder {
  public generics.SimpleHolder();
    Code:
       0: aload_0
       1: invokespecial #10                 // Method java/lang/Object."<init>":()V
       4: return

  public void set(java.lang.Object);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #18                 // Field obj:Ljava/lang/Object;
       5: return

  public java.lang.Object get();
    Code:
       0: aload_0
       1: getfield      #18                 // Field obj:Ljava/lang/Object;
       4: areturn

  public static void main(java.lang.String[]);
    Code:
       0: new           #1                  // class generics/SimpleHolder
       3: dup
       4: invokespecial #24                 // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: ldc           #25                 // String item
      11: invokevirtual #27                 // Method set:(Ljava/lang/Object;)V
      14: aload_1
      15: invokevirtual #29                 // Method get:()Ljava/lang/Object;
      18: checkcast     #31                 // class java/lang/String
      21: astore_2
      22: return
}
 * */
