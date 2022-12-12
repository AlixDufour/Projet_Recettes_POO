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

	public Boolean isSameAs(Ingredient i) {
		return this.id == i.getId();
	}

	public Boolean nameEquals(String s) {
		return nom.equals(s);
	}
}
