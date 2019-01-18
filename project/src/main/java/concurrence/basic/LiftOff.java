package concurrence.basic;
/**
 *  线程可以驱动任务,Runable接口实现run方法定义任务
 * @author hucha
 */
public class LiftOff implements Runnable {
	protected int countDown = 10;//Default
	private static int taskCount = 0;
	private final int id = taskCount++;//区分任务的多个实例,final初始化后就不会修改了
	public LiftOff(){};
	public LiftOff(int countDown){
		this.countDown = countDown;
	}
	
	public String status(){
		return "#" + id + "(" + (countDown>0?countDown:"LiftOff!") + "),";
	}
	@Override
	public void run() {
		while(countDown-->0){//多线程常常保持一个死循环,然后设置一个推出条件
			System.out.print(status());
			Thread.yield();//执行线程调度,将CPU转交给其它线程,这里主要显示更多的任务切换证据
		}
	}
	
	public static void main(String[] args) {
		LiftOff test = new LiftOff();
		test.run();//注意这里还是使用main的线程执行的,实现run方法只是描述任务的执行方法,没有创建多线程能力,除非将它显式传递给多线程
		//#0(9),#0(8),#0(7),#0(6),#0(5),#0(4),#0(3),#0(2),#0(1),#0(LiftOff!),
	}
	
	
}
