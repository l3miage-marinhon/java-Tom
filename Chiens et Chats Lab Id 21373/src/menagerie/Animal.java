package menagerie;

public abstract class Animal {
    // TODO Déclarer l'attribut nom

    // TODO Question 1: Constructeur
    // 
    // TODO Question 1: Accesseur
    // 
    // TODO Question 1: Déclarer la méthode cri.
    // 
	protected String nom;

	public Animal(String nom) {
		this.nom=nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public abstract String cri();
}
