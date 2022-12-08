package application;

public class QuantiteIngredient {
	
	int recetteId;
	Ingredient ingredient;
	int quantite;
	TypeQuantite typeQuantite;
	
	public QuantiteIngredient(int recetteId, Ingredient i, int quant, TypeQuantite tq) {
		this.recetteId = recetteId;
		this.ingredient = i;
		this.quantite = quant;
		this.typeQuantite = tq;
	}
	
	public int getId() {return this.recetteId;}
	public Ingredient getIngredient() {return this.ingredient;}
	public int getQuantite() {return this.quantite;}
	public TypeQuantite getTypeQuantite() {return this.typeQuantite;}
}
