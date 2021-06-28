package menagerie;

import java.util.ArrayList;
import java.util.Scanner;

public class Animalerie {
	
	private ArrayList<Animal> chenil;
	
	public Animalerie(int n) {
		setChenil(n);
	}
	
	public Animalerie() {
		chenil = new ArrayList<Animal>();
    	chenil.add(new Chat("Kira"));
    	chenil.add(new Chien("Médor"));
    	chenil.add( new Poisson("Bubulle"));
	}
	
	public void setChenil(int n) {
		chenil = new ArrayList<Animal>(n);
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
				chenil.add(new Chien(nom));
			}else if(choix == 2) {
				chenil.add(new Chat(nom));
			}else {
				chenil.add(new Poisson(nom));
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
        
        Scanner sc = new Scanner(System.in);
        int nb = 0;
        while(!(nb>0 && nb<10)) {
        	System.out.print("Combien d'animaux souhaitez vous enregistrer (entre 1 et 9) : ");
        	nb = sc.nextInt();
        }
       
        Animalerie an2 = new Animalerie(nb);
        System.out.println(an2.getChenil());
        sc.close();
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
