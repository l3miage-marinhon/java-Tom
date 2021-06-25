package classes;

public class Voiture extends Vehicule{
	private int nbPortiere;
	
	public Voiture(double vitesse, int nbPassager, int nbPortiere) {
		super(vitesse, nbPassager);
		setNbPortiere(nbPortiere);
	}

	public int getNbPortiere() {
		return nbPortiere;
	}

	public void setNbPortiere(int nbPortiere) {
		this.nbPortiere = (nbPortiere >= 1 && nbPortiere <= 5 ? nbPortiere : 1);
	}
	
	@Override
	public String toString() {
		return "Voiture: [vitesse: " + getVitesse() + " km/h, passagers: " + getNbPassager() + ", portières: " + getNbPortiere() + "] ";
	}
	
}
