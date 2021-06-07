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
	}

}