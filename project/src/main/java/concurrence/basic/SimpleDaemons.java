package concurrence.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/**
 * 后台线程,主要是作为服务线程,当所有非后台线程退出后,后台也会退出
 * 
 * @author hucha
 */
public class SimpleDaemons implements Runnable {

	public static void main(String[] args) throws Exception{
		ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<10;i++){
			service.execute(new SimpleDaemons());
		}
		service.shutdown();
		System.out.println("all daemons started");
		TimeUnit.MILLISECONDS.sleep(175);//main线程退出后后台进程也会退出
	}
	@Override
	public void run() {
		while(true){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + 	" "+ this);
			} catch (InterruptedException e) {
				System.out.println("sleep error");
			}
		}
	}
}
class DaemoSpawn implements Runnable{
	@Override
	public void run() {
		while(true){
			Thread.yield();
		}
	}
}
class DaemonTask implements Runnable{
	private Thread[] ts  = new Thread[10];
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new DaemonTask());
		t.setDaemon(true);//后台线程启动的线程也是后台线程
		t.start();
		System.out.println("t.isDaemon()=" + t.isDaemon() +"." );
		TimeUnit.MILLISECONDS.sleep(100);
	}
	@Override
	public void run() {
		for(int i=0;i<ts.length;i++){
			ts[i] = new Thread(new DaemoSpawn());
			ts[i].start();
		}
		for(int i=0;i<ts.length;i++){
			System.out.println("t["+i +"].isDaemon()=" + ts[i].isDaemon() +"." );
		}
		while(true){
			Thread.yield();
		}
	}
}
class DaemonThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
	
}
