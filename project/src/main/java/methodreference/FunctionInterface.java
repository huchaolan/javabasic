package methodreference;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * 只定义了一个抽象方法的接口。比如Comparator和Runnable。 lambda表达式允许直接以内联的形式为函数式接口的抽象方法提供实现。
 */
public class FunctionInterface {
	public static void main(String[] args) {
		foo1();
	}

	/**
	 * lambda作为参数传递
	 */
	public static void foo1(){
		bar1(()->{System.out.println("Hello lambda1");});
	}

	public static void bar1(Runnable r) {
		r.run();
	}

	/**
	 * lambda表达式允许直接以内联的形式为函数式接口的抽象方法提供实现。
	 */
	public static void extracted() {
		Runnable r1 = () -> System.out.println("Hello lambda1");
		r1.run();
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello innerclass");
			}
		};
		r2.run();
		Predicate<String> p = null;
	}

	
}