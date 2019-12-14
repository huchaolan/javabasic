package stream.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import stream.bean.Dish;

public class StreamDemoUtil {
	

	public static List<Dish> genDishList() {
		final Random r = new Random(System.currentTimeMillis());
		return Collections.nCopies(400, new Dish()).stream()
			.map((d)->{
					int c = r.nextInt(400);
					Dish dd = new Dish();
					dd.setCalories(c);
					return dd;
				}).collect(Collectors.toList());
	}
}
