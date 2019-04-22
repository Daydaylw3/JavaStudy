package concurrency;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName concurrency.MyArrayBlockingQueue
 * @Description 自创阻塞队列
 * 
 * @author dayday
 * @date 2019年4月20日
 */
public class MyArrayBlockingQueue<T> {
	private Object[] array;
	
	private int size;
	
	private int takeIndex;
	
	private int putIndex;
	
	private int count;
	
	private final Lock lock;
	
	private final Condition notEmpty;
	
	private final Condition notFull;
	
	public MyArrayBlockingQueue(int size) {
		this.size = size;
		count = 0;
		array = new Object[size];
		takeIndex = 0;
		putIndex = 0;
		lock = new ReentrantLock();
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
	}
	
	/**
	 * 出
	 * */
	private T dequeue() {
		// 假定array[takeIndex] != null
		@SuppressWarnings("unchecked")
		T obj = (T) array[takeIndex];
		array[takeIndex] = null;
		count--;
		if (++takeIndex == size) {
			takeIndex = 0;
		}
		notFull.signalAll();
		return obj;
	}
	
	/**
	 * 入
	 * */
	private void enqueue(T obj) {
		// 假定array[putIndex] == null
		array[putIndex] = obj;
		count++;
		if (++putIndex == size) {
			putIndex = 0;
		}
		notEmpty.signalAll();
	}
	
	/**
	 * 1、入队元素判空
	 * 2、队列是否已满（满则抛出异常）
	 * 3、入队列
	 * 
	 * @throws IllegalStateException if this queue is full
	 * @throws NullPointerException if the specified element is null
	 * */
	public boolean add(T obj) {
		if (obj == null) {
			throw new NullPointerException("enqueue element is null");
		}
		lock.lock();
		try {
			if (count == size) {
				throw new IllegalStateException("queue is full");
			} else {
				enqueue(obj);
				return true;
			}
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 1、入队元素判空
	 * 2、队列是否已满（满则阻塞等待）
	 * 3、入队列
	 * 
	 * @throws NullPointerException if the specified element is null
	 * */
	public void put(T obj) throws InterruptedException {
		if (obj == null) {
			throw new NullPointerException("enqueue element is null");
		}
		lock.lock();
		try {
			while (count == size) {
				notFull.await();
			}
			enqueue(obj);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 1、队列是否已空（空则返回null）
	 * 2、返回队列头
	 * */
	public T poll() {
		lock.lock();
		try {
			T obj = count == 0 ? null : (T) dequeue();
			return obj;
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 1、队列是否为空（空则阻塞等待）
	 * 2、返回队列头
	 * */
	public T take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			return dequeue();
		} finally {
			lock.unlock();
		}
	}
	
	public int size() {
		lock.lock();
		try {
			return count;
		} finally { 
			lock.unlock();
		}
	}
	
	@Override
	public String toString() {
		lock.lock();
        try {
            int k = count;
            if (k == 0)
                return "[]";

            final Object[] items = this.array;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = takeIndex; ; ) {
                Object e = items[i];
                sb.append(e == this ? "(this Collection)" : e);
                if (--k == 0)
                    return sb.append(']').toString();
                sb.append(',').append(' ');
                if (++i == items.length)
                    i = 0;
            }
        } finally {
            lock.unlock();
        }
	}
	
	public static void main(String[] args) {
		MyArrayBlockingQueue<Integer> queue = new MyArrayBlockingQueue<Integer>(5);
		for (int i = 0; i < 6; i++) {
			queue.add(i);
		}
		System.out.println(queue);
		Random rand = new Random();
	}
}
