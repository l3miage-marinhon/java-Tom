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

    // TODO 2.01. Déclarer sommets, un tableau de sommets (edu.uha.miage.geometrie.Point)
	private Point[] sommets;
    // 
    /* TODO 2.02. Écrire un constructeur pour créer un polygone d'au moins 3 sommets 
	et autant qu'on veut
	*/
	public Polygone(Point[] sommets) {
		if (sommets.length >= 3) {
			this.sommets = sommets;
		}else {
			System.out.println("Vous ne pouvez pas créer un polygone avec moins de 3 sommets"); // a refaire plus tard avec des exceptions
		}
	}
    // 
    // TODO 2.03. Écrire nbSommets() qui retourne le nombre de sommets de ce polygone
	public int nbSommets() {
		return sommets.length;
	}
    // 
    // TODO 2.04. Écrire getSommet(int i) qui retourne le ième sommet de ce polygone
	public Point getSommet(int i) {
		return (i>=0 && i<sommets.length) ? sommets[i] : null;
	}
    // 
    // TODO 2.05. Écrire setSommet(int i, Point p) qui affecte le ième sommet de ce polygone
	public void setSommet(int i, Point p) {
		if (i>=0 && i<sommets.length) {
			sommets[i] = p;
		}else {
			System.out.println("Le sommet d'indice" + i + "n'existe pas"); //idem constructeur, exception plus tard
		}
	}
    // 
    // TODO 2.06. Écrire translation(double dx, double dy) qui translate ce polygone
	public void translation(double dx, double dy) {
		for(Point s : sommets) {
			s.translation(dx,  dy);
		}
	}
    // 
    // TODO 2.07. Écrire rotation(double dtheta) qui applique une rotation à ce polygone
	public void rotation(double dtheta) {
		for(Point s : sommets) {
			s.rotation(dtheta);
		}
	}
    // 
	/* TODO 2.08. Écrire afficher(boolean polaire) qui affiche ce polygone sur la 
	sortie standard comme une liste de sommets, chacun d'eux au format
	"polaire"  ou "cartésien" selon l'état du paramètre
    }
    // TODO 2.09. Par défaut, l'affichage se fait au format "cartésien"
    }*/
	public void affichage(boolean polaire) {
		for(Point s : sommets) {
			s.afficher(polaire);
		}
	}
	
	public void affichage() {
		for(Point s : sommets) {
			s.afficher();
		}
	}
}
