package application;

import java.util.HashMap;
import java.util.List;

public class Recette {

	String nom;
	String description;
	int duree;
	String difficulte;
	String prix;
	
	List<Etape> etapes;
	List<Ustensile> ustensiles;
	List<QuantiteIngredient> ingredients;
	
	HashMap<Integer, String> regimes;
	
	public Recette(String nom, String desc, int duree, String diff, String prix, List <Etape>etapes, List<Ustensile> ustensiles, 
			List<QuantiteIngredient> ingr) {
		this.nom = nom;
		this.description = desc;
		this.duree = duree;
		this.difficulte = diff;
		this.prix = prix;
		this.etapes = etapes;
		this.ustensiles = ustensiles;
		this.ingredients = ingr;
		
		regimes = null;
	}
	
	public String toString() {
		String s = "Nom : " + this.nom + "\n";
		s += "Description : " + this.description + "\n";
		s += "Durée : " + this.duree + "\n";
		s += "Difficulté : " + this.difficulte + "\n";
		s += "Prix : " + this.prix + "\n";
		s += "Etapes : \n";
		for(Etape e : this.etapes) {
			s += e.getDescription() + "\n";
		}
		
		s += "Ustensiles : \n";
		for(Ustensile u : this.ustensiles) {
			s += u.getNom() + "\n";
		}
		
		s += "Ingredients : \n";
		for(QuantiteIngredient i : this.ingredients) {
			s += i.getIngredient().getNom() + " : "+ i.getQuantite() + " " + i.getTypeQuantite().getLibelle() + "\n";
		}
		
		return s;
	}
	
	public String getName() {return this.nom;}
	public String getDesc() {return this.description;}
	public int getDuree() {return this.duree;}
	public String getDifficulte() {return this.difficulte;}
	public String getPrix() {return this.prix;}
}
