package evaluable;

public class TestEvaluable {
	public static void main(String[] args) {
		Etudiant[] notesPOO = new Etudiant[5];
		notesPOO[0] = new Etudiant("Pat Bole", 4.5);
		notesPOO[1] = new Etudiant("Agatha", 13.2);
		notesPOO[2] = new Etudiant("Wesh Gros", 19.5);
		notesPOO[3] = new Etudiant("Micheline", 15.45);
		notesPOO[4] = new Etudiant("Melinafouillendromaque", 2.5);

		
		Statistiques<Etudiant> stats = new Statistiques<Etudiant>(notesPOO);
		stats.afficherValeurs();
		System.out.println("Min : " + stats.min().valeur());
		System.out.println("Max : " + stats.max().valeur());
		System.out.println("Moyenne : " + stats.moyenne());
		System.out.println("Variance : " + stats.variance());
	}
}
