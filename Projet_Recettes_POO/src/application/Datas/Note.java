package application.Datas;

public class Note {
	int profileId;
	int recetteId;
	int note;
	
	public Note(int profileId, int recetteId, int note) {
		this.profileId = profileId;
		this.recetteId = recetteId;
		this.note = note;
	}
	
	public int getProfileId() {return this.profileId;}
	public int getRecetteId() {return this.recetteId;}
	public int getNote() {return this.note;}
	
	public void setNote(int note) {this.note = note;}
	
	public String toString() {
		String s = "Profile : " + this.profileId + "\n"; 
		s += "Recette : " + this.recetteId + "\n"; 
		s += "Note : " + this.note + "/5 \n"; 
		
		return s;
	}
}
