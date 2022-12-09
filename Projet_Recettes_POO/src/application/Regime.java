package application;

public class Regime {
	int id;
	String libelle;
	
	public Regime(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	
	public Regime() {
		id = 0;
		libelle = "classique";
	}

	public int getId() {return this.id;}
	public String getLibelle() {return this.libelle;}
}
