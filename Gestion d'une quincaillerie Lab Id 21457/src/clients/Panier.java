package clients;

import java.util.HashMap;
import java.util.Map;

import pieces.Piece;
import pieces.PieceDeBase;

public class Panier {

	private Map<Piece, Integer> panier;

	public Panier() {
		panier = new HashMap<>();
		//panier.put(new PieceDeBase("test", "00AZ00", 10, 2, 2), 3);
	}
	
	public Map<Piece, Integer> getPanier(){
		return panier;
	}
	
	public void ajoutPanier(Piece p, int n) {
		if(!panier.containsKey(p)) {
			panier.put(p, n);
		}else {
			panier.put(p, n+panier.get(p));
		}
	}
	
	public void supprimePanier(Piece p, int n) {
		int reste = panier.get(p)-n;
		if(reste > 0) {
			panier.replace(p, reste);
		}else {
			panier.remove(p);
		}
	}
	
	public void supprimePanier(Piece p) {
		panier.remove(p);
	}
	
	public int nbArticles() {
		int n = 0;
		for(Piece p : panier.keySet()) {
			n += panier.get(p);
		}
		return n;
	}
	
	public double total() {
		double tot = 0.0;
		for(Piece p : panier.keySet()) {
			tot += p.prix() * panier.get(p);
		}
		return tot;
	}
		
	@Override
	public String toString() {
		String s = "Panier\n";
		for(Piece p : panier.keySet()) {
			s += p.getNom() + ", " + p.getRef() + " : " + panier.get(p) + " exemplaire(s)\n";
		}
		return s;
	}
}
