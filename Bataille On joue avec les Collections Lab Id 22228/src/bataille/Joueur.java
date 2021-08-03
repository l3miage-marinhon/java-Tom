package bataille;
//quand la classe est terminer faire le TestJoueur
//a la bataille, on utilise un deck de carte 

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

public class Joueur{
	//TODO donner les attribues d'un joueur
	private String nom;	
	private ArrayDeque<Carte> deck;

	
	//TODO faire le conctructeur
	public Joueur(String nom) {
		this.nom = nom;
		deck = new ArrayDeque<Carte>();
	}
	
	public String getNom() {
		return nom;
	}
	
	//TODO faire la méthode remporte qui lorsqu'un joueur gagne une partie, il ajoute
	//une carte son deck
	//public void remporte(Carte carte)
	public void remporte(Carte carte) {
		deck.addLast(carte);
	}
	
	//ou bien un tas de carte
	//public void remporte(Collection<Carte> cartes)
	public void remporte(Collection<Carte> listeCartes) {
		for(Carte carte : listeCartes) {
			this.remporte(carte);
		}
	}
	
	//TODO faire la méthode joue qui permet de jouer la carte la plus hautes dans le deque
	//public Carte joue() 
	public Carte joue() {
		return deck.pollFirst();
	}
	
	//TODO faire le nb carte
	//public int nbCartes()
	public int nbCartes() {
		return deck.size();
	}
	
	public void printDeck() {
		System.out.println("Cartes du deck de " + getNom());
		for(Carte c : deck) {
			System.out.println(c);
		}
	}
	
}
