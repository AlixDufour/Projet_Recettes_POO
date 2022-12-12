package application;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	int id;
	String prenom;
	String nom;
	Regime regime;
	List<Ingredient> gouts;
	List<Ustensile> ustensiles;
	
	
	public Profile(int id, String prenom, String nom, Regime regime, List<Ingredient> gouts, List<Ustensile> ustensiles) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.regime = regime;
		this.gouts = gouts;
		this.ustensiles = ustensiles;
	}
	
	public Profile() {
		this.prenom = "prenom";
		this.nom = "nom";
		this.regime = new Regime();
		this.gouts = new ArrayList<Ingredient>();
		this.ustensiles = new ArrayList<Ustensile>();
	}

	public int getId() {return this.id;}
	public String getPrenom() {return this.prenom;}
	public String getNom() {return this.nom;}
	public Regime getRegime() {return this.regime;}

	public List<Ingredient> getGouts() {
		return this.gouts;
	}
	
	public List<Ustensile> getUstensile() {
		return this.ustensiles;
	}
	
	public String toString() {
		String s = "Prénom : " + this.prenom + "\n";
		s += "Nom : " + this.nom + "\n";
		s += "Régime : " + this.regime.getLibelle() + "\n";
		
		s+= "Ingredient à éviter : \n";
		for(Ingredient i : gouts) {
			s += i.getNom() + "\n";
		}
		
		s+= "Equipement (celui qu'on a pas je crois) : \n";
		for(Ustensile u : ustensiles) {
			s += u.getNom() + "\n";
		}
		
		return s;
	}
}
