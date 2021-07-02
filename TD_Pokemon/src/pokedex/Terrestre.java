package pokedex;

import java.util.Locale;

public abstract class Terrestre extends Pokemon{
	private int nbPattes;
	private double taille;
	
	public Terrestre(String nom, double poids, int nbPattes, double taille) {
		super(nom, poids);
		setNbPattes(nbPattes);
		setTaille(taille);
		setVitesse();
	}
	
	public int getNbPattes() {
		return nbPattes;
	}
	public void setNbPattes(int nbPattes) {
		this.nbPattes = (nbPattes<=0 ? 4 : nbPattes);
	}
	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = (taille<=0 ? 1 : taille);
	}
	
	@Override
	public void setVitesse() {
		vitesse = getNbPattes() * 3 * getTaille();
	}
	
	@Override
	public String toString() {
		return "Je suis le pokemon " + getNom() + ", je pèse " + getPoids() + "kg, ma vitesse est de " + String.format(Locale.US, "%.2f", getVitesse()) + "km/h, j'ai " + getNbPattes() + " patte" + (getNbPattes()>1? "s" : "") + ", je mesure " + getTaille() + "m";
		
	}
	

}
