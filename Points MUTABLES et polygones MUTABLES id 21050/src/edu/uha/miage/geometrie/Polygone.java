/*
  @author : Yvan Maillot (yvan.maillot@uha.fr)

Écrire une classe (mutable) Polygone avec:

    1. un attribut de type tableau de sommets,
    2. la possibilité de le construire à partir d’une liste d’au moins trois sommets,
    3. une méthode qui retourne le nombre de sommets de ce polygone,
    4. un getter "public Point getSommet(int i)" qui retourne le ième sommet,
    5. un setter "public void setSommet(Point p, int i) qui affecte le ième à p,
    6. une méthode pour lui appliquer une translation,
    7. une méthode pour lui appliquer une rotation par rapport à l’origine,
    8. des méthodes pour l’afficher sur la sortie standard.
 */
package edu.uha.miage.geometrie;

/**
 * @author yvan
 */
public class Polygone {
	private Point [] sommets;
	
	
	public Polygone(Point[] tab){
		this.sommets= new Point [tab.length];
		for(int i=0;i<sommets.length;i++) {
			sommets[i]=tab[i] ;
		}
	}
	public int nbSommets() {
		return sommets.length;
	}
	
	public Point getSommet(int i) {
		return sommets[i];
	}
	public void setPoint(Point p,int i) {
		sommets[i]=p;
	}
	public void translation(int i,double dx,double dy) {
		sommets[i].translation(dx, dy);
	}
	public void rotation(int i, double dtheta) {
		sommets[i].rotation(dtheta);
	}
	
    public void afficher(boolean polaire) {
    /* TODO 2.08. Écrire afficher(boolean polaire) qui affiche ce polygone sur la 
	sortie standard comme une liste de sommets, chacun d'eux au format
	"polaire"  ou "cartésien" selon l'état du paramètre
	*/	System.out.print("[");
    	for(Point point:sommets) {
    		point.afficher(polaire);
    	}
    	System.out.println("]");
    }

    public void afficher() {
    // TODO 2.09. Par défaut, l'affichage se fait au format "cartésien"
    	System.out.print("[");
    	for(Point point:sommets) {
    			point.afficher();
    	}
    	System.out.println("]");
    }
    
    
    
    
    // TODO 2.01. Déclarer sommets, un tableau de sommets (edu.uha.miage.geometrie.Point)
    // 
    /* TODO 2.02. Écrire un constructeur pour créer un polygone d'au moins 3 sommets 
	et autant qu'on veut
	*/
    // 
    // TODO 2.03. Écrire nbSommets() qui retourne le nombre de sommets de ce polygone
    // 
    // TODO 2.04. Écrire getSommet(int i) qui retourne le ième sommet de ce polygone
    // 
    // TODO 2.05. Écrire setSommet(int i, Point p) qui affecte le ième sommet de ce polygone
    // 
    // TODO 2.06. Écrire translation(double dx, double dy) qui translate ce polygone
    // 
    // TODO 2.07. Écrire rotation(double dtheta) qui applique une rotation à ce polygone
    // 
}
