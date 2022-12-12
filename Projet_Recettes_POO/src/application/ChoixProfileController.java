package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ProfileDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ChoixProfileController implements Observateur, Initializable{

	private Modele model;
	private ProfileDAO profils;
	private final int MAXprofils = 5;

	@FXML
	private FlowPane listeProfils;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listeProfils.getChildren().clear();
		listeProfils.setHgap(12);
		listeProfils.setVgap(12);
		profils = new ProfileDAO();
		int i = 0;
		for (Profile p : profils.getAll()) {
			Button b = new Button();
			b.setStyle("-fx-padding: 35px; -fx-min-width: 50px;");
			listeProfils.getChildren().add(b);
			b.setText(p.getPrenom());
			b.setOnAction(e -> {
				try {
					model.connexion(p); // charge le profil dans le modèle
					model.switchScene(CreationScenes.creerMainScene(model));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			// listeProfils.getChildren().add(label);
		}

		if (profils.getAll().size() < MAXprofils) {
			Button bplus = new Button();
			bplus.setText("new");
			bplus.setStyle("-fx-padding: 20px; -fx-min-width: 20px;");
			listeProfils.getChildren().add(bplus);
			bplus.setOnAction(e -> {
				try {
					Profile prof = new Profile();
					profils.create(prof);
					model.connexion(prof); // charge le profil dans le modèle
					model.switchScene(CreationScenes.creerMainScene(model));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}

	}

	@Override
	public void reagir() {

	}


	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}

}
