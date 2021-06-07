package uga;

/**
 * Classe qui s'assure que son attribut age est toujours >=0
 */
public class Humain {
    // TODO Question 1: Déclation d'un attribut age de type int
    //
	private int age;
	
	public Humain(int age) {
		setAge(age);
		System.out.println(this);
	}
	
	public  Humain() {
		this(50);
	}
    // TODO Question 4: Écrire le constructeur de Humain avec un paramètre (cf description de l'énoncé.)
    // 
    // TODO Question 6: Écrire le constructeur de Humain (cf description de l'énoncé.)
    // 
    // TODO Question 2: Écrire l'accesseur pour l'attribut age
    // 
	public int getAge() {
		return age;
	}
    // TODO Question 3: Ecrire le modificateur de age
    // 
	public void setAge(int age) {
		if(age>=0) {
			this.age=age;
		}
	}
    // TODO Question 9 Redéfinir la méthode toString
    //
	public String toString() {
		return ("Humain (age : "+getAge()+")");
	}
}
