package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.IngredientDAO;
import dao.ProfileDAO;
import dao.RegimeDAO;
import dao.UstensileDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProfileController extends Controller{

	@FXML
	private TextField editName, champIngredient, champUstensile;

	@FXML
	private Button bRetour;
	@FXML
	private Button bSupprimerProfil;

	@FXML
	private ChoiceBox<String> regimeSelect;

	@FXML
	private VBox allIngredients;
	@FXML
	private VBox listeIngredients;

	@FXML
	private VBox allUstensiles;
	@FXML
	private VBox listeUstensiles;

	private List<String> regimes;

	@Override
	public void reagir() {
		//this.updateProfileInfos();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	public void updateAllUstensiles() {
		//UstensileDAO ustensileDAO = new UstensileDAO();
		//List<Ustensile> allUstensilesListe = ustensileDAO.getAll();
		this.allUstensiles.getChildren().clear();
		this.model.filtrerUstensiles(this.champUstensile.getText());
		List<Ustensile> allUstensilesListe = this.model.getUstensileFiltrees();
		for (Ustensile u : allUstensilesListe) {
			Boolean isIn = false;
			for (Ustensile uProfil : model.getActiveProfile().getUstensiles()) {
				if (u.isSameAs(uProfil)) {
					isIn = true;
				}
			}
			if (!isIn) {
				Button l = new Button(u.getNom());
				l.setMaxWidth(Double.MAX_VALUE);

				ajoutBoutonAllUstensiles(l);
			}
		}
	}

	public void updateProfileInfos() {
		updateName();

		updateIngredients();
		updateAllIngredients();

		updateUstensiles();
		updateAllUstensiles();

		updateAllRegime();
	}

	public void updateAllIngredients() {
		//IngredientDAO ingredientDAO = new IngredientDAO();
		//List<Ingredient> allIngredientsListe = ingredientDAO.getAll();
		this.allIngredients.getChildren().clear();
		this.model.filtrerIngredient(this.champIngredient.getText());
		List<Ingredient> allIngredientsListe = this.model.getIngredientFiltrees();
		for (Ingredient u : allIngredientsListe) {
			Boolean isIn = false;
			for (Ingredient uProfil : model.getActiveProfile().getGouts()) {
				if (u.isSameAs(uProfil)) {
					isIn = true;
				}
			}
			if (!isIn) {
				Button l = new Button(u.getNom());
				l.setMaxWidth(Double.MAX_VALUE);

				ajoutBoutonAllIngredients(l);
			}
		}
	}

	public void updateIngredients() {
		this.listeIngredients.getChildren().clear();
		if (model != null) {
			ArrayList<Ingredient> gouts = (ArrayList<Ingredient>) model.getActiveProfile().getGouts();
			for (Ingredient i : gouts) {
				Button l = new Button(i.getNom());
				l.setMaxWidth(Double.MAX_VALUE);
				ajoutBoutonListeIngredients(l);
			}
		}
	}

	public void updateAllRegime() {
		// Récupération des régimes
		RegimeDAO rrd = new RegimeDAO();
		regimes = rrd.getNames();
		ObservableList<String> regimesChoices = FXCollections.observableArrayList(regimes);
		if (model.getActiveProfile().getRegime() == null)
			regimeSelect.setValue(regimes.get(0));
		else
			regimeSelect.setValue(model.getActiveProfile().getRegime().getLibelle());
		regimeSelect.setItems(regimesChoices);
	}

	public void updateUstensiles() {
		this.listeUstensiles.getChildren().clear();
		if (model != null) {
			ArrayList<Ustensile> materiels = (ArrayList<Ustensile>) model.getActiveProfile().getUstensiles();
			for (Ustensile u : materiels) {
				Button l = new Button(u.getNom());
				l.setMaxWidth(Double.MAX_VALUE);
				ajoutBoutonListeUstensiles(l);

			}
		}
	}

	public void ajoutBoutonListeUstensiles(Button b) {
		listeUstensiles.getChildren().add(b);
		b.setOnAction(e -> {
			listeUstensiles.getChildren().remove(b);
			ajoutBoutonAllUstensiles(b);
			// METTRE A JOUR PROFIL ET BDD AUSSI
			model.getActiveProfile().removeUstensile(getUstensileFromButton(b));
		});
	}

	public void ajoutBoutonAllUstensiles(Button b) {
		allUstensiles.getChildren().add(b);
		b.setOnAction(e -> {
			allUstensiles.getChildren().remove(b);
			ajoutBoutonListeUstensiles(b);
			// METTRE A JOUR PROFIL ET BDD AUSSI
			model.getActiveProfile().addUstensile(getUstensileFromButton(b));
		});
	}

	public void ajoutBoutonListeIngredients(Button b) {
		listeIngredients.getChildren().add(b);
		b.setOnAction(e -> {
			listeIngredients.getChildren().remove(b);
			ajoutBoutonAllIngredients(b);
			// METTRE A JOUR PROFIL ET BDD AUSSI
			model.getActiveProfile().removeIngredient(getIngredientFromButton(b));
		});
	}

	public void ajoutBoutonAllIngredients(Button b) {
		allIngredients.getChildren().add(b);
		b.setOnAction(e -> {
			allIngredients.getChildren().remove(b);
			ajoutBoutonListeIngredients(b);
			// METTRE A JOUR PROFIL ET BDD AUSSI
			model.getActiveProfile().addIngredient(getIngredientFromButton(b));
		});
	}

	public void clickRetour() {
		try {
			changeProfileName();
			changeRegime();
			ProfileDAO pdao = new ProfileDAO();
			pdao.update(model.getActiveProfile(), null);
			model.switchScene(CreationScenes.creerMainScene(model));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSupprimerProfil() {
		try {
			ProfileDAO pdao = new ProfileDAO();
			pdao.delete(model.getActiveProfile());
			model.switchScene(CreationScenes.creerChoixProfileScene(model));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeRegime() {
		RegimeDAO rrd = new RegimeDAO();
		Regime regime = rrd.getRegimeId(regimeSelect.getValue());
		if (regime != null) {
			model.getActiveProfile().setRegime(regime);
		}
	}


	public void updateName() {
		if (model != null)
			editName.setText(model.getActiveProfile().getPrenom());
	}

	public static Ingredient getIngredientFromButton(Button b) {
		IngredientDAO ingredientDAO = new IngredientDAO();
		String s = b.getText();
		if (s == null)
			return null;
		List<Ingredient> allIngredientsListe = ingredientDAO.getAll();
		for (Ingredient i : allIngredientsListe) {
			if (i.nameEquals(s))
				return i;
		}
		return null;
	}

	public void changeProfileName() {
		model.getActiveProfile().setPrenom(editName.getText());
	}

	public static Ustensile getUstensileFromButton(Button b) {
		UstensileDAO ustensileDAO = new UstensileDAO();
		String s = b.getText();
		if (s == null)
			return null;
		List<Ustensile> allUstensilesListe = ustensileDAO.getAll();
		for (Ustensile i : allUstensilesListe) {
			if (i.nameEquals(s))
				return i;
		}
		return null;
	}

}

