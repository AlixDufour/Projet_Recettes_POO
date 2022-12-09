package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RecetteFormController implements Initializable, Observateur {

	private Modele model;
	
	@FXML
	private Button enregistrerRecette, retourAccueil, ajouterIngrédient, ajouterUstensile, ajouterEtape, ajouterRegime;
	
	@FXML
	private TextField form_nom, form_duree, form_desc, form_prix;
	
	@FXML
	private ChoiceBox<String> form_diff, form_regime;
	
	@FXML
	private VBox ingredientsBox, ustensilesBox, etapesBox, regimeBox;
	
	@Override
	public void reagir() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		ObservableList<String> niveauChoices = FXCollections.observableArrayList("Facile", "Intermédiaire", "Difficile"); ;
		form_diff.setItems(niveauChoices);
		form_diff.setValue("Facile");
		
		ObservableList<String> regimesChoices = FXCollections.observableArrayList("Classique", "Végétarien", "Végétalien"); ;
		form_regime.setItems(regimesChoices);
		form_regime.setValue("Classique");	
		
		ajouterIngrédient.setOnMouseClicked(e -> {
			try {
				HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormIngredient.fxml"));
				ingredientsBox.getChildren().add((Node) form);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		
		ajouterUstensile.setOnMouseClicked(e -> {
			try {
				HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormUstensile.fxml"));
				ustensilesBox.getChildren().add((Node) form);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		
		ajouterEtape.setOnMouseClicked(e -> {
			try {
				HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormEtape.fxml"));
				etapesBox.getChildren().add((Node) form);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		
		ajouterRegime.setOnMouseClicked(e -> {
			try {
				HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormRegime.fxml"));
				regimeBox.getChildren().add((Node) form);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		
		enregistrerRecette.setOnMouseClicked(e -> {
			if(saveRecette()) {
				try {
					model.switchScene(CreationScenes.creerMainScene(model));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				// TODO Afficher message d'erreur
			}
		});
		
		retourAccueil.setOnMouseClicked(e -> {
			try {
				model.switchScene(CreationScenes.creerMainScene(model));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}

	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}
	
	private boolean saveRecette() {
		
		// Création de la liste des étapes
		// Boucle for
		
		// Création de la liste des ustensiles
		
		// Création de la liste des ingrédients
		
		// Création de la liste des régimes
		
		// Enregistrement de la nouvelle recette
		/*Recette nouvelleRecette = new Recette(form_nom, form_desc, 
				form_duree, 
				form_diff, 
				form_prix, 
				List <Etape>etapes, 
				List<Ustensile> ustensiles, 
				List<QuantiteIngredient> ingr, 
				List<Regime> regimes);*/
		return true;
	}
}
