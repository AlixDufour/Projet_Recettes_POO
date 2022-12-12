package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RecetteController implements Initializable, Observateur {

	private Modele model;

	@FXML
	Label titreRecette, duree, niveau, prix, quantite;
	
	
	@FXML
	VBox ingredientBox, ustensileBox, etapeBox, test;
	
	@FXML
	TabPane testTab;
	
	
	@Override
	public void reagir() {

		Recette r = model.getSelectedRecette();
		
		titreRecette.setText(r.getName());
		duree.setText(r.getDuree() + " min");
		niveau.setText(r.getDifficulte());
		prix.setText(r.getPrix());
		
		// Creation de la liste des ingrédients
		ingredientBox.getChildren().clear();
		for(QuantiteIngredient i : r.getIngredients()) {
			HBox h = new HBox();
			Label nom = new Label(i.getIngredient().getNom() + " : ");
			Label quant = new Label(i.getQuantite() * Integer.valueOf(quantite.getText())  + i.getTypeQuantite().getLibelle());
			h.getChildren().add(nom);
			nom.setPadding(new Insets(0,10,0,10));
			h.getChildren().add(quant);
			
			nom.setFont(new Font("System", 15));
			quant.setFont(new Font("System", 15));
			
			ingredientBox.getChildren().add(h);
		}
		
		// Creation liste des ustensiles
		ustensileBox.getChildren().clear();
		for (Ustensile u : r.getUstensiles()) {
			Label nom = new Label(u.getNom());
			nom.setFont(new Font("System", 15));
			
			nom.setPadding(new Insets(0,10,0,10));
			
			ustensileBox.getChildren().add(nom);
		}
		
		// Création liste des étapes
		etapeBox.getChildren().clear();
		for(Etape e : r.getEtapes()) {
			HBox h = new HBox();
			
			Label numEtape = new Label("Etape " + e.getNumEtape() + " : ");
			numEtape.setFont(new Font("System", 15));
			numEtape.setPadding(new Insets(0,10,0,10));
			numEtape.setMinWidth(100);
			Label desc = new Label(e.getDescription());
			desc.setWrapText(true);
			desc.setFont(new Font("System", 15));
			h.setPadding(new Insets(0,0,10,0));
			h.getChildren().add(numEtape);
			h.getChildren().add(desc);
			
			etapeBox.getChildren().add(h);
		}
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setModele(Modele m) {
		this.model = m;
		model.ajouterObservateur(this);
	}
	
	@FXML
	public void retourAccueil() {
		try {
			model.switchScene(CreationScenes.creerChoixProfileScene(model));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
