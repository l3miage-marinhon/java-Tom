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
    	Point [] tab_pointP1= {new Point(2,0),new Point(2,2),new Point(1,3)};
    	Point [] tab_pointP2={new Point(0,0),new Point(0,2),new Point(1,3)};
    	Polygone p1= new Polygone(tab_pointP1);
    	Polygone p2= new Polygone(tab_pointP2);
    	
    	p1.afficher();
    	p1.afficher(true);
    	p2.afficher();
    	p2.afficher(true);
    	
    	
    	p1.translation(2,3,4);
    	p2.translation(2,3,4);
    	
    	p1.afficher();
    	p2.afficher();
      /* TODO 3.01 Écrivez une méthode principal main qui
	1. crée deux polygones se partageant un côté,
	les affiche,
	leur applique à chacun la même translation 
	et les affiche.

	Observez et répondez au QCM qui suit ce "lab".*/
    }
}
