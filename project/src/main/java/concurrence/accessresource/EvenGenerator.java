package concurrence.accessresource;

public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	@Override
	public int next() {
		++currentEvenValue;//danger code
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator(), 10);
		/**
		 * 各个EvenChecker任务在EvenGenerator处于不恰当的状态时
		 * 任然可以访问其中的信息,出现了两次569,有时出现了连续的数值	
		 * 569val is not  Even Number
			571val is not  Even Number
			569val is not  Even Number
		 */
	}
}
