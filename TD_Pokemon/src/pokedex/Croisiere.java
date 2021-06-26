package Pokedex;

public class Croisiere extends Aquatique{
	
	public Croisiere(String nom, double poids, int nbNageoires) {
		super(nom, poids, nbNageoires);
		setVitesse();
	}
	
	@Override
	public void setVitesse() {	
		super.vitesse = (getPoids() / 25 * getNbNageoires()) / 2;
	}

	@Override
	public void yo() {
		System.out.println("yo");
	}

}
