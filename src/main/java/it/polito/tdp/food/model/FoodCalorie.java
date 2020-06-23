package it.polito.tdp.food.model;

public class FoodCalorie implements Comparable<FoodCalorie>{
	private Food food;
	private double calorie;
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
	public FoodCalorie(Food food, double calorie) {
		super();
		this.food = food;
		this.calorie = calorie;
	}
	@Override
	public int compareTo(FoodCalorie o) {
		// TODO Auto-generated method stub
		return -Double.compare(this.calorie, o.calorie);
	}
	@Override
	public String toString() {
		return  food + ", calorie=" + calorie;
	}
	
	

}
