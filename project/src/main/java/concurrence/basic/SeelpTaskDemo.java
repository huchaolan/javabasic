package concurrence.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SeelpTaskDemo extends LiftOff {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new SeelpTaskDemo());
		}
	}
	@Override
	public void run() {
		try {
			while(countDown-->0){
					System.out.println(status());
					//Thread.sleep(500);
					TimeUnit.MILLISECONDS.sleep(500);//JavaSE5.0引入新的睡眠方法
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
