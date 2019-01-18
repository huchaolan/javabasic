package concurrence.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionThread  {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(new ThreadHanlder());
		service.execute(new ExceptionDemo());
	}
	
}
class ExceptionDemo implements Runnable{

	@Override
	public void run() {
		throw new RuntimeException();
	}
	
}
class MyExceptionHandler implements Thread.UncaughtExceptionHandler{
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getClass().getName());
		System.out.println("caught " + e);
	}
}
 class ThreadHanlder implements ThreadFactory{
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyExceptionHandler());
		return t;
	}
	
}
