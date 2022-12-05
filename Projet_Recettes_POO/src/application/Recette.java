package application;

public class Recette {

	String nom;
	String description;
	int duree;
	String difficulte;
	String prix;
	
	
	public Recette(String nom, String desc, int duree, String diff, String prix) {
		this.nom = nom;
		this.description = desc;
		this.duree = duree;
		this.difficulte = diff;
		this.prix = prix;
	}
	
	public String toString() {
		String s = "Nom : " + this.nom + "\n";
		s += "Description : " + this.description + "\n";
		s += "Durée : " + this.duree + "\n";
		s += "Difficulté : " + this.difficulte + "\n";
		s += "Prix : " + this.prix + "\n";
		
		return s;
	}
	
	public String getName() {return this.nom;}
	public String getDesc() {return this.description;}
	public int getDuree() {return this.duree;}
	public String getDifficulte() {return this.difficulte;}
	public String getPrix() {return this.prix;}
}
