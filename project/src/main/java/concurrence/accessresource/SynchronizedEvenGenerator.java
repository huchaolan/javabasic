package concurrence.accessresource;

/**
 * 将next方法添加synchronized方法级别同步,Thread.yield()目的
 * 让出资源造成currentEvenValue可能为奇数,而停止运行,没有演示出来
 * @author hucha
 *
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue=0;
	@Override
	public synchronized int next() {
		++currentEvenValue;
		String before = Thread.currentThread().getName();
		System.out.println("before:" + before +"_" +currentEvenValue);
		Thread.yield();
		Thread.yield();
		Thread.yield();
		++currentEvenValue;
		String after = Thread.currentThread().getName();
		System.out.println("after:" + after+"_" +currentEvenValue );
		return currentEvenValue;
	}

	public static void main(String[] args) {
		SynchronizedEvenGenerator gen = new SynchronizedEvenGenerator();
		EvenChecker.test(gen, 10);
	}
}
