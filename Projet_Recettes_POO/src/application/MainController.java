package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
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
	
	@FXML
	private ImageView imgEntree, imgPlat, imgDessert, imgViande, imgPoisson, imgLegumes;

	
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
	
	public void entreeHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgEntree.setEffect(blackout);
	}
	public void platHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgPlat.setEffect(blackout);
	}
	public void dessertHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgDessert.setEffect(blackout);
	}
	public void viandeHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgViande.setEffect(blackout);
	}
	public void poissonHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgPoisson.setEffect(blackout);
	}
	public void legumesHover() {
		 ColorAdjust blackout = new ColorAdjust();
	     blackout.setBrightness(-0.5f);
		
	     imgLegumes.setEffect(blackout);
	}
	
	
	
	public void entreeUnHover() {
		if(this.categorie != "entree")
	     imgEntree.setEffect(null);
	}
	public void platUnHover() {
		if(this.categorie != "plat")
	     imgPlat.setEffect(null);
	}
	public void desserUntHover() {

		if(this.categorie != "dessert")
	     imgDessert.setEffect(null);
	}
	public void viandeUnHover() {
		if(this.categorie != "viande")
	     imgViande.setEffect(null);
	}
	public void poissonUnHover() {
		if(this.categorie != "poisson")
	     imgPoisson.setEffect(null);
	}
	public void legumesUnHover() {
		if(this.categorie != "legumes")
	     imgLegumes.setEffect(null);
	}
	
	
	public void entreeClicked() {
		
		
		if(categorie == "entree") {
			categorie = "";
			this.imgEntree.setEffect(null);
			this.rechercher();
			return;
		}
		
		categorie = "entree";
		imgLegumes.setEffect(null);
		this.imgPlat.setEffect(null);
		this.imgDessert.setEffect(null);
		this.imgPoisson.setEffect(null);
		this.imgViande.setEffect(null);
		this.rechercher();

	}
	public void platClicked() {

		if(categorie == "plat") {
			categorie = "";
			this.imgPlat.setEffect(null);
			
			this.rechercher();
			return;
		}
		
		categorie = "plat";
		imgLegumes.setEffect(null);
		this.imgEntree.setEffect(null);
		this.imgDessert.setEffect(null);
		this.imgPoisson.setEffect(null);
		this.imgViande.setEffect(null);
		this.rechercher();

	}
	public void desserClicked() {

		
		if(categorie == "dessert") {
			categorie = "";
			this.imgDessert.setEffect(null);
			this.rechercher();
			return;
		}

		categorie = "dessert";
		imgLegumes.setEffect(null);
		this.imgPlat.setEffect(null);
		this.imgEntree.setEffect(null);
		this.imgPoisson.setEffect(null);
		this.imgViande.setEffect(null);
		this.rechercher();

	}
	public void viandeClicked() {
		

		if(categorie == "viande") {
			categorie = "";
			this.imgViande.setEffect(null);
			this.rechercher();
			return;
		}
		categorie = "viande";
		imgLegumes.setEffect(null);
		this.imgPlat.setEffect(null);
		this.imgDessert.setEffect(null);
		this.imgPoisson.setEffect(null);
		this.imgEntree.setEffect(null);
		this.rechercher();

	}
	public void poissonClicked() {
		
		
		if(categorie == "poisson") {
			categorie = "";
			this.imgPoisson.setEffect(null);
			this.rechercher();
			return;
		}
		
		categorie = "poisson";
		imgLegumes.setEffect(null);
		this.imgPlat.setEffect(null);
		this.imgDessert.setEffect(null);
		this.imgEntree.setEffect(null);
		this.imgViande.setEffect(null);
		this.rechercher();
	}
	public void legumesClicked() {
		
		
		if(categorie == "legumes") {
			categorie = "";
			this.imgLegumes.setEffect(null);
			this.rechercher();
			return;
		}

		categorie = "legumes";
		this.imgEntree.setEffect(null);
		this.imgPlat.setEffect(null);
		this.imgDessert.setEffect(null);
		this.imgPoisson.setEffect(null);
		this.imgViande.setEffect(null);
		this.rechercher();
	}
}
