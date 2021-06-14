/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package edu.uha.miage;

import edu.uha.miage.geometrie.Point;
import edu.uha.miage.geometrie.Polygone;

/**
 * Classe autonome pour tester et observer le comportement des classes Point et
 * Polygone.
 *
 * @author yvan
 */
public class Main {

    /**
     * Programme qui
     *   1. crée deux polygones qui se partagent un côté et les affiche,
     *   2. leur applique à chacun la même translation et les affiche.
     *
     * Par exemple
     *      (1,3)
     *     .     .
     *    .       .
     * (0,2)----(2,2)
     *   |        |
     *   |        |
     *   |        |
     * (0,0)----(2,0)
     *
     * Terminez cet exercice pour répondre au questionnaire suivant :
     * "Observations à propos des Points MUTABLES et Polygones MUTABLES.
     */
    public static void main(String[] args) {
    	
    	System.out.println("point 1 ");
    	Point p1 = new Point(2.35, 5.46, false);
    	p1.afficher();
    	p1.afficher(true);
    	
    	p1.translation(-2, -8);
    	System.out.println("translation p1 (-2, -8) : ");
    	p1.afficher();
    	p1.afficher(true);
    	
    	System.out.println("point 2 ");
    	Point p2 = new Point(4.2, -4.23, true);
    	p2.afficher();
    	p2.afficher(true);
    	
    	p2.rotation(4.53);
    	System.out.println("rotation p2 (4,53) : ");
    	p2.afficher();
    	p2.afficher(true);
    	
    	System.out.println("point 3 ");
    	Point p3 = new Point(-8.56, 7.54, true);
    	p3.afficher();
    	p3.afficher(true);
      /* TODO 3.01 Écrivez une méthode principal main qui
	1. crée deux polygones se partageant un côté,
	les affiche,
	leur applique à chacun la même translation 
	et les affiche.

	Observez et répondez au QCM qui suit ce "lab".*/
    	
    	Point a = new Point();
    	Point b = new Point(1, 3);
    	Point c = new Point(-5, 1);
    	Point d = new Point(2, -3);
    	Point e = new Point(4, 4);
    	Point f = new Point(-1, -9);
    	Point z = new Point(-10, -10);
    	
    	Point[] sA = {a, c, f, d};
    	Point[] sB = {a, b, e, d};
    	Point[] sC = {a, b, e};
    	Point[] sD = {c, d, f, z};
    	
    	Polygone polyA = new Polygone(sA);
    	Polygone polyB = new Polygone(sB);
    	Polygone polyC = new Polygone(sC);
    	Polygone polyD = new Polygone(sD);
    	
    	System.out.println("Polygone A cartesien : ");
    	polyA.affichage();
    	System.out.println("Polygone B cartesien : ");
    	polyB.affichage();
    	
    	polyA.translation(3, -1);
    	polyB.translation(3, -1);
    	
    	System.out.println("Polygone A translaté (3, -1) : ");
    	polyA.affichage();
    	System.out.println("Polygone B translaté (3, -1) : ");
    	polyB.affichage();
    	
    	System.out.println();
    	
    	System.out.println("Polygone C cartesien : ");
    	polyC.affichage();
    	System.out.println("Polygone C polaire : ");
    	polyC.affichage(true);
    	System.out.println("Polygone D cartesien : ");
    	polyD.affichage(false);
    	System.out.println("Polygone D polaire : ");
    	polyD.affichage(true);
    	
    	System.out.println();

    	polyC.translation(-2, 1);
    	System.out.println("Polygone C cartesien translation (-2,1): ");
    	polyC.affichage();
    	polyC.rotation(Math.PI*2);
    	System.out.println("Polygone C cartesien rotation (2*PI): ");
    	polyC.affichage();
    	
    	polyD.rotation(3*Math.PI/4);
    	System.out.println("Polygone D polaire rotation (3*PI/4): ");
    	polyD.affichage(true);
    	System.out.println("Polygone D cartesien rotation (3*PI/4): ");
    	polyD.affichage();
    	polyD.translation(0, 0);
    	System.out.println("Polygone D cartesien translation (0,0): ");
    	polyD.affichage();
    	
    	System.out.println("Le polygone C possède "+ polyC.nbSommets() + " sommets"); 
    	int i = 1;
    	System.out.println("Le sommet d'indice " + i + " du polygone D est " + polyD.getSommet(i) + " de coordonnées cartésiennes ");
    	polyD.getSommet(i).afficher();
    	polyD.setSommet(i, new Point());
    	System.out.println("Le nouveau sommet d'indice " + i + " du polygone D est " + polyD.getSommet(i) + " de coordonnées cartésiennes ");
    	polyD.getSommet(i).afficher();
    	
    }
}
