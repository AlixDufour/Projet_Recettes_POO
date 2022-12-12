package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Recette {

	int id;
	String nom;
	String description;
	int duree;
	String difficulte;
	String prix;
	String tags;
	
	List<Etape> etapes;
	List<Ustensile> ustensiles;
	List<QuantiteIngredient> ingredients;
	List<Regime> regimes;
	
	public Recette(int id, String nom, String desc, int duree, String diff, String prix, String tags, List <Etape>etapes, List<Ustensile> ustensiles, 
			List<QuantiteIngredient> ingr, List<Regime> regimes) {
		this.id = id;
		this.nom = nom;
		this.description = desc;
		this.duree = duree;
		this.difficulte = diff;
		this.prix = prix;
		this.tags = tags;
		this.etapes = etapes;
		this.ustensiles = ustensiles;
		this.ingredients = ingr;
		this.regimes = regimes;
	}
	
	public Recette(String nom, String desc, int duree, String diff, String prix, String tags, List <Etape>etapes, List<Ustensile> ustensiles, 
			List<QuantiteIngredient> ingr, List<Regime> regimes) {
		this.nom = nom;
		this.description = desc;
		this.duree = duree;
		this.difficulte = diff;
		this.prix = prix;
		this.tags = tags;
		this.etapes = etapes;
		this.ustensiles = ustensiles;
		this.ingredients = ingr;
		this.regimes = regimes;
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
		
		s += "Convient aux régimes : \n";
		for(Regime r : this.regimes) {
			s += r.getLibelle() + "\n";
		}
		
		return s;
	}
	
	public int getId() {return this.id;}
	public String getName() {return this.nom;}
	public String getDesc() {return this.description;}
	public int getDuree() {return this.duree;}
	public String getDifficulte() {return this.difficulte;}
	public String getPrix() {return this.prix;}
	
	public List<String> getTags(){
		return new ArrayList<String>(Arrays.asList(tags.split(";")));
	} 
	
	public String getUnformatedTags() {
		return this.tags;
	}
	
	public void addTag(String t) {
		tags += ";"+t;
	}
	
	public List<Etape> getEtapes(){return this.etapes;}
	public List<Ustensile> getUstensiles(){return this.ustensiles;}
	public List<QuantiteIngredient> getIngredients(){return this.ingredients;}
	public List<Regime> getRegimes(){return this.regimes;}
}
