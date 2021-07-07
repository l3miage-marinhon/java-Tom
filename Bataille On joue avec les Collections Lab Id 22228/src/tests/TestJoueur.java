package tests;

import java.util.ArrayList;

import bataille.Carte;
import bataille.Couleur;
import bataille.Joueur;
import bataille.Valeur;

public class TestJoueur {

	public static void main(String[] args) {
		ArrayList<Carte> desCartes = new ArrayList<Carte>();
		desCartes.add(new Carte(Couleur.TREFLE, Valeur.SEPT));
		desCartes.add(new Carte(Couleur.TREFLE, Valeur.HUIT));
		desCartes.add(new Carte(Couleur.TREFLE, Valeur.NEUF));
		desCartes.add(new Carte(Couleur.TREFLE, Valeur.DIX));
		
		
		Joueur joueur = new Joueur("Pocker Face");
		joueur.printTas();
		
		joueur.remporte(new Carte(Couleur.COEUR, Valeur.DAME));
		joueur.remporte(new Carte(Couleur.PIQUE, Valeur.VALET));
		joueur.remporte(desCartes);
		joueur.printTas();
		System.out.println("\nJoueur joue : " + joueur.joue());
		System.out.println("Joueur joue : " + joueur.joue());
		System.out.println("Joueur joue : " + joueur.joue());
		joueur.printTas();
		System.out.println("Joueur remporte as de carreau");
		joueur.remporte(new Carte(Couleur.CARREAU, Valeur.AS));
		System.out.println("Joueur remporte 9 de carreau");
		joueur.remporte(new Carte(Couleur.CARREAU, Valeur.NEUF));
		joueur.printTas();
	}

}