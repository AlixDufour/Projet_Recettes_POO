package application;

import java.net.URL;
import java.util.ResourceBundle;

import dao.RecetteDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChoixProfileController implements Observateur, Initializable{

	private Modele model;

	@FXML
	private HBox listeRecettes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@Override
	public void reagir() {

	}


	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}

}
