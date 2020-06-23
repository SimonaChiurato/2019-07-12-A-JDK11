package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo;
	Map<Integer,Food> idMap;
	FoodDao dao;
	
	public Model() {
		this.dao=new FoodDao();
	}
	
	public void creaGrafo(int x) {
	
		 idMap= new HashMap<>();
		 dao.listAllFoods(idMap);
		 grafo= new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		 Graphs.addAllVertices(grafo, this.dao.listAllVertici(idMap, x));
		 for(Adiacenza a: dao.listAllArchi(idMap, x)) {
			 if(grafo.containsVertex(a.getF1()) && grafo.containsVertex(a.getF2())){
			 Graphs.addEdge(grafo, a.getF1(), a.getF2(), a.getPeso());
			 }
		 }
	}
	
	public Set<Food> vertici() {
		return this.grafo.vertexSet();
	}
	public Set<DefaultWeightedEdge> archi() {
	 return this.grafo.edgeSet();
	}

	public List<FoodCalorie> getAdiacenti(Food food){
		List<FoodCalorie> result= new ArrayList<>();
		for(Food f: Graphs.neighborListOf(grafo, food)) {
			result.add(new FoodCalorie(f,grafo.getEdgeWeight(grafo.getEdge(food, f))));

		}
		Collections.sort(result);
	
		return result;
	}
}
