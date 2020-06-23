package it.polito.tdp.food.model;

public class FoodCalorie implements Comparable<FoodCalorie> {

	private Food food;
	private double calorie;
	
	
	public FoodCalorie(Food food, double calorie) {
		super();
		this.food = food;
		this.calorie = calorie;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public double getCalorie() {
		return calorie;
	}
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	@Override
	public int compareTo(FoodCalorie o) {

		return Double.compare(calorie, o.calorie);
	}
	@Override
	public String toString() {
		return  food.toString() + " calorie: " + calorie;
	}
	
	

}
