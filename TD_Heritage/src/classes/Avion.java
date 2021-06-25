package classes;

public class Avion extends Vehicule{
	private int nbMoteur;

	public Avion(double v, int nbP, int nbM) {
		super(v, nbP);
		setNbMoteur(nbM);
	}
	
	public int getNbMoteur() {
		return nbMoteur;
	}

	public void setNbMoteur(int nbMoteur) {
		this.nbMoteur = (nbMoteur >= 1 && nbMoteur <= 8 ? nbMoteur : 1);
	}
	
	@Override
	public String toString() {
		return "Avion: [vitesse: " + getVitesse() + " km/h, passagers: " + getNbPassager() + ", moteurs: " + getNbMoteur() + "] ";
	}
	
}
