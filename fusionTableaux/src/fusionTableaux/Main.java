package fusionTableaux;

public class Main {

	public static void main(String[] args) {
		int tab1[] = {1, 2, 2, 3, 5, 5, 6, 8};
		int tab2[] = {1, 1, 4, 5, 5, 8, 8, 8, 8, 8};
		TableauEntiersTrie tabT = new TableauEntiersTrie(tab1, tab2);
		
		System.out.println("Affichage tableau max 4 colonnes");
		tabT.affichageLargeur(4);
		System.out.println();
		System.out.println("Affichage tableau max 5 lignes");
		tabT.affichageHauteur(5);
		
		System.out.println("Valeur maj = " + tabT.valMaj());
		System.out.println("Nb valeur maj = " + tabT.nbValMaj());
		
		tabT.elimineDupliques();
		System.out.print("Tableau sans valeurs mutliples : ");
		tabT.affichage();
	}
}
