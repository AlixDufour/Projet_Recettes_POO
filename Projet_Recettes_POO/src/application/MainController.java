package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.RecetteDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MainController implements Observateur, Initializable {

	private Modele model;
	private RecetteDAO recettes;

	@FXML
	private HBox listeRecettes;
	@FXML
	private ImageView profileButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listeRecettes.getChildren().clear();
		recettes = new RecetteDAO();
		for (Recette r : recettes.getAll()) {
			Label label = new Label(r.getName());
			listeRecettes.getChildren().add(label);
		}

		profileButton.setOnMouseClicked(e -> {
			try {
				model.switchScene(CreationScenes.creerProfileScene(model));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
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
