package worldcompany;

// TODO Ecrire la classe Commercial ici.
// 
public class Commercial extends Employe {
	private double chiffreAffaire;

	public Commercial(String nom, String prenom, int anneEmbauche,double chiffreAffaire) {
		super(nom, prenom, anneEmbauche);
		// TODO Auto-generated constructor stub
		this.chiffreAffaire=chiffreAffaire;
	}
	
	public double getChiffreAffaire() {
		return chiffreAffaire;
	}
	
}