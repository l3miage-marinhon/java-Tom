package tests;

import bataille.Jeu;

public class TestJeu {

    public static void main(String[] args) {
        Jeu jeu = new Jeu("Pocker Face", "Eddy Moitou");
        jeu.printJeuDeCarte();
        
        System.out.println("--- On mélange les cartes ---");
            jeu.melangerCartes();
            jeu.printJeuDeCarte();
            
        System.out.println("--- On distribue les cartes ---");
           jeu.distribuer();
           jeu.printTasJoueur(1);
           jeu.printTasJoueur(2);
           
    }

}
