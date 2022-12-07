package application;

public class Ingredient {
	String nom;
	
	public Ingredient(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {return this.nom;}
	public String toString() {
		return "Nom : " + this.nom + "\n";
	}
}
