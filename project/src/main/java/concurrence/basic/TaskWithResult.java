package concurrence.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 带有返回值的Task要实现Callable接口,接口带一个泛型,指定返回值的类型
 * 接口中有call方法,执行器使用submit方法将task添加到线程池中,添加后就开始执行
 * submit返回Future对象,调用get方法就可以获取task的结果,但是如果没有执行完就会就会阻塞,直到执行完为止
 * Future对象还有带有超时的get方法和isDone判断是否执行完的方法
 * @author hucha
 */
public class TaskWithResult implements Callable<String>{
	private int id = 0;
	public  TaskWithResult(int id) {
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		System.out.println("id:"+id);
		Thread.sleep(2000);
		return "Result of TaskWithResult" + id;
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();
		for(int i =0;i<10;i++)
			resultList.add(service.submit(new TaskWithResult(i)));
		System.out.println("service is done");
		for(Future<String> fs:resultList){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} 
		}
		service.shutdown();
	}
}
class FibonacciTask implements Callable<Long>{
	public static void main(String[] args) throws Exception {
		ExecutorService  service = Executors.newCachedThreadPool();
		List<Future<Long>> resultList = new ArrayList<Future<Long>>();
		for(int i=0;i<10;i++){
			resultList.add(service.submit(new FibonacciTask(i)));
		}
		service.shutdown();//不关闭,main方法不能结束
		Long result = 0L;
		for(Future<Long> fu:resultList){
				result+=fu.get();
		}
		System.out.println(result);
	}
	private int count = 0;
	private FibonacciTask(int count){
		this.count = count;
	}
	
	public long getResult(){
		if(count<=1){
			return count;
		}else{
			long pre1=0,pre2=1,result=0;
			for(int i=2;i<=count;i++){
				result = pre1+pre2;
				pre1 = pre2;
				pre2 = result;
			}
			return result;
		}
	}
	@Override
	public Long call() throws Exception {
		return getResult();
	}
	
}