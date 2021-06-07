package exo3;

public class TESTdevinette {

	public static void main(String[] args) {
		System.out.println("Une première devinette");
		devinette nombreMystère = new devinette(5, 8);
		nombreMystère.cheat(7);
		System.out.println("Borne inférieure du domaine : " + nombreMystère.getMin());
		System.out.println("Borne supérieure du domaine : " + nombreMystère.getMax());
		System.out.println("Comparaison du nombre à trouver avec 6 : " + nombreMystère.essayer(6).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 7 : " + nombreMystère.essayer(7).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 8 : " + nombreMystère.essayer(8).getMessage());
		 
		System.out.println("Une deuxième devinette (attention il y a un piège...)");
		nombreMystère.cheat(10);
		System.out.println("Comparaison du nombre à trouver avec 5 : " + nombreMystère.essayer(5).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 6 : " + nombreMystère.essayer(6).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 7 : " + nombreMystère.essayer(7).getMessage());
		 
		System.out.println("Une troisième devinette (attention il y a des pièges...)");
		devinette nombreMystère2 = new devinette(7, -1);
		nombreMystère2.cheat(6);
		System.out.println("Borne inférieure du domaine : " + nombreMystère2.getMin());
		System.out.println("Borne supérieure du domaine : " + nombreMystère2.getMax());
		System.out.println("Comparaison du nombre à trouver avec -1 : " + nombreMystère2.essayer(-1).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 10 : " + nombreMystère2.essayer(10).getMessage());
		System.out.println("Comparaison du nombre à trouver avec 6 : " + nombreMystère2.essayer(6).getMessage());
		 
		System.out.println("Une devinette aléatoire");
		nombreMystère.auHasar();
		System.out.println("Borne inférieure du domaine : " + nombreMystère.getMin());
		System.out.println("Borne supérieure du domaine : " + nombreMystère.getMax());
		for (int i = nombreMystère.getMin(); i <= nombreMystère.getMax(); i++) {
		    System.out.println("Test avec " + i + " : " + nombreMystère.essayer(i).getMessage());
		}
		
		System.out.println("Deviner un nombre connu");
		nombreMystère.cheat(7);
		nombreMystère.partieContreHumain();
		 
		System.out.println("Deviner un nombre tiré au hasard");
		nombreMystère.auHasar();
		nombreMystère.partieContreHumain();

	}

}
