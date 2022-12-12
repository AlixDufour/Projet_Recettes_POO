package application;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	String prenom;
	String nom;
	Regime regime;
	List<Ingredient> gouts;
	List<Ustensile> ustensiles;
	
	
	public Profile(String prenom, String nom, Regime regime, List<Ingredient> gouts, List<Ustensile> ustensiles) {
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

	public void setPrenom(String s) {
		prenom = s;
	}
	public String getPrenom() {return this.prenom;}
	public String getNom() {return this.nom;}
	public Regime getRegime() {return this.regime;}

	public List<Ingredient> getGouts() {
		return this.gouts;
	}
	
	// retourne true si bien retirer, false si il n'était pas dans la liste
	public Boolean removeIngredient(Ingredient ingredient) {
		for (Ingredient i : gouts) {
			if (i.isSameAs(ingredient)) {
				gouts.remove(i);
				return true;
			}
		}
		return false;
	}

	// retourne true si bien ajouté, false si il était déjà dans la liste
	public Boolean addIngredient(Ingredient ingredient) {
		for (Ingredient i : gouts) {
			if (i.isSameAs(ingredient)) {
				return false;
			}
		}
		gouts.add(ingredient);
		return true;
	}

	// retourne true si bien retirer, false si il n'était pas dans la liste
	public Boolean removeUstensile(Ustensile ustensile) {
		for (Ustensile u : ustensiles) {
			if (u.isSameAs(ustensile)) {
				ustensiles.remove(u);
				return true;
			}
		}
		return false;
	}

	// retourne true si bien ajouté, false si il était déjà dans la liste
	public Boolean addUstensile(Ustensile ustensile) {
		for (Ustensile u : ustensiles) {
			if (u.isSameAs(ustensile)) {
				return false;
			}
		}
		ustensiles.add(ustensile);
		return true;
	}

	public List<Ustensile> getUstensiles() {
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
