package it.polito.tdp.food.model;

public class Stazione {

	private boolean libera;
	private Food food;
	public boolean isLibera() {
		return libera;
	}
	public void setLibera(boolean libera) {
		this.libera = libera;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Stazione(boolean libera, Food food) {
		super();
		this.libera = libera;
		this.food = food;
	}
	
}
