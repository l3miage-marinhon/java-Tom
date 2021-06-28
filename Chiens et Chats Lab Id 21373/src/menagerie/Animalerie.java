package menagerie;

import java.util.Scanner;

public class Animalerie {
	
	private Animal chenil[];
	
	public Animalerie(int n) {
		setChenil(n);
	}
	
	public Animalerie() {
		chenil = new Animal[3];
    	chenil[0] = new Chat("Kira");
    	chenil[1] = new Chien("Beethoven");
    	chenil[2] = new Poisson("Saumon");
	}
	
	public void setChenil(int n) {
		chenil = new Animal[n];
		Scanner sc = new Scanner(System.in);
		int choix;
		String nom;
		for (int i=0; i<n; i++) {
			choix = 0;
			System.out.println("Type de l'animal : | Chien : 1 | Chat : 2 | Poisson : 3 |");
			while(choix != 1 && choix != 2 && choix != 3) {
				System.out.print("Votre choix : ");
				choix = sc.nextInt();
				sc.nextLine();
			}
			System.out.print("Nom de l'animal : ");
			nom = sc.nextLine();
			if(choix == 1) {
				chenil[i] = new Chien(nom);
			}else if(choix == 2) {
				chenil[i] = new Chat(nom);
			}else {
				chenil[i] = new Poisson(nom);
			}
		}
		sc.close();
	}

    public String getChenil() {
    // TODO Créez un tableau de 3 animaux et renvoyez une chaîne de carractère qui contient ce qui est demandé dans l'énoncé. Contrainte: utilier un foreach...    	
    	int n=1;
    	String s = "";
    	for(Animal a : chenil) {
    		s += "Animal " + n + " : " + a.toString();
    		n++;
    	}
    	return s;
    }
      

    public static void main(String[] args) {
        Animalerie an = new Animalerie();
        System.out.println(an.getChenil());
       
        Animalerie an2 = new Animalerie(4);
        System.out.println(an2.getChenil());
    }
    
    /*
     * J'ai redéfini la méthode toString() dans Animal plutot que dans Mammifere, car un poisson 
     * n'étant pas un Mammifere, il aurait fallu redéfinir toString() dans Poisson en plus de Mammifere
     * 
     * J'ai écris le constructeur par défaut de Animalerie pour qu'il fasse la création du tableau 
     * de 3 animaux du TODO de getChenil(), ainsi getChenil() ne gère plus que l'affichage du chenil
     * J'ai aussi écris un constructeur avec un paramètre entier pour que l'utilisateur puisse créer
     * un chenil personnalisé, avec le setChenil(int n) qui va avec (pour voir le fonctionnement de 
     * la saisie au clavier) 
     * Avant ces modifs, le code de Animalerie() se trouvait au début de getChenil(), et les 2 constructeurs 
     * et setChenil(int n) n'existaient pas
     */
}
