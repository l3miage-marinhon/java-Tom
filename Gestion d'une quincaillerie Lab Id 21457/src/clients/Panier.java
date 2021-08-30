package clients;

import java.util.HashMap;
import java.util.Map;

import pieces.Piece;
import pieces.PieceDeBase;

public class Panier {

	private Map<Piece, Integer> panier;

	public Panier() {
		panier = new HashMap<>();
	}
	
	public Map<Piece, Integer> getPanier(){
		return panier;
	}
	
	public void ajoutPiecePanier(Piece p, int n) {
		if(!panier.containsKey(p)) {
			panier.put(p, n);
		}else {
			panier.put(p, n+panier.get(p));
		}
	}
	
	public void supprimePiecePanier(Piece p, int n) {
		int reste = panier.get(p)-n;
		if(reste > 0) {
			panier.replace(p, reste);
		}else {
			panier.remove(p);
		}
	}
	
	public void supprimePiecePanier(Piece p) {
		panier.remove(p);
	}
	
	public void modifiePiecePanier(Piece p, int n) {
		panier.replace(p, n);
	}
	
	public void viderPanier() {
		panier.clear();
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
