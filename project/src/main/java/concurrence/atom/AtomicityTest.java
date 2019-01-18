package concurrence.atom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简单描述并发下原子性和易变性
 * 21.3.3,682页 
 * volatile关键字的作用 ,当对变量进行写操作时,会直接刷到主存中
 * synchronized和区别
 * 可视性和原子性
 * 
 * 多线程一直调用evenIncrement方法将i两次自增
 * main方法中不停的获取i
 * @author hucha
 *
 */
public class AtomicityTest implements Runnable {
	
	private  int i = 0 ;
	
	public int getValue(){ //缺少同步,获取到i可能是中间态的
		return i;
	}
	
	private synchronized void evenIncrement(){
		i++;
		i++;
	}
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true){
			int i = at.getValue();
			if(i%2!=0){//如果获取到奇数退出程序
				System.out.println(i);
				System.exit(0);
			}
		}
	}

}
