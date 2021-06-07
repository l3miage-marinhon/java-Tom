package number;

/**
 * Classe représentant un triplet d'entiers.
 * suivez les instructions des commentaires
 */
public class Triplet {
    // TODO Déclarer un attribut a de type int
	int a;
    
    // TODO Déclarer un attribut b de type int
    int b;
    // TODO Déclarer un attribut c de type int
    int c;
    
    public Triplet(int a, int b, int c) {
    // TODO Complétez le constructeur qui affecte les trois attributs
    this.a=a;
    this.b=b;
    this.c=c;
    }

    public int sum() {
        return (this.a+this.b+this.c);
    // TODO Complétez la méthode sum() qui retourne la somme des trois attributs
    }

    public void affiche() {
    // TODO décommenter la ligne suivante dès que vous aurez déclaré les 3 attributs a, b et c
      System.out.print("a: " + a + ", b: " + b + ", c: " + c);
    }
}
