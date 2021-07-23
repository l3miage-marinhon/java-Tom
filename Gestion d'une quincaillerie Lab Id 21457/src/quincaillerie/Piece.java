package quincaillerie;

// TODO Question 1: Ecrire la classe Piece.
// 
public abstract class Piece {
	//TODO une piece  1 ref qui peut ressembler a ça 89B13 et un nom
	private String ref;
	private String nom;
	private double prix;
	private int dureeGarantie;
	private int dureeGarantieBase;
	private int dureeFabrication;
	
	public Piece(String nom, double prix, int dureeGarantieBase, int dureeFabrication) {
		setNom(nom);
		setPrix(prix);
		setDureeGarantieBase(dureeGarantieBase);
		setDureeGarantie(dureeGarantieBase);
		setDureeFabrication(dureeFabrication);
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = (prix>0 ? prix : 1);
	}
	
	public int getDureeGarantie() {
		return dureeGarantie;
	}
	public void setDureeGarantie(int dureeGarantie) {
		this.dureeGarantie = (dureeGarantie>0 ? dureeGarantie : 1);
	}
	
	public int getDureeGarantieBase() {
		return dureeGarantieBase;
	}
	public void setDureeGarantieBase(int dureeGarantieBase) {
		this.dureeGarantieBase = (dureeGarantieBase>0 ? dureeGarantieBase : 1);
	}
	
	public int getDureeFabrication() {
		return dureeFabrication;
	}
	public void setDureeFabrication(int dureeFabrication) {
		this.dureeFabrication = (dureeFabrication>0 ? dureeFabrication : 1);
	}
	
	
	// une pieces a des propriété qui lui sont propre peut importe son type de piece
	//on peut lui chnager ça ref
	//onpeut l'afficher
	//on peut avoir son prix
	// on peut avoir ça dureer de garantie
	//on peut avoir ça dureer de garantie de base
	//on peut avoir ça dureer de fabrication
	
	@Override
	public String toString() {
		return "Reference : " + getRef()
			+ "\nNom : " + getNom()
			+ "\nPrix : " + getPrix() + (getPrix()>1 ? " euros" : " euro")
			+ "\nDurée garantie : " + getDureeGarantie() + " mois"
			+ "\nDurée garantie base : " + getDureeGarantieBase() + " mois"
			+ "\nDurée fabrication : " + getDureeFabrication() + (getDureeFabrication()>1 ? " heures" : " heure");
	}
	
}

