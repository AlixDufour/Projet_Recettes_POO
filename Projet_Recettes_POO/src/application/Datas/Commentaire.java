package application.Datas;

public class Commentaire {

	private int profileId;
	private int recetteId;
	private String commentaire;
	
	public Commentaire(int profileId, int recetteId, String commentaire) {
		this.profileId = profileId;
		this.recetteId = recetteId;
		this.commentaire = commentaire;
	}
	
	public int getProfileId() {return this.profileId;}
	public int getRecetteId() {return this.recetteId;}
	public String getCommentaire() {return this.commentaire;}
	
	public void setCommentaire(String comm) {this.commentaire = comm;}
}
