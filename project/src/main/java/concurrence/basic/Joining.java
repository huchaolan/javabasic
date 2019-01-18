package concurrence.basic;
/**
 * 当前线程允许线程t.join,使前线程中断,等t线程允许完成后在再运行
 * @author hucha
 */
public class Joining {
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy",1500);
		Sleeper grumpy = new Sleeper("Grumpy",1500);
		Joiner dopey = new Joiner("Dopey",sleepy);
		Joiner doc = new Joiner("doc",grumpy);
		grumpy.interrupt();//线程为sleep时,中断线程会触发中断异常
	}
}
class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name,int duration){
		super(name);
		this.duration = duration;
		start();
	}
	public void run(){
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted. isInterrupted(): " + isInterrupted());//捕获异常总为假
			return ;
		}
		System.out.println(getName() + " has awakened.");
	}
}
class Joiner extends Thread{
	private Sleeper sleep;
	public Joiner(String name,Sleeper sleep){
		super(name);
		this.sleep = sleep;
		start();
	}
	
	public void run(){
		try {
			sleep.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " Join Completed.");
	}
}
