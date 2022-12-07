package application;

public class Ustensile {
	String nom;
	
	public Ustensile(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {return this.nom;}
	
	public String toString() {return "Nom : " + this.nom + "\n";}
}
