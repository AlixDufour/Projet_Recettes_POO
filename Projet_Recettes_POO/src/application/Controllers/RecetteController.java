package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.*;
import application.Datas.*;
import dao.CommentaireDAO;
import dao.NoteDAO;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RecetteController extends Controller {

	private Recette r;
	private ColorAdjust jaune;
	private ColorAdjust hoverJaune;
	private Note note;

	@FXML
	Label titreRecette, duree, niveau, prix, quantite;
	
	
	@FXML
	VBox ingredientBox, ustensileBox, etapeBox, test;
	
	@FXML
	TabPane testTab;
	
	@FXML
	TextArea commField;
	
	@FXML
	ImageView etoile1, etoile2, etoile3, etoile4,etoile5;
	
	@Override
	public void reagir() {

		r = model.getSelectedRecette();
		
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
		
		// Affichage commentaire
		CommentaireDAO cDao = new CommentaireDAO();
		Commentaire comm = cDao.getCommentaireByIDs(this.model.getActiveProfile().getId(), r.getId());
		if(comm != null) {
			commField.setText(comm.getCommentaire());
		}

		
		this.updateNote();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jaune = new ColorAdjust();
		jaune.setBrightness(0.5);
		jaune.setHue(0.2);
		jaune.setSaturation(2.0);
		jaune.setContrast(0.2);
		
		hoverJaune = new ColorAdjust();
		hoverJaune.setBrightness(0.5);
		hoverJaune.setHue(0.1);
		hoverJaune.setSaturation(2.0);
		hoverJaune.setContrast(0.2);
		
	}

	
	public void changeCommentaire() {
		
		CommentaireDAO cDao = new CommentaireDAO();
		Commentaire comm = cDao.getCommentaireByIDs(this.model.getActiveProfile().getId(), r.getId());
		
		if(comm != null) {
			comm.setCommentaire(commField.getText());
			cDao.update(comm, null);
		}else {
			Commentaire newComm = new Commentaire(this.model.getActiveProfile().getId(), r.getId(), commField.getText());
			cDao.create(newComm);
		}
		
	}
	
	
	@FXML
	public void retourAccueil() {
		try {
			model.switchScene(CreationScenes.creerMainScene(model));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void updateNote() {
		NoteDAO nDao = new NoteDAO();
		note = nDao.getNoteByIDs(this.model.getActiveProfile().getId(), r.getId());
		
		etoile1.setEffect(null);
		etoile2.setEffect(null);
		etoile3.setEffect(null);
		etoile4.setEffect(null);
		etoile5.setEffect(null);
		
		if(note != null) {
			etoile1.setEffect(jaune);
			if(note.getNote() > 1) {
				etoile2.setEffect(jaune);
			}
			if(note.getNote() > 2) {
				etoile3.setEffect(jaune);
			}if(note.getNote() > 3) {
				etoile4.setEffect(jaune);
			}if(note.getNote() > 4) {
				etoile5.setEffect(jaune);
			}	
		}
	}
	
	public void hoverEtoile1() {
		etoile1.setEffect(hoverJaune);
		
	}
	public void hoverEtoile2() {
		etoile2.setEffect(hoverJaune);
		if(note == null) {
			etoile1.setEffect(jaune);
		}
		
	}
	public void hoverEtoile3() {
		etoile3.setEffect(hoverJaune);
		if(note == null || note.getNote() == 1) {
			etoile2.setEffect(jaune);
			etoile1.setEffect(jaune);
		}
		
	}
	public void hoverEtoile4() {
		etoile4.setEffect(hoverJaune);
		if(note == null || note.getNote() <= 3) {
			etoile2.setEffect(jaune);
			etoile1.setEffect(jaune);
			etoile3.setEffect(jaune);
		}
	}
	public void hoverEtoile5() {
		etoile5.setEffect(hoverJaune);
		if(note == null || note.getNote() <= 4) {
			etoile2.setEffect(jaune);
			etoile1.setEffect(jaune);
			etoile3.setEffect(jaune);
			etoile4.setEffect(jaune);
		}
	}
	
	public void clickEtoile1() {this.setNote(1);}
	public void clickEtoile2() {this.setNote(2);}
	public void clickEtoile3() {this.setNote(3);}
	public void clickEtoile4() {this.setNote(4);}
	public void clickEtoile5() {this.setNote(5);}
	
	public void setNote(int newNote) {
		NoteDAO nDao = new NoteDAO();
		note = nDao.getNoteByIDs(this.model.getActiveProfile().getId(), r.getId());
		
		if(note == null) {
			Note n = new Note(this.model.getActiveProfile().getId(), r.getId(), newNote);
			nDao.create(n);
		}else {
			note.setNote(newNote);
			nDao.update(note, null);
		}
		this.updateNote();
	}
	
	public void plus() {
		quantite.setText(String.valueOf(Integer.valueOf(quantite.getText()) + 1));
		this.reagir();
	}
	
	public void moins() {
		int quantValue = Integer.valueOf(quantite.getText());
		if(quantValue > 1) {
			quantValue--;
			quantite.setText(String.valueOf(quantValue));
		}
		this.reagir();
	}
}
