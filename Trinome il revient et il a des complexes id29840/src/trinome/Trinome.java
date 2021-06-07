package trinome;

/**
 *  Classe qui modélise le trinôme du second degré sous la forme
 *                          ax^2 + bx + c = 0
 *
 * Pour la résolution de ce trinôme, on calcule delta:
 * delta = b^2 - 4ac
 *
 * Si delta = 0, alors le trinôme a une seule solution: x=(-b)/(2a)
 * Si delta > 0, alors le trinôme a deux solutions réelles
 * Si delta < 0, alors le trinôme a deux solutions complexes.
 * Pour comprendre le détail de la résolution du trinôme, voir Wikipedia ou:
 *  http://www.bibmath.net/dico/index.php?action=affiche&quoi=./t/trinome.html
 *
 *  Attention, si a = 0, alors on a une équation de deegré 1: bx + c = 0
 *  	Cette équation admet une seule solution: x = -c/b
 *  	Sauf si b = 0, alors l'équation c = 0 admet
 *  	- soit zéro solution si c != 0
 *  	- soit une infinité de solutions si c = 0
 */
class Trinome {

    // facteur de x^2
    double a;

    // facteur de x^1
    double b;

    // facteur de x^0
    double c;

    // delta = b^2-4ac (tout le temps !)
    double delta;

    // Constructeur
    Trinome(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        calculDelta();
    }

    // Calcule delta en fonction de a, b et c.
    void calculDelta() {
    	delta=b*b-4*a*c;    
    	}

    // Modifie l'attribut a
    void setA(double a) {
    	this.a=a;
    }

    // Modifie l'attribut b
    void setB(double b) {
    	this.b=b;
    }

    // Modifie l'attribut c
    void setC(double c) {
    	this.c=c;
    }

    /*
	 * Renvoie
	 * - 0 si l'équation ax^2+bx+c n'a pas de racine,
	 * - 1 si l'équation a 1 racine,
	 * - 2 si l'équation a 2 racines 
	 * - Integer.MAX_VALUE si l'équation a une infinité de racines.
	 */
    int nbRacine() {
    	if(a==0) {
    		if(b==0 && c==0) {
    			return Integer.MAX_VALUE;
    		}
    		else if(b==0 && c!=0) {
    			return 0;
    		}
    	}else {
    		if(delta<0){
    			return 2;
    		}
    		else if(delta==0){
    			return 1;
    		}
    		else {
    			return 2;
    		}
    	}
		return 0;
    }

    /*
	 * Renvoie
	 * - null si l'équation a zéro ou une infinité de racines 
	 * 		(et écrit sur la console un petit message pour le signaler)
	 * - [(-c/b) + i 0.0] ou [(-b)/(2a) + i 0.0] si l'équation a une seule racine
	 * - [(-b-sqrt(delta)/2a) + i 0.0] si l'équation a 2 racines réelles
	 * - [-b/2a + i (-sqrt(delta)/2a)] si l'équation a 2 racines complexes
	 * 
	 */
    Complexe getRacine1() {
    		switch(nbRacine()) {
    			ca
    		}
    // TODO A compléter
    }

    /*
	 * Renvoie
	 * - null si l'équation a zéro ou une infinité de racines 
	 * 		(et écrit sur la console un petit message pour le signaler)
	 * - [(-c/b) + i 0.0] ou [(-b)/(2a) + i 0.0] si l'équation a une seule racine
	 * - [(-b+sqrt(delta)/2a) + i 0.0] si l'équation a 2 racines réelles
	 * - [-b/2a + i (sqrt(delta)/2a)] si l'équation a 2 racines complexes
	 * 
	 */
    Complexe getRacine2() {
        return null;
    // TODO A compléter
    }

    /*
	 * Renvoie une chaîne de caractères désignant le trinôme.
	 * Cette chaîne peut contenir 1.0x^2, 1.0x, mais ne peut pas contenir 0.0x^2 ni 0.0x, ni +-3.5x.
	 * Cette chaîne ne contient pas 0.0 lorsque c=0.0, à l'exception de l'équation 0.0=0.0 qui doit être écrite telle quelle.
	 * Cette chaîne ne contient pas d'espace.
	 * Exemples de chaînes: 
	 * 	"1.125x^2+5.0x+2.0=0.0"
	 *  "5.0=0.0" 
	 *  "1.0x^2+2.0x+1.0=0.0"
	 *  "5.0x-3.5=0"
	 *  2.5x^2+3.0x-5.0=0.0"
	 *  3.0x^2=0.0.
	 */
    String getString() {
        return null;
    // TODO A compléter
    }
}
