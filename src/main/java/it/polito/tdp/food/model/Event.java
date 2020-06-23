package it.polito.tdp.food.model;

public class Event implements Comparable<Event> {
	public enum EventType{
		INIZIO, FINE;
	}

	private double durata;
	private EventType type;
	private Food food;
	private Integer stazione;



	public Event(double durata, EventType type, Food food, Integer stazione) {
		super();
		this.durata = durata;
		this.type = type;
		this.food = food;
		this.stazione = stazione;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Integer getStazione() {
		return stazione;
	}

	public void setStazione(Integer stazione) {
		this.stazione = stazione;
	}

	public double getDurata() {
		return durata;
	}

	public void setDurata(double durata) {
		this.durata = durata;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return Double.compare(this.durata, o.durata);
	}
	
}
