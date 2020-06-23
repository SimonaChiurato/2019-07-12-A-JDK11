/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.FoodCalorie;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.boxFood.getItems().remove(0, this.boxFood.getItems().size()-1);
    	String input= this.txtPorzioni.getText();
    	if(!input.matches("[0-9]+")) {
    		txtResult.appendText("Devi inserire un valore numerico intero");
    		return;
    	}
    	int x= Integer.parseInt(input);
    	this.model.creaGrafo(x);
    	
    	txtResult.appendText("Arco creato con # vertici: "+this.model.vertici().size()+" # achi: "+this.model.archi().size()+"\n");
  List<Food> vertici= new ArrayList<>(this.model.vertici());
  Collections.sort(vertici);
    	this.boxFood.getItems().addAll(vertici);
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	Food food= this.boxFood.getValue();
    	if(food==null) {
    		txtResult.appendText("Devi selezionare");
    		return;
    	}
    	List<FoodCalorie> result= this.model.getAdiacenti(food);
    	txtResult.appendText("I vicini di "+food+":\n");
    	if(result.size()>5) {
    	for(int i=0; i<5; i++) {
    		txtResult.appendText(result.get(i).toString()+"\n");	
    	}
    	}else {
    		for(FoodCalorie c: result) {
    			txtResult.appendText(c.toString()+"\n");	
    		}
    	}
    
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
      	String input= this.txtK.getText();
    	if(!input.matches("[0-9]+")) {
    		txtResult.appendText("Devi inserire un valore numerico intero");
    		return;
    	}
    	int k= Integer.parseInt(input);
    	Food food= this.boxFood.getValue();
    	
    	this.model.runSimulator(k, food);
    	txtResult.appendText("Piatti completati: "+this.model.getCompletati()+" tempo impiegato: "+this.model.getTemo());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
