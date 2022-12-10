package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProfileController implements Initializable, Observateur {

	private Modele model;
	
	@FXML
	private TextField editName;

	@FXML
	private ChoiceBox regimeSelect;

	@FXML
	private VBox listeIngredients;

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
	
	public void updateProfileInfos() {
		updateName();
		// updateIngredients();
	}

	public void updateIngredients() {
		if (model != null) {
			ArrayList<Ingredient> gouts = (ArrayList<Ingredient>) model.getActiveProfile().getGouts();
			for (Ingredient i : gouts) {
				Label l = new Label(i.getNom());
				listeIngredients.getChildren().add(l);
			}
		}

	}

	public void updateName() {
		if (model != null)
			editName.setText(model.getActiveProfile().getPrenom());
	}

}
