package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Modele {

	private final ArrayList<Observateur> obs = new ArrayList<>();

	private Stage stage;
	private Profile activeProfile;
	
	public Modele(Stage stage) {
		this.stage = stage;
		
		// Sera affecté lors du choix du profil, soit profil déjà existant, soit création d'un nouveau profil
		activeProfile = null;
	}

	public void ajouterObservateur(Observateur o) {
		obs.add(o);
	}

	public void notifierObservateur() {
		for (Observateur o : obs) {
			o.reagir();
		}
	}
		
	// Changement de scène
	public void switchScene(Scene newScene) {
		stage.setScene(newScene);
	}

	
	public void connexion(Profile p) {
		//Appelé dans la page de choix de profil quand on clique sur le profile, on change de scène et on donne le profile en paramètre
		
		this.activeProfile = p;
		try {
			this.switchScene(CreationScenes.creerMainScene(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.notifierObservateur();
	}
}
