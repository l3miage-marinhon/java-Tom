package quincaillerie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

import java.util.Iterator;


public class Stocks {
	
	private Map<Piece, Integer> stocks;

	public Stocks(Collection<Piece> l) {
		stocks = new HashMap<>();
		setStocks(l);
	}
	
	public Map<Piece, Integer> getStocks() {
		return stocks;
	}
	private void setStocks(Collection<Piece> l) {
		for(Piece p : l) {
			stocks.put(p, 20);
		}
	}
	
	public Piece pieceExisteStocks(String nom, String ref, boolean aff) {
		Piece p = null;
		Iterator<Piece> it = stocks.keySet().iterator();
		while(it.hasNext() && p==null) {
			Piece pInt = it.next();
			if(pInt.getNom()==nom && pInt.getRef()==ref) {
				p = pInt;
				if(aff) System.out.println("La pièce de nom \"" + nom + "\" et de ref \"" + ref + "\" est dans les stocks\n");
			}
		}
		if(p==null) {
			System.out.println("La pièce de nom \"" + nom + "\" et de ref \"" + ref + "\" n'est pas dans les stocks\n");
		}
		return p;
	}
	
	public void ajoutePieceStocks(String nom, String ref, int n) {
		Piece p = pieceExisteStocks(nom, ref, false);
		if(p != null) {
			int stock = stocks.get(p);
			stocks.replace(p, stock+n);
		}
	}
	
	public void retirePieceStocks(String nom, String ref, int n) {
		Piece p = pieceExisteStocks(nom, ref, false);
		if(p != null) {
			int stock = stocks.get(p);
			stocks.replace(p, (stock - n > 0 ? stock-n : 0) );
		}
	}
	
	public void supprimePieceStocks(String nom, String ref) {
		Piece p = pieceExisteStocks(nom, ref, false);
		if(p != null) {
			stocks.remove(p);
			System.out.println("Pièce de nom \"" + nom + "\" et de ref \"" + ref + "\" supprimée");
		}
	}
	
	public void afficheStocksPiece(String nom, String ref) {
		Piece p = pieceExisteStocks(nom, ref, false);
		if(p!=null) {
			System.out.println(stocks.get(p) + (stocks.get(p)>1 ? " exemplaires" : " exemplaire") 
							+ " de la pièce de nom \"" + nom + "\" et de ref \"" + ref + " dans les stocks");
		}
	}

	@Override
	public String toString() {
		String s = "\nStocks\n\n";
		for(Piece p : stocks.keySet()) {
			s += "Pièce \"" + p.getNom() + "\", \"" + p.getRef() + " : ";
			int n = stocks.get(p);
			if(n==0) {
				s += " plus de stocks disponibles\n";
			}else if(n==1) {
				s += n + " exemplaire en stock\n";
			}else {
				s += n + " exemplaires en stock\n";
			}
		}
		return s;
	}
	
}
