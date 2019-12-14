package stream.bean;

public class Dish {

	@Override
	public String toString() {
		return "Dish [calories=" + calories + "]";
	}

	private int calories;

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
}
