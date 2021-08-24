package pieces;

// TODO Question 1: Ecrire la classe Piece.
// 
public abstract class Piece {
	//TODO une piece  1 ref qui peut ressembler a ça 89B13 et un nom
	protected String ref;
	private String nom;
	
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
	
	public abstract void setRef(String ref);
	public abstract double prix();
	public abstract int dureeGarantie();
	public abstract int dureeGarantieBase();
	public abstract int dureeFabrication();
	
	@Override
	public String toString() {
		return "Reference : " + getRef()
			+ "\nNom : " + getNom();
	}
	
	//TODO ensuite toutes les pièces vont avoir certiain foncton de base , donc il manque des fonction abstraite ...
	
}

