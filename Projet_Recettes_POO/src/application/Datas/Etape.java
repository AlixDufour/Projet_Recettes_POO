package application.Datas;

public class Etape {
	int recetteId;
	int num_etape;
	String description;
	
	public Etape(int recetteId, int num_etape, String description) {
		this.recetteId = recetteId;
		this.num_etape = num_etape;
		this.description = description;
	}
	
	public int getRecetteId() {return this.recetteId;}
	public int getNumEtape() {return this.num_etape;}
	public String getDescription() {return this.description;}
}
