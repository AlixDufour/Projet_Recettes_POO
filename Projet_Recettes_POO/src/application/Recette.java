package application;

import java.util.HashMap;
import java.util.List;

public class Recette {

	String nom;
	String description;
	int duree;
	String difficulte;
	String prix;
	
	List<String> etapes;
	HashMap<Integer, String> regimes;
	
	public Recette(String nom, String desc, int duree, String diff, String prix, List <String >etapes) {
		this.nom = nom;
		this.description = desc;
		this.duree = duree;
		this.difficulte = diff;
		this.prix = prix;
		this.etapes = etapes;
		regimes = null;
	}
	
	public String toString() {
		String s = "Nom : " + this.nom + "\n";
		s += "Description : " + this.description + "\n";
		s += "Durée : " + this.duree + "\n";
		s += "Difficulté : " + this.difficulte + "\n";
		s += "Prix : " + this.prix + "\n";
		s += "Etapes : \n";
		for(String d : this.etapes) {
			s += d + "\n";
		}
		
		return s;
	}
	
	public String getName() {return this.nom;}
	public String getDesc() {return this.description;}
	public int getDuree() {return this.duree;}
	public String getDifficulte() {return this.difficulte;}
	public String getPrix() {return this.prix;}
}
