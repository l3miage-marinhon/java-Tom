package quincaillerie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

/**
 * 
 * @author tom
 *
 */
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
	
	public boolean pieceExiste(Piece p, boolean aff) {
		
		boolean f = false;
		if(stocks.containsKey(p)) {
			f = true;
			if(aff) System.out.println("La pièce de nom \"" + p.getNom() + "\" et de ref \"" + p.getRef() + "\" est dans les stocks\n");
		}else {
			System.out.println("La pièce de nom \"" + p.getNom() + "\" et de ref \"" + p.getRef() + "\" n'est pas dans les stocks\n");
		}
		return f;
	}
	
	public void augmenteStocksPiece(Piece p, int n) {
		if(pieceExiste(p, false)) {
			stocks.replace(p, stocks.get(p)+n);
		}
	}
	
	public void supprimeStocksPiece(Piece p, int n) {
		if(pieceExiste(p, false)) {
			int stock = stocks.get(p);
			stocks.replace(p, (stock - n > 0 ? stock-n : 0) );
		}
	}
	
	/**
	 * 
	 * @param p {@link Piece} à ajouter des les stocks
	 * @param n
	 * 
	 */
	public void nouvellePieceStocks(Piece p, int n) {
		if(stocks.putIfAbsent(p, n) != null) {	//attention, peut renvoyer null si la key est associée à la valeur null (normalement n'arrivera jamais)
			System.out.println("Cette pièce est déjà enregistrée dans les stocks");
		}
		
	}
	
	public void supprimePiece(Piece p) {
		if(stocks.remove(p) != null){
			System.out.println("Pièce de nom \"" + p.getNom() + "\" et de ref \"" + p.getRef() + "\" supprimée");
		}
	}
	
	public Integer stocksPiece(Piece p) {
		return stocks.get(p);
	}
	
	public boolean pieceDisponible(Piece p) {
		Integer n = stocksPiece(p);
		return ( (n == null ? 0 : n) > 0 ? true : false );
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
