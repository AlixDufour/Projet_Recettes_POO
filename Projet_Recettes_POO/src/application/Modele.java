package application;

import java.io.IOException;
import java.util.ArrayList;

import dao.RecetteDAO;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Modele {

	private final ArrayList<Observateur> obs = new ArrayList<>();

	private Stage stage;
	private Profile activeProfile;
	private ArrayList<Recette> recettes;
	private ArrayList<Recette> recettesFiltrees;
	
	public Modele(Stage stage) {
		this.stage = stage;
		RecetteDAO rDao = new RecetteDAO();
		recettes = (ArrayList<Recette>) rDao.getAll();
		
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

	public Profile getActiveProfile() {
		return activeProfile;
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
	
	
	public void filtrerRecettes(String champRecherche,String categorie,boolean appliquerPreferences){
		
		this.recettesFiltrees = new ArrayList<>(this.recettes);
		// On itère dans chaque recette pour comparer avec les préférences/ catégorie et champ de recherche indiqué de l'utilisateur
		for(Recette r : this.recettes) {
				
				// Comparaison de la catégorie avec les tags
				if(categorie != "") {
					boolean checkTag = false;
					for(String t : r.getTags()) {
					
						if(categorie.toLowerCase().compareTo(t) == 0) checkTag = true;
					}
					if(!checkTag) {this.recettesFiltrees.remove(r); continue;}
					
				}
				
				boolean correspondance = false;
				
				// Recherche d'une correspondance dans les noms des recettes
				if(r.getName().toLowerCase().contains(champRecherche.toLowerCase())) correspondance =true;
				
				// Recherche d'une correspondance dans les ingrédients
				else
					{
					for(QuantiteIngredient i : r.getIngredients()) {
						if (i.getIngredient().getNom().toLowerCase().contains(champRecherche.toLowerCase())) correspondance = true;
					}
				}
				if (correspondance == false) this.recettesFiltrees.remove(r);
				
				// Si les préférences sont activée, on filtre
				if (appliquerPreferences == true) {
					
					// On regarde parmi les ingrédients

					for(Ingredient g : this.activeProfile.gouts) {				
						{for(QuantiteIngredient i : r.getIngredients()) {
							if(g.getId() == i.getIngredient().getId()) {
									this.recettesFiltrees.remove(r);
									break;
								}
							}
						}
					}
					
					// On regarde parmi les ustensiles
					for(Ustensile ru : r.getUstensiles()) {
						boolean checkUstensile = false;
						for(Ustensile u : this.activeProfile.ustensiles) {
							if(u.getId() == ru.getId()) checkUstensile = true;
						}
						if(!checkUstensile) {
							this.recettesFiltrees.remove(r);
							break;
						}
					}
					
					boolean checkRegime = false;
					for(Regime rr : r.getRegimes()) {
						if(this.activeProfile.getRegime().getId() == rr.getId()) {checkRegime = true; break;}
					}
					if(!checkRegime) this.recettesFiltrees.remove(r);
					
				}
		}
		
		this.notifierObservateur();
	}
	
	public ArrayList<Recette> getRecetteFiltrees(){
		return this.recettesFiltrees;
	}
}
