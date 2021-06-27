package uga;

/**
 * Classe qui s'assure que
 *  - age est entre 0 et 6(le met à 3 sinon)
 *  - dents est entre 0 et 28 (le met à 12 sinon)
 */
public class Bebe extends Humain {
    // TODO Question 11: Ecrire l'accesseur de dents
    // TODO Question 11: Ecrire le modificateur de dents
    // TODO Question 11: Déclarer un attribut dents de type int.
	private int dents;
	
	public int getDents() {
		return dents;
	}
	
	public void setDents(int dents) {
		this.dents = (dents<=28 && dents>=0? dents : 12);
	}
	
    // TODO Question 13: Écrire le constructeur de Bebe avec 2 paramètres (cf description de l'énoncé
	public Bebe(int age, int dents) {
		super(age);
		setDents(dents);
	}
	
    // TODO Question 13: Écrire le constructeur par défaut de Bebe (cf description de l'énoncé.)
    // 
	public Bebe() {
		this(3, 12);
	}
	
    // TODO Question 12: Redéfinir le modificateur de age
    // 
	@Override
	public void setAge(int age) {
		super.age = age>=0 && age<=6 ? age : 3;
	}
	
    // TODO Question 16: Redéfinir la méthode toString
    //
	@Override
	public String toString() {
		return "Ce bébé a " + getAge() + (getAge()>1 ? " ans" : " an") + ", et possède " + getDents() + (getDents()>1? " dents" : " dent");
	}
}
