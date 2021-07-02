package pokedex;

public abstract class Pokemon {
	private String nom;
	private double poids;
	protected double vitesse;
	
	public Pokemon(String nom, double poids) {
		setNom(nom);
		setPoids(poids);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = (poids<=0 ? 10 : poids);
	}
	
	public double getVitesse() {
		return vitesse;
	}
	
	public abstract void setVitesse();
	
	public abstract void yo();

}
