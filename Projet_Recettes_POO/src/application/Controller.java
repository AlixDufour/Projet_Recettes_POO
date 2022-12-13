package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public abstract class Controller implements Observateur, Initializable {

	protected Modele model;
	
	@Override
	public abstract void reagir();
	
	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}
	
	@Override
	public abstract void initialize(URL arg0, ResourceBundle arg1);
	
}
