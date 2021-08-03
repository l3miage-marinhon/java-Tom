package bataille;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

import javax.sound.midi.SysexMessage;

import exceptions.*;

//Quand la classe et terminer lancer le TestJeu
//un jeux consiste a avoir 2 joueur , un jeux de carte (32 carte)  remplie, et 2 deck courant sur le jeux pour les 2 joueur
public class Jeu{
	//TODO attribue de la classe 
	private Joueur joueur1;
	private Joueur joueur2;
	private ArrayList<Carte> jeuDeCartes;
	private Deque<Carte> tasJ1;
	private Deque<Carte> tasJ2;
	
	
	//TODO le constructeur, ici on prend les 2 noms des joueurs
	public Jeu(String n1, String n2) {
		joueur1 = new Joueur(n1);
		joueur2 = new Joueur(n2);
		tasJ1 = new ArrayDeque<Carte>();
		tasJ2 = new ArrayDeque<Carte>();
		this.initialiseJeuDeCartes();
	}
	
	public Joueur getJoueur1() {
		return joueur1;
	}
	
	public Joueur getJoueur2() {
		return joueur2;
	}
	
	//TODO faire la méthode void initialiseJeuDeCartes() qui vas creer le jeux de carte
	private void initialiseJeuDeCartes() {
		jeuDeCartes = new ArrayList<>();
		for(Couleur c : Couleur.values()) {
			for(Valeur v : Valeur.values()) {
				jeuDeCartes.add(new Carte(c, v));
			}
		}
	}

	//TODO faire la méthode void distribuer() qui distribue 1 a 1 les cartes entre les 2 joueurs
	public void distribuer() {
		this.melangerCartes();
		for(int i=0; i<jeuDeCartes.size(); i++) {
			(i%2==0? joueur1 : joueur2).remporte(jeuDeCartes.get(i));
		}
	}

	//TODO faire la méthode void melangerCartes() qui vas mellanger les cartes, ici il ya une propriete des collection qui vas te servir
	public void melangerCartes() {
		Collections.shuffle(jeuDeCartes);
	}
	
	
	//TODO faire la méthode void printJeuDeCarte() 
	public void printJeuDeCarte() {
		for(Carte c : jeuDeCartes) {
			System.out.println("Carte " + (jeuDeCartes.indexOf(c)+1) + " : " + c);
		}
	}
	
	//TODO faire la méthode Carte joueurJoue(int joueur) qui si c'est le joueur est 1 c'est le joueur 1 qui joue ,pareil pour 2
	public Carte joueurJoue(int joueur) throws UnknownPlayer{
		Carte carte;
		if(joueur==1) {
			carte = joueur1.joue();
			tasJ1.addFirst(carte);
		}else if(joueur==2) {
			carte = joueur2.joue();
			tasJ2.addFirst(carte);
		}else {
			throw new UnknownPlayer("Joueur inconnu");
		}
		return carte;
	}
	
	//TODO faire la méthode public Carte derniereCarteJouee(int joueur)
	public Carte derniereCarteJouee(int joueur) throws UnknownPlayer{		
		if(joueur!=1 && joueur!=2) {
			throw new UnknownPlayer("Joueur inconnu");
		}
		return (joueur==1 ? tasJ1 : tasJ2).peek();
	}
	
	//TODO faire la méthode void printTasJoueur(int joueur)
	public void printTasJoueur(int joueur) throws UnknownPlayer{
		
		if(joueur==1 || joueur==2) {
			System.out.println("\nTas du joueur " + (joueur==1 ? joueur1 : joueur2).getNom());
			for(Carte c : (joueur == 1 ? tasJ1 : tasJ2)) {
				System.out.println(c);
			}
		}else {
			throw new UnknownPlayer("Joueur inconnu");
		}
		
	}
	
	//TODO faire la méthode int getNbCartesJoueur(int joueur)
	public int getNbCartesJoueur(int joueur) throws UnknownPlayer{
		int nb = 0;
		if(joueur==1) {
			nb = joueur1.nbCartes();
		}else if(joueur==2) {
			nb = joueur2.nbCartes();
		}else {
			throw new UnknownPlayer("Joueur inconnu");
		}
		return nb;
	}
		
	
	//TODO faire la méthode  public String remporter()
	//attention cette fonction doit regarder tous les cas mais aussi une partie banale et même un tour.
	public String remporter() throws UnknownPlayer{
		String n = null;
		
		if(!tasJ1.isEmpty() && !tasJ2.isEmpty()) {
			Carte carteJoueur1 = this.derniereCarteJouee(1);
			Carte carteJoueur2 = this.derniereCarteJouee(2);
			int gagne = carteJoueur1.compareTo(carteJoueur2);
			if(gagne == 1) {
				if(this.getNbCartesJoueur(2)==0) {
					n = joueur1.getNom().toUpperCase();
				}else {
					n = joueur1.getNom();
				}
				joueur1.remporte(tasJ1);
				joueur1.remporte(tasJ2);
				tasJ1.clear();
				tasJ2.clear();
				
			}else if(gagne == -1) {
				if(this.getNbCartesJoueur(1)==0) {
					n = joueur2.getNom().toUpperCase();
				}else {
					n = joueur2.getNom();
				}
				joueur2.remporte(tasJ1);
				joueur2.remporte(tasJ2);
				tasJ1.clear();
				tasJ2.clear();
			}else {
				if(this.getNbCartesJoueur(1)==0 && this.getNbCartesJoueur(2)==0) {
					n = "Draw";
				}else if(this.getNbCartesJoueur(1)==0) {
					n = joueur2.getNom() + "PlayAgain";
				}else if(this.getNbCartesJoueur(2)==0) {
					n = joueur1.getNom() + "PlayAgain";
				}else {
					n = "TIE";
				}
				System.out.println("\nEgalité");
			}
		}else {
			System.out.println("\nLes deux joueurs doivent avoir joué chacun le même nombre de carte, et au moins une chacun");
		}
		return n;
	}
	
	//TODO faire la méthode public String getNomJoueur(int joueur)
	public String getNomJoueur(int joueur) throws UnknownPlayer{
		String nom = "";
		if(joueur==1) {
			nom += joueur1.getNom();
		}else if(joueur==2) {
			nom += joueur2.getNom();
		}else {
			throw new UnknownPlayer("Joueur inconnu");
		}
		return nom;
	}
}
