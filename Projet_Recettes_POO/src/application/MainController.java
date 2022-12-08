package application;

import java.net.URL;
import java.util.ResourceBundle;

import dao.RecetteDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainController implements Observateur, Initializable {

	private Modele model;
	private RecetteDAO recettes;

	@FXML
	private HBox listeRecettes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listeRecettes.getChildren().clear();
		recettes = new RecetteDAO();
		for (Recette r : recettes.getAll()) {
			Label label = new Label(r.getName());
			listeRecettes.getChildren().add(label);
		}
	}

	@Override
	public void reagir() {
		// TODO Auto-generated method stub

	}


	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}

}
