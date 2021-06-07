package bataille;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class Joueur{
	private String nom;
	private Deque<Carte> tab;
	
	
	
	public Joueur(String nom) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
		tab= new ArrayDeque<Carte>();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void remporte(Carte carte) {
		tab.addLast(carte);
	}
	public void remporte(Collection<Carte> cartes) {
		for(Carte c : cartes) {
			tab.addLast(c);
		}
	}
	
	public Carte joue() {
		Carte carte=tab.getFirst();
		tab.removeFirst();
		return carte;
	}
	
	public int nbCartes() {
		return tab.size();
		
	}
	
	public void printTas() {
		System.out.println("--- Tas du joueur: "+nom+"---");
		for(Carte c:tab) {
			System.out.println(c.toString());
		}
	}
	

}
