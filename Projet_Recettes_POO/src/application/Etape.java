package application;

public class Etape {
	int recetteId;
	int num_etape;
	String description;
	
	public Etape(int recetteId, int num_etape, String description) {
		this.recetteId = recetteId;
		this.num_etape = num_etape;
		this.description = description;
	}
	
	public String getDescription() {return this.description;}
}
