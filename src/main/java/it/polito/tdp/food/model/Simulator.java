package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.model.Event.EventType;

public class Simulator {

	Model model;
	int k; // stazioni, un solo cibo per volta
	SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo;
	Food iniziale;
	PriorityQueue<Event> queue;
	List<Food> preparati;
	List<Integer> stazioni;
	// output
	int completati;
	double tempo;

	public Simulator(int k, SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo, Food food, Model model) {
		this.k = k;
		this.grafo = grafo;
		this.iniziale = food;
		this.model =model;
		queue = new PriorityQueue<Event>();
		this.stazioni = new ArrayList<>();
		this.preparati = new ArrayList<>();
		tempo = 0.0;
		completati = 0;
		

	}

	public void run() {
		this.queue.clear();

		List<FoodCalorie> vicini = new ArrayList<>(this.model.getAdiacenti(iniziale));
				
		for (int i = 0; i < k && i < vicini.size(); i++) {
			stazioni.add(i);
			queue.add(
					new Event(vicini.get(i).getCalorie(), EventType.FINE, vicini.get(i).getFood(), stazioni.get(i)));
			vicini.get(i).getFood().setInCorso(true);
		}

		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			this.processEvent(e);
		}
	}

	private void processEvent(Event e) {
		switch (e.getType()) {
		case FINE:
              if(!preparati.contains(e.getFood())) {
				preparati.add(e.getFood());
				this.completati++;
				this.tempo=e.getDurata();
			
				queue.add(new Event(e.getDurata(),EventType.INIZIO,e.getFood(),e.getStazione()));
			}
			
			break;
		case INIZIO:
			
		      List<FoodCalorie> vicini = this.model.getAdiacenti(e.getFood());
				FoodCalorie prossimo = null;
				for (FoodCalorie f : vicini) {
					
					
					if (f.getFood().getInCorso()==false) {
						prossimo = f;
						break;
					}
				}

				if(prossimo!=null) {
					prossimo.getFood().setInCorso(true);
					queue.add(new Event(e.getDurata()+prossimo.getCalorie(),EventType.FINE,prossimo.getFood(),e.getStazione()));
				}
				break;
		}

	}
	
	public int getCompletati() {
		return this.completati;
	}

	public double getTempo() {
		return this.tempo;
	}
}
