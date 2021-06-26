package Pokedex;

public class Mer extends Aquatique{
	
	public Mer(String nom, double poids, int nbNageoires) {
		super(nom, poids, nbNageoires);
		setVitesse();
	}

	@Override
	public void setVitesse() {
		super.vitesse = getPoids() / 25 * getNbNageoires();
	}
	
	@Override
	public void yo() {
		System.out.println("yo");
	}

}
