package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.IngredientDAO;
import dao.RecetteRegimeDAO;
import dao.UstensileDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class RecetteFormController implements Initializable, Observateur {

	private Modele model;

	@FXML
	private TextField form_nom, form_duree, form_desc, form_prix;

	@FXML
	private ChoiceBox<String> form_diff, form_regime, form_ingredient, form_ustensile;

	@FXML
	private VBox ingredientsBox, ustensilesBox, etapesBox, regimeBox;

	private int countEtapes;

	private List<String> regimes, ingredients, ustensiles;

	@Override
	public void reagir() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		countEtapes = 1;

		ObservableList<String> niveauChoices = FXCollections.observableArrayList("Facile", "Intermédiaire", "Difficile"); ;
		form_diff.setItems(niveauChoices);
		form_diff.setValue("Facile");

		// Récupération des régimes
		RecetteRegimeDAO rrd = new RecetteRegimeDAO();
		regimes = rrd.getRegimesNames();
		ObservableList<String> regimesChoices = FXCollections.observableArrayList(regimes);
		form_regime.setItems(regimesChoices);

		// Récupération des ingrédients 
		IngredientDAO idao = new IngredientDAO();
		ingredients = idao.getIngredientsNames();
		ObservableList<String> ingredientsChoices = FXCollections.observableArrayList(ingredients);
		form_ingredient.setItems(ingredientsChoices);

		// Récupération des ustensiles
		UstensileDAO udao = new UstensileDAO();
		ustensiles = udao.getUstensilesNames();
		ObservableList<String> ustensilesChoices = FXCollections.observableArrayList(ustensiles);
		form_ustensile.setItems(ustensilesChoices);
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

	@FXML
	public void ajouterLigneIngredient(ActionEvent event) {
		try {
			HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormIngredient.fxml"));
			ObservableList<String> ingredientsChoices = FXCollections.observableArrayList(ingredients);
			((ChoiceBox) form.getChildren().get(1)).setItems(ingredientsChoices);

			ingredientsBox.getChildren().add((Node) form);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	public void ajouterLigneUstensile(ActionEvent event) {
		try {
			HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormUstensile.fxml"));
			ObservableList<String> ustensilesChoices = FXCollections.observableArrayList(ustensiles);
			((ChoiceBox) form.getChildren().get(1)).setItems(ustensilesChoices);

			ustensilesBox.getChildren().add((Node) form);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	public void ajouterLigneEtape(ActionEvent event) {
		try {
			countEtapes ++;
			HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormEtape.fxml"));
			((Label) form.getChildren().get(0)).setText("Etape n°" + countEtapes);
			etapesBox.getChildren().add((Node) form);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	public void ajouterLigneRegime(ActionEvent event) {
		try {
			HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormRegime.fxml"));
			ObservableList<String> regimesChoices = FXCollections.observableArrayList(regimes);
			((ChoiceBox) form.getChildren().get(1)).setItems(regimesChoices);

			regimeBox.getChildren().add((Node) form);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	public void retourAccueil(ActionEvent event) {
		try {
			model.switchScene(CreationScenes.creerMainScene(model));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void enregistrerRecette(ActionEvent event) {
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
	}




}
