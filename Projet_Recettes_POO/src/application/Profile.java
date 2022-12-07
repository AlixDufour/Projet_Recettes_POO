package application;

public class Profile {
	
	String prenom;
	String nom;
	int regimeId;
	String regimeLibelle;
	
	public Profile(String prenom, String nom, int regimeId, String regimeLibelle) {
		this.prenom = prenom;
		this.nom = nom;
		this.regimeId = regimeId;
		this.regimeLibelle = regimeLibelle;
	}
	
	public String getPrenom() {return this.prenom;}
	public String getNom() {return this.nom;}
	public int getRegimeId() {return this.regimeId;}
	public String getRegimeLibelle() {return this.regimeLibelle;}
	
	public String toString() {
		String s = "Prénom : " + this.prenom + "\n";
		s += "Nom : " + this.nom + "\n";
		s += "Régime : " + this.regimeLibelle + "\n";
		
		return s;
	}
}
