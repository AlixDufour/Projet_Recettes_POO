package application.Datas;

public class RecetteUstensile {
	int recetteId;
	Ustensile ustensile;
	
	public RecetteUstensile(int recetteId, Ustensile u){
		this.recetteId = recetteId;
		this.ustensile = u;
	}
	
	public int getRecetteId() {return this.recetteId;}
	public Ustensile getUstensile() {return this.ustensile;}
	
}
