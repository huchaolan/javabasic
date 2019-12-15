package stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import junit.framework.TestCase;

import org.junit.Test;

import stream.bean.Dish;
import stream.util.StreamDemoUtil;

public class StreamDemo1 {
	/**
	 * Java8以前的方式
	 */
	@Test
	public void demo1() {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		List<Dish> menu = StreamDemoUtil.genDishList();
		for(Dish d:menu) {
			if(d.getCalories()<300) {
				lowCaloricDishes.add(d);
			}
		}
		Collections.sort(lowCaloricDishes,new Comparator<Dish>(){
			@Override
			public int compare(Dish o1, Dish o2) {
				return Integer.compare(o1.getCalories(), o2.getCalories());
			}
			
		});
		List<Integer> lowCaloricDishesName = new ArrayList<>();
		for(Dish d:lowCaloricDishes){
			lowCaloricDishesName.add(d.getCalories());
		}
		System.out.println(lowCaloricDishesName);
	}
	
	/**
	 * stream方式
	 */
	@Test
	public void demo2() {
		List<Integer> lowCaloricDishesName = StreamDemoUtil.genDishList().stream()
			.filter(d->d.getCalories()<300)
			//.sorted(Comparator.comparingInt(Dish::getCalories))
			.limit(3)
			.map(Dish::getCalories)
			.collect(Collectors.toList());
		System.out.println(lowCaloricDishesName);
	}
}
