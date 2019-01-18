package concurrence.accessresource;

/**
 * 整数产生器
 * @author hucha
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	public void cancel(){
		canceled = true;
	}
	public boolean isCanceled(){
		return canceled;
	}
}
