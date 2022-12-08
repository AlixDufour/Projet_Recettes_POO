package application;

public class RecetteRegime {
	int recetteId;
	Regime regime;
	
	public RecetteRegime(int recetteId, Regime r) {
		this.recetteId = recetteId;
		this.regime = r;
	}
	
	public int getRecetteId() {return this.recetteId;}
	public Regime getRegime() {return this.regime;}
}
