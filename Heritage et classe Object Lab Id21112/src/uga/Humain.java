package uga;

/**
 * Classe qui s'assure que son attribut age est toujours >=0
 */
public class Humain {
    // TODO Question 1: Déclation d'un attribut age de type int
    //
	protected int age;	//protected pour pouvoir y acceder depuis la classe Bebe (Override de setAge())
	
    // TODO Question 4: Écrire le constructeur de Humain avec un paramètre (cf description de l'énoncé.)
    // 
	public Humain(int age) {
		setAge(age);
	}
	
    // TODO Question 6: Écrire le constructeur de Humain (cf description de l'énoncé.)
    // 
	public Humain() {
		this(18);		//je mets l'age à 18 ans par défaut
	}
	
    // TODO Question 2: Écrire l'accesseur pour l'attribut age
    // 
	public int getAge() {
		return age;
	}
	
    // TODO Question 3: Ecrire le modificateur de age
    // 
	public void setAge(int age) {
		this.age = age>=0 ? age : 18;
	}
	
    // TODO Question 9 Redéfinir la méthode toString
    //
	@Override
	public String toString() {
		return "Cet humain a " + getAge() + (getAge()>1 ? " ans" : " an");
	}
}
