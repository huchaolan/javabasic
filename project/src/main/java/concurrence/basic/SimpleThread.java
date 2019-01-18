package concurrence.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程的启动还有３中编程变体
 * １.继承Thread类
 * 2.自管理线程
 * 3.内部类
 * @author hucha
 */
public class SimpleThread extends Thread {
	private int countDown= 5;
	private static int threadCount = 0;
	public SimpleThread() {
		super(Integer.toString(++threadCount));//给线程起名字
		this.start();
	}
	@Override
	public String toString(){
		return "#" + getName() + "(" +countDown+")";
	}
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new SimpleThread();
		}
	}
	@Override
	public void run(){
		while(true){
			System.out.println(this);
			if(--countDown<0){
				return ;
			}
		}
	}
}
class SelfManager implements Runnable{
	private int countDown = 5;
	private Thread t = new Thread(this);
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new SelfManager();
		}
	}
	public SelfManager(){
		t.start();//在创建对象时候启动不可取,最好的使用方法用执行器
	}
	@Override
	public String toString(){
		return "#" + Thread.currentThread().getName() + "(" + countDown +")";
	}
	@Override
	public void run() {
		while(true){
			System.out.println(this);
			if(--countDown<0) return ;
		}
	}
}
class ThreadMethod{
	private int countDown = 5;
	private Thread t = null;
	private String name = null;
	public static void main(String[] args) throws Exception {
		Future<Long> f =  new ThreadMethod("ThreadMethod").runTask(10);
		System.out.println(f.get());
	}
	public ThreadMethod(String name){
		this.name = name;
	}
	public Future<Long> runTask(final int layer){
		ExecutorService service = Executors.newSingleThreadExecutor();
		return service.submit(new Callable<Long>(){
				@Override
				public Long call() throws Exception {
					Long result = 0L;
					if(layer<=1){
						result = new Long(layer);
					}else{
						long prev=0;long prev1=1;
						for(int i=2;i<=layer;i++){
							result = prev + prev1;
							prev1 = result;
							prev = prev1;
						}
					}
					return result;
				}
			}
		);
	}
}
class RunnableThread1{
	private int countDown = 5;
	public static void main(String[] args) {
		new RunnableThread1("RunnableThread1");
	}
	public RunnableThread1(String name) {
		Thread t = new Thread(new Runnable(){//匿名类,主要定义任务
			@Override
			public void run() {
				try{
					while(true){
						System.out.println(this);
						if(--countDown<0) return ;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				}catch(Exception e){
					System.out.println("sleep exception");
				}
			}
			public String toString(){
				return "#" + Thread.currentThread().getName() + ": " + countDown;
			}
		},
		name);
		t.start();
	}
}
class RunnableThread{
	private int countDown = 5;
	private Inner2 inner ;
	public static void main(String[] args) {
		new RunnableThread("RunableThread");
	}
	public RunnableThread(String name) {
		inner = new Inner2(name);
	}
	private class Inner2 implements Runnable{
		Thread t = null;
		public Inner2(String name) {
			t = new Thread(this,name);
			t.start();
		}
		@Override
		public String toString(){
			return "#" + Thread.currentThread().getName() + ": " + countDown;
		}
		@Override
		public void run() {
			try{
				while(true){
					System.out.println(this);
					if(--countDown<0)return ;
					TimeUnit.MILLISECONDS.sleep(10);
				}
				}catch(Exception e){
					System.out.println("sleep() exception");
				}
		}
	}
}
class InnerThread2{
	private int countDown = 5;
	private Inner1 inner;
	
	public InnerThread2(String name) {
		inner = new Inner1(name);
	}
	public static void main(String[] args) {
		new InnerThread2("InnerThread2");
	}
	private class Inner1{
		Inner1(String name){
			Thread t = new Thread(name){//在内部类中创建匿名的内部类,在内部类中定义任务
				public void run(){
					try{
					while(true){
						System.out.println(this);
						if(--countDown<0)return ;
						sleep(10);
					}
					}catch(Exception e){
						System.out.println("sleep() exception");
					}
				}
				public String toString(){
					return getName() +": " + countDown;
				}
			};
			t.start();
		}
	}
}
class InnerThread1{
	private int countDown = 5;
	private Inner inner ;
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
	}
	public InnerThread1(String name){
		inner = new Inner(name);
	}
	/**
	 * 使用内部类创建线程
	 * @author hucha
	 */
	private class Inner extends Thread{
		Inner(String name){
			super(name);
			start();//在内部类创建时候启动线程
		}
		public String toString(){
			return "#" + getName() + "("+countDown+")";
		}
		public void  run(){
				while(true){
					System.out.println(this);
					if(--countDown<0) return ;
				}
		}
	}
}
