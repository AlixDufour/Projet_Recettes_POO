package application;

public class Ustensile {
	
	int id;
	String nom;
	
	public Ustensile(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public int getId() {return this.id;}
	
	public String getNom() {return this.nom;}
	
	public String toString() {return "Nom : " + this.nom + "\n";}

	public Boolean isSameAs(Ustensile u) {
		return this.id == u.getId();
	}

	public Boolean nameEquals(String s) {
		return nom.equals(s);
	}
}
