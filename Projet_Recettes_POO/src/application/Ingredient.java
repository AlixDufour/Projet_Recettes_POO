package application;

public class Ingredient {
	int id;
	String nom;
	
	public Ingredient(int id, String nom) {
		this.nom = nom;
		this.id = id;
	}
	
	public int getId() {return this.id;}
	public String getNom() {return this.nom;}
	public String toString() {
		return "Nom : " + this.nom + "\n";
	}
}
