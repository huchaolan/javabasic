package concurrence.accessresource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用了ReentrantLock,可重用锁,lock提供了颗粒度更细致的控制
 * synchronized如果一些错误失败了,就会抛出异常,没有机会清理
 * lock配合try/finally可以做到,但是一般在synchronized做不到情况才会使用
 * @author hucha
 *
 */
public class AttemptLocking {
	private Lock lock = new ReentrantLock();//可重用的锁
	
	public void untimed(){
		boolean iscapture = lock.tryLock();//尝试获取锁,如果对象已经锁住,返回false
		try{
			System.out.println("tryLocak:" + iscapture);
		}finally{
			if(iscapture){
				lock.unlock();
			}
		}
	}
	public void timed(){
		boolean iscapture = false;
		try {
			iscapture = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			System.out.println("timed()"+ iscapture);
		}finally{
			if(iscapture){
				lock.unlock();
			}
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread(){
			{setDaemon(true);}
			@Override
			public void run(){
				al.lock.lock();
				System.out.println("acquired....");
			}
		}.start();
		Thread.sleep(1000);
		al.untimed();
		al.timed();
	}
}
