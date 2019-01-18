package concurrence.basic;

import java.util.Random;

/**
 *多线程创建 
 * @author hucha
 */
public class BasicThread {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			//Thread需要一个Runable对象,start方法会初始化线程,并允许run方法
			new Thread(new LiftOff()).start();
		}
		System.out.println("Waiting for LiftOff End....");
		/*
		 * 由于代码中yield方法会将CPU时间转个其他线程,所以
		 * 输出的实例也是不会重复的.
		 * Waiting for LiftOff End....
			#2(9),#1(9),#2(8),#0(9),#3(9),#4(9),
			#3(8),#0(8),#2(7),#1(8),#2(6),#0(7),
			#3(7),#4(8),#3(6),#0(6),#2(5),#1(7),
			#2(4),#0(5),#3(5),#4(7),#3(4),#0(4),
			#2(3),#1(6),#2(2),#0(3),#3(3),#4(6),
			#3(2),#0(2),#2(1),#1(5),#2(LiftOff!),
			#0(1),#3(1),#4(5),#3(LiftOff!),#0(LiftOff!),
			#1(4),#4(4),#1(3),#4(3),#1(2),#4(2),
			#1(1),#4(1),#1(LiftOff!),#4(LiftOff!),
		 */
	}
}
class Fibonacci implements Runnable{
	private static int count = 0;
	private int id = count++;
	private int layer = 0;
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			new Thread(new Fibonacci(i)).start();
		}
	}
	public Fibonacci(int layer){
		this.layer = layer;
	}
	@Override
	public void run() {
		fibonacci();
	}
	//0,1,1,2,3,5,8
	//f(n) = f(n-1)+f(n-2)
	public int fibonacci1(int layer){
		if(layer<=1){
			return layer;
		}else{
			int pre1 =  fibonacci1(layer-1);
			int pre2 =  fibonacci1(layer-2);
			int result = pre1+pre2;
			return result;
		}
	}
	public long fibonacci(){
		StringBuilder sb = new StringBuilder();
		sb.append(id+"======");
		if(layer<=1){
			sb.append(layer + ",");
			return layer;
		}else{
			long pre1=0,pre2=1,result=0;
			sb.append(pre1+","+pre2+",");
			for(int i=2;i<layer;i++){
				result = pre1+pre2;
				pre1 = pre2;
				pre2 = result;
				sb.append(result + ",");
			}
			System.out.println(sb.toString());
			return result;
		}
	}
}
/**
 * 
 * @author hucha
 */
class OutRunable implements Runnable{
	private static int count = 0;
	private int id= count++;
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new OutRunable()).start();
		}
	}
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			System.out.println("#"+id+" :"+i);
			Thread.yield();
		}
	}
}

