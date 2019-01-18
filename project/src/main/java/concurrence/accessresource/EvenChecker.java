package concurrence.accessresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator gen;
	private final int id;
	public EvenChecker(IntGenerator gen,int ident) {
		this.gen = gen;
		this.id = ident;
	}
	@Override
	public void run() {
		while(!gen.isCanceled()){
			int val = gen.next();
			if(val % 2 != 0){
				System.out.println(val + "val is not  Even Number");
				gen.cancel();
			}
		}
	}
	
	public static void test(IntGenerator gen,int count){
		System.out.println("Press Control-C Stop...");
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++){
			service.execute(new EvenChecker(gen,i));
		}
		service.shutdown();
	}

}
