package concurrence.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 设置优先级,可以让线程调度器优先调度优先级高的线程,但是只是优先,不会导致优先级低线程不会执行
 * 也不会产生锁线程的情况,优先级是执行比率会低一些,所以开发多线程时应使用默认的线程.如果修改优先级别
 * 应该是等线程启动后在修改--放到run方法中,修改后就立即生效
 * @author hucha
 */
public class PrioritiesTaskDemo implements Runnable{
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		//开始的线程都是最低优先级的
		for(int i=0;i<5;i++)
			service.execute(new PrioritiesTaskDemo(Thread.MIN_PRIORITY) );
		service.execute(new PrioritiesTaskDemo(Thread.MAX_PRIORITY));//最后一个最高优先级
		service.shutdown();
		
	}
	private int countDown = 5;
	private volatile double d ;
	private int priority ;
	public PrioritiesTaskDemo(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return Thread.currentThread()+"_"+priority;//输出优先级
	}
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true){
			//做一些耗时的运算,让能看到优先级设置的效果
			for (int i = 0; i < 100000; i++) {
				d+=(Math.PI+Math.E)/(double)i;
				if(i%1000==0)
					Thread.yield();
			}
			System.out.println(this);
			if(--countDown<0) return ;
		}
	}

}
