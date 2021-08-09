package pieces;

// TODO Question 1: Ecrire la classe Piece.
// 
public abstract class Piece {
	//TODO une piece  1 ref qui peut ressembler a ça 89B13 et un nom
	private String ref;
	private String nom;
	protected double prix;
	protected int dureeGarantie;
	protected int dureeGarantieBase;
	protected int dureeFabrication;
	
	public Piece(String nom) {
		setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	@Override
	public String toString() {
		return "Reference : " + getRef()
			+ "\nNom : " + getNom();
		/*
			+ "\nPrix : " + getPrix() + (getPrix()>1 ? " euros" : " euro")
			+ "\nDurée garantie : " + getDureeGarantie() + " mois"
			+ "\nDurée garantie base : " + getDureeGarantieBase() + " mois"
			+ "\nDurée fabrication : " + getDureeFabrication() + (getDureeFabrication()>1 ? " heures" : " heure");
			*/
	}
	
}

