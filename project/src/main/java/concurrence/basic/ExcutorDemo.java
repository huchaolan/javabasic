package concurrence.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 用执行器来允许线程,简化线程的开发,由执行器来执行线程
 * CachedThreadPool 创建当前需要的线程数,当回收旧线程时,会停止新线程创建
 * FixedThreadPool 创建固定个数的线程池,限制执行线程的数量.当线程超过上限时,新的任务会等待,直到前面任务运行完成
 * SingleThreadExecutor 创建一个单个线程,应用场景,监听
 * @author hucha
 */
public class ExcutorDemo {
	public static void main(String[] args) {
		//ExecutorService service = Executors.newCachedThreadPool();//创建具有服务生命周期的执行器
		//service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 50;i++) {
			service.execute(new LiftOff());
		}
		service.shutdown();
	}
}
