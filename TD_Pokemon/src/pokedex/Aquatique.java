package Pokedex;

import java.util.Locale;

public abstract class Aquatique extends Pokemon{
	private int nbNageoires;
	
	public Aquatique(String nom, double poids, int nbNageoires) {
		super(nom, poids);
		setNbNageoires(nbNageoires);
	}

	public int getNbNageoires() {
		return nbNageoires;
	}

	public void setNbNageoires(int nbNageoires) {
		this.nbNageoires = (nbNageoires<=0 ? 2 : nbNageoires);
	}
		
	@Override
	public String toString() {
		return "Je suis le pokemon " + getNom() + ", je pèse " + getPoids() + "kg, ma vitesse est de " + String.format(Locale.US, "%.2f", getVitesse()) +  "km/h, et je possède " + getNbNageoires() + " nageoire" + (getNbNageoires()>1?"s":"");
	}
}
