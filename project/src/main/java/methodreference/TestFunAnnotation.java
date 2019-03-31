package methodreference;

/**
 * 有的接口带有@FunctionalInterface注解，这个注解是为了显式的说明接口是函数式接口，
 * 编译器也会修饰的接口是否符合函数式接口规范，不然就会报错，作用和Override注解一样
 */
@FunctionalInterface
public interface TestFunAnnotation{

	public boolean test();

	//ublic boolean test1();
}