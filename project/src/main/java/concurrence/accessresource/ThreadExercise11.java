package concurrence.accessresource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习11
 * 一个类有两个数据域和操作数据方法,其操作过程是多步骤
 * 添加读取这些域的方法,创建多个线程去调用各种方法,并展示
 * 处于不正确状态数据是可视,synchronized关键字修复
 * @author hucha
 */
public class ThreadExercise11 {
	private Part part= null;
	public static void main(String[] args) {
		final ThreadExercise11 t = new ThreadExercise11().init();
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			service.execute(new Runnable(){
				Random r = new Random();
				private int i = 0;
				@Override
				public void run() {
					try{
						while(true){
							if(t.isWrong()){
								System.out.println(Thread.currentThread().getName()+":isWrong");
								break;
							}else{
								t.opPart(String.valueOf(r.nextInt(100000)),
										String.valueOf(r.nextInt(200000)));
								System.out.println(Thread.currentThread().getName() + "====>" + (++i));
								Thread.yield();
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
		}
		service.shutdown();
	}
	
	public boolean isWrong(){
		return "WrongNumber".equals(part.getNumber())||"WrongName".equals(part.getName());
	}
	
	public void opPart(String number,String name) throws InterruptedException{
		part.setNumber("WrongNumber");
		Thread.sleep(10);
		part.setNumber(number);
		part.setName("WrongName");
	//	Thread.sleep(10);
		part.setName(name);
	}
	
	public ThreadExercise11 init(){
		part = buildPart("111010","name110");
		return this;
	}
	
	public static Part buildPart(String number,String name){
		Part p = new Part();
		p.setName(name);
		p.setNumber(number);
		return p;
	}
}

class Part{
	private String number;
	private String name;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}