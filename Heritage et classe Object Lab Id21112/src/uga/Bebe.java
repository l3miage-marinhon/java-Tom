package uga;

/**
 * Classe qui s'assure que
 *  - age est entre 0 et 6(le met à 3 sinon)
 *  - dents est entre 0 et 28 (le met à 12 sinon)
 */
public class Bebe extends Humain {
	private int dents;
	
	public Bebe(int age,int dents) {
		super(age);
		setDents(dents);
		System.out.println(this);
	}
	public Bebe() {
		this(3,12);
	}
	
    public int getDents() {
        return dents;
    // TODO Question 11: Ecrire l'accesseur de dents
    }

    protected void setDents(int dents) {
    // TODO Question 11: Ecrire le modificateur de dents
    	if(dents>=0 && dents<=28) {
    		this.dents=dents;
    	}else {
    		this.dents=12;
    	}
    }
    // TODO Question 11: Déclarer un attribut dents de type int.
    // 
    // TODO Question 13: Écrire le constructeur de Bebe avec 2 paramètres (cf description de l'énoncé.)
    // 
    // TODO Question 13: Écrire le constructeur par défaut de Bebe (cf description de l'énoncé.)
    // 
    // TODO Question 12: Redéfinir le modificateur de age
    //
    @Override
    public void setAge(int age) {
    	if(age>=0 && age<=6) {
    		super.setAge(age);
    	}else {
    		super.setAge(3);
    	}
    } 
    // TODO Question 16: Redéfinir la méthode toString
    // 
    public String toString() {
    	return ("Bebe (age : "+getAge()+", dents : "+getDents()+")");
    }
}
