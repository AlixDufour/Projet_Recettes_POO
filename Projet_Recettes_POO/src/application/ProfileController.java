package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.IngredientDAO;
import dao.ProfileDAO;
import dao.UstensileDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProfileController implements Initializable, Observateur {

	private Modele model;
	
	@FXML
	private TextField editName;

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

	@Override
	public void reagir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}
	
	public void updateAllUstensiles() {
		UstensileDAO ustensileDAO = new UstensileDAO();
		List<Ustensile> allUstensilesListe = ustensileDAO.getAll();
		for (Ustensile u : allUstensilesListe) {
			Boolean isIn = false;
			for (Ustensile uProfil : model.getActiveProfile().getUstensiles()) {
				if (u.isSameAs(uProfil)) {
					isIn = true;
				}
			}
			if (!isIn) {
				Button l = new Button(u.getNom());

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
	}

	public void updateAllIngredients() {
		IngredientDAO ingredientDAO = new IngredientDAO();
		List<Ingredient> allIngredientsListe = ingredientDAO.getAll();
		for (Ingredient u : allIngredientsListe) {
			Boolean isIn = false;
			for (Ingredient uProfil : model.getActiveProfile().getGouts()) {
				if (u.isSameAs(uProfil)) {
					isIn = true;
				}
			}
			if (!isIn) {
				Button l = new Button(u.getNom());

				ajoutBoutonAllIngredients(l);
			}
		}
	}

	public void updateIngredients() {
		if (model != null) {
			ArrayList<Ingredient> gouts = (ArrayList<Ingredient>) model.getActiveProfile().getGouts();
			for (Ingredient i : gouts) {
				Button l = new Button(i.getNom());

				ajoutBoutonListeIngredients(l);
			}
		}
	}

	public void updateUstensiles() {
		if (model != null) {
			ArrayList<Ustensile> materiels = (ArrayList<Ustensile>) model.getActiveProfile().getUstensiles();
			for (Ustensile u : materiels) {
				Button l = new Button(u.getNom());

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

