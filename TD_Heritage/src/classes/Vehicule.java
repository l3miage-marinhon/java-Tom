package classes;

public class Vehicule {
	private double vitesse;
	private int nbPassager;
	
	public Vehicule(double vitesse, int nbPassager) {
		setVitesse(vitesse);
		setNbPassager(nbPassager);
	}

	public void setVitesse(double v){
		vitesse = (v >= 0 ? v : -v);
	}
	
	public void setNbPassager(int n) {
		nbPassager = (n > 0 ? n : -n);
	}
	
	public double getVitesse() {
		return vitesse;
	}

	public int getNbPassager() {
		return nbPassager;
	}
	
	@Override
	public String toString() {
		return "Vehicule: [vitesse: " + getVitesse() + " km/h, passagers: " + getNbPassager() + "] ";
	}
	
	
	
	
}
