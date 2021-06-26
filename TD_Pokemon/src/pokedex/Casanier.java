package Pokedex;

public class Casanier extends Terrestre{
	private int nbHeuresTele;

	public Casanier(String nom, double poids, int nbPattes, double taille, int nbHeuresTele) {
		super(nom, poids, nbPattes, taille);
		setNbHeuresTele(nbHeuresTele);
	}

	public int getNbHeuresTele() {
		return nbHeuresTele;
	}

	public void setNbHeuresTele(int nbHeuresTele) {
		this.nbHeuresTele = (nbHeuresTele<0 ? -nbHeuresTele : nbHeuresTele);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", et je regarde la télévision " + getNbHeuresTele() + " heure" + (getNbHeuresTele()>1? "s" : "") + " par jour";
	}
	
	@Override
	public void yo() {
		System.out.println("yo");
	}
}
