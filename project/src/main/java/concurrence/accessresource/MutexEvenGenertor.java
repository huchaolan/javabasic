package concurrence.accessresource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenertor extends IntGenerator{

	private int currentNumber = 0;
	private Lock lock = new ReentrantLock();//重用锁
	@Override
	public int next() {
		lock.lock();
		try{
			++currentNumber;
			Thread.yield();
			++currentNumber;
			return currentNumber;//return资源必须放在try子句中,防止unlock子句过早发生
		}finally{
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenertor(), 10);
	}

}
