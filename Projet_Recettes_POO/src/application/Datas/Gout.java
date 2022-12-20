package application.Datas;

public class Gout {
	int profileId;
	Ingredient ingredient;
	
	public Gout(int profileId, Ingredient i) {
		this.profileId = profileId;
		this.ingredient = i;
	}
	
	public int getProfileId() {return this.profileId;}
	public Ingredient getIngredient() {return this.ingredient;}
}
