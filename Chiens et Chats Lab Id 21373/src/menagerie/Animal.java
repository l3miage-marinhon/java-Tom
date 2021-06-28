package menagerie;

public abstract class Animal {
    // TODO Déclarer l'attribut nom
	private String nom;

    // TODO Question 1: Constructeur
    // 
	public Animal(String nom) {
		setNom(nom);
	}
	
    // TODO Question 1: Accesseur
    // 
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
    // TODO Question 1: Déclarer la méthode cri.
    // 
	public abstract String cri();
	
	@Override
	public String toString() {
		return "Je m'appelle " + getNom() + ", et je peux faire " + cri() + "\n";
	}
}
