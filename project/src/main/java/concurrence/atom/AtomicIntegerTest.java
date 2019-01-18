package concurrence.atom;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性变量类 AtomicInteger,AtomicLong,AtomicReference
 * @author hucha
 *
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);
	public int getValue(){
		return i.get();
	}
	private void evenIncrement(){
		i.getAndAdd(2);
	}
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			public void run(){
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while(true){
			int val = ait.getValue();
			if(val %2 != 0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
