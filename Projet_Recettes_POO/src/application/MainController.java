package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainController implements Observateur, Initializable {

	private Modele model;

	@FXML
	private GridPane listeRecettes;

	@FXML
	private ImageView profileButton;
	
	@FXML 
	private Button returnButton;
	
	@FXML
	private Button ajouterRecette;
	
	@FXML
	private TextField champRecherche;
	
	private String categorie = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listeRecettes.getChildren().clear();


		profileButton.setOnMouseClicked(e -> {
			try {
				model.switchScene(CreationScenes.creerProfileScene(model));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		returnButton.setOnMouseClicked(e -> {
			try {
				model.switchScene(CreationScenes.creerChoixProfileScene(model));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		ajouterRecette.setOnMouseClicked(e -> {
			try {
				model.switchScene(CreationScenes.creerFormulaireRecetteScene(model));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});		
	}

	@Override
	public void reagir() {
		
		listeRecettes.getChildren().clear();
		
		int maxColumnNumber = 4;
		int x = 1;
		int y = 1;
		for (Recette r : this.model.getRecetteFiltrees()) {
			VBox vbox = new VBox();
			Label label = new Label(r.getName());
			
			// Mise en forme de la vbox
			vbox.getChildren().add(label);
			vbox.setPadding(new Insets(30, 30, 30, 30));
			vbox.setAlignment(Pos.CENTER); 
			
			listeRecettes.add(vbox, x, y);
			x++;
			// Si x dépasse le nombre de colonnes alors on passe à la ligne suivante
			if(x > maxColumnNumber) {
				x = 1;
				y++;
			}
		}

	}


	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
		this.model.filtrerRecettes(champRecherche.getText(),categorie ,true);
	}

	
	public void rechercher() {
		this.model.filtrerRecettes(champRecherche.getText(),categorie, true);
	}
}
