package bataille;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class Jeu {
	private Joueur joueur1;
	private Joueur joueur2;
	private Collection<Carte> JeuDeCarte;
	private Deque<Carte> jouees1;
	private Deque<Carte> jouees2;
	
	public Jeu(String joueur1, String joueur2) {
		// TODO Auto-generated constructor stub
		this.joueur1=new Joueur(joueur1);
		this.joueur2=new Joueur(joueur2);
		this.JeuDeCarte=new ArrayList<Carte>();
		this.jouees1=new LinkedList<>();
		this.jouees2=new LinkedList<>();
		initialiseJeuDeCartes();
	}
	
	private void initialiseJeuDeCartes(){
	        for (Couleur couleur: Couleur.values()) {
	            for (Valeur valeur: Valeur.values()) {
	                JeuDeCarte.add(new Carte(couleur, valeur));
	            }
	        }
	}
	
	public void distribuer() {
		int i=1;
		for(Carte c : JeuDeCarte) {
			if(i==1) {
				joueur1.remporte(c);
				i=2;
			}else {
				joueur2.remporte(c);
				i=1;
			}
		}
	}
	
	public void melangerCartes() {
		List<Carte> tabMelangee=new ArrayList<Carte>();
		tabMelangee.addAll(JeuDeCarte);
		Collections.shuffle(tabMelangee);
		this.JeuDeCarte=tabMelangee;
	}
	
	
	public void printJeuDeCarte() {
		System.out.println("------------jeu de carte---------");
		for(Carte c : JeuDeCarte) {
			System.out.println(c.toString());
		}
	}
	public Carte joueurJoue(int joueur) {
		switch(joueur) {
			case 1:Carte c=joueur1.joue();
				   jouees1.addFirst(c);
				   return c;
				   
			case 2:Carte c2 =joueur2.joue();
					jouees2.addFirst(c2);
					return c2;
					
			default:return null;
		}
	}
	
	public Carte derniereCarteJouee(int joueur) {
		switch(joueur) {
			case 1:return jouees1.peekFirst();
			case 2:return jouees2.peekFirst();
			default : return null;
		}
	}
	
	public void printTasJoueur(int joueur) {
		switch(joueur) {
			case 1 :joueur1.printTas();break;
			case 2 :joueur2.printTas();break;
			default :System.out.println("---Ce joueur n'existe pas-----");
		}
		
	}
	public int getNbCartesJoueur(int joueur) {
		switch(joueur) {
		case 1 :return joueur1.nbCartes();
		case 2 :return joueur2.nbCartes();
		default: return 0;
	}
		
	}
	
	public String remporter() {
		if(jouees1.isEmpty() || jouees2.isEmpty())
        {
            if(jouees1.isEmpty())
                return "C'est le joueur " + joueur2.getNom() + "qui remporte la partie";
            else
                return "C'est le joueur " + joueur1.getNom() + "qui remporte la partie";
        }
        else
        {
            Carte c1 = derniereCarteJouee(1);
            Carte c2 = derniereCarteJouee(2);
            String s = "";
            if(c1.compareTo(c2) > 0)
            {
                joueur1.remporte(jouees1);
                joueur1.remporte(jouees2);
                jouees1.clear();
                jouees2.clear();
                s = "C'est le joueur " + joueur1.getNom() + " qui remporte le pli";
            }
            if(c1.compareTo(c2) < 0)
            {
                joueur2.remporte(jouees1);
                joueur2.remporte(jouees2);
                jouees1.clear();
                jouees2.clear();
                s = "C'est le joueur " + joueur2.getNom() + " qui remporte le pli";
            }
            if(c1.compareTo(c2) == 0)
            {
                s = "Bataille !!";
            }
            if(joueur1.nbCartes() == 0 || joueur2.nbCartes() == 32)
            {
                s = "C'est le joueur " + joueur2.getNom() + " qui remporte la partie";
            }
            if(joueur2.nbCartes() == 0 || joueur1.nbCartes() == 32)
            {
                s = "C'est le joueur " + joueur1.getNom() + " qui remporte la partie";
            }
            return s;
        }
    }

	
	public String getNomJoueur(int joueur) {
		switch(joueur) {
		case 1 :return joueur1.getNom();
		case 2 :return joueur2.getNom();
		default :return "---Ce joueur n'existe pas-----";
	}
		
	}

}
