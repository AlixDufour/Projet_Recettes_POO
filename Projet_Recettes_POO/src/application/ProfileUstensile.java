package application;

public class ProfileUstensile {
	int profileId;
	Ustensile ustensile;
	
	public ProfileUstensile(int id, Ustensile u) {
		this.profileId = id;
		this.ustensile = u;
	}
	
	public int getProfileId() {return this.profileId;}
	public Ustensile getUstensile() {return this.ustensile;}
	
}
