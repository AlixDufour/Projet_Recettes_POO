package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EtapeDAO;
import dao.IngredientDAO;
import dao.QuantiteIngredientDAO;
import dao.RecetteDAO;
import dao.RecetteRegimeDAO;
import dao.RecetteUstensileDAO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class RecetteFormController implements Initializable, Observateur {

	private Modele model;

	@FXML
	private TextField form_nom, form_duree, form_desc, form_prix;

	@FXML
	private ChoiceBox<String> form_diff, form_regime, form_ingredient, form_type_quantite, form_ustensile;

	@FXML
	private VBox ingredientsBox, ustensilesBox, etapesBox, regimeBox;

	private int countEtapes;

	private List<String> regimes, ingredients, ustensiles, typeQte;
	
	private IngredientDAO idao;
	
	private UstensileDAO udao;
	
	private RecetteRegimeDAO rrd;

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
		rrd = new RecetteRegimeDAO();
		regimes = rrd.getRegimesNames();
		ObservableList<String> regimesChoices = FXCollections.observableArrayList(regimes);
		form_regime.setItems(regimesChoices);

		// Récupération des ingrédients 
		idao = new IngredientDAO();
		ingredients = idao.getIngredientsNames();
		ObservableList<String> ingredientsChoices = FXCollections.observableArrayList(ingredients);
		form_ingredient.setItems(ingredientsChoices);
		
		// Récupération des types de quantité 
		ObservableList<String> typeQteChoices = FXCollections.observableArrayList("ml", "g");
		form_type_quantite.setItems(typeQteChoices);

		// Récupération des ustensiles
		udao = new UstensileDAO();
		ustensiles = udao.getUstensilesNames();
		ObservableList<String> ustensilesChoices = FXCollections.observableArrayList(ustensiles);
		form_ustensile.setItems(ustensilesChoices);
	}

	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}

	private void saveRecette() {

		// Enregistrement de la nouvelle recette
		RecetteDAO rdao = new RecetteDAO();
		String nom = form_nom.getText();
		String duree = form_duree.getText();
		String diff = form_diff.getValue();
		String prix = form_prix.getText();
		Recette r = new Recette(nom, "",  Integer.parseInt(duree), diff, prix, null, null, null, null);
		rdao.create(r);
		
		// Récupération de l'id de la nouvelle recette
		int id_recette = rdao.getLastId();
		
				
		// Enregistrement des étapes
		ObservableList<Node> box = etapesBox.getChildren(); // List
		EtapeDAO edao = new EtapeDAO();
		int numEtape = 1;
		
		for(Node i : box)  {
			String textEtape = ((TextArea) ((HBox) i).getChildren().get(1)).getText(); 
			Etape e = new Etape(id_recette, numEtape, textEtape);
			edao.create(e);
			numEtape++;
		}

		// Enregistrement des ustensiles
		box = ustensilesBox.getChildren(); // List
		List<Ustensile> ustensiles = null;
		RecetteUstensileDAO rudao = new RecetteUstensileDAO();
		for(Node i : box)  {
			Ustensile tmp = udao.getUstensileId(((ChoiceBox<String>) ((HBox) i).getChildren().get(1)).getValue()); 	
			System.out.print(tmp);
			RecetteUstensile ru = new RecetteUstensile(id_recette, tmp);
			rudao.create(ru);			
		}

		// Enregistrement des ingrédients
		box = ingredientsBox.getChildren(); // List
		List<Ingredient> ingredients = null;
		QuantiteIngredientDAO qidao = new QuantiteIngredientDAO();
		for(Node i : box)  {
			Ingredient tmp = idao.getIngredientId(((ChoiceBox<String>) ((HBox) i).getChildren().get(1)).getValue());
			int qte = Integer.parseInt(((TextField) ((HBox) i).getChildren().get(3)).getText()); 
			QuantiteIngredient qi = new QuantiteIngredient(id_recette, tmp, qte, null);
			qidao.create(qi);
		}

		// Enregistrement des régimes
		box = regimeBox.getChildren(); // List
		List<Regime> regimes = null;
		for(Node i : box)  {
			Regime tmp = rrd.getRegimeId(((ChoiceBox<String>) ((HBox) i).getChildren().get(1)).getValue()); 
			RecetteRegime rr = new RecetteRegime(id_recette, tmp);
			rrd.create(rr);
		}
		
		// TODO Affichage d'un message de succès
		// Ou affichage d'un message d'erreur
		redirect();
	}

	@FXML
	public void ajouterLigneIngredient(ActionEvent event) {
		try {
			HBox form = FXMLLoader.load(getClass().getResource("./../scenes/FormIngredient.fxml"));
			ObservableList<String> ingredientsChoices = FXCollections.observableArrayList(ingredients);
			((ChoiceBox) form.getChildren().get(1)).setItems(ingredientsChoices);
			
			ObservableList<String> typeQteChoices = FXCollections.observableArrayList("ml", "g");
			((ChoiceBox) form.getChildren().get(5)).setItems(typeQteChoices);
			

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
		redirect();
	}

	@FXML
	public void enregistrerRecette(ActionEvent event) {
		saveRecette();
	}
	
	private void redirect() {
		try {
			model.switchScene(CreationScenes.creerMainScene(model));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}




}
