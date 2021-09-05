package quincaillerie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;

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
			/*if(p instanceof PieceDeBase || p instanceof PieceCompositeEnKit)*/ stocks.put(p, 20);
		}
	}
	
	/**
	 * Vérifie l'existence d'une pièce dans les stocks. Affichera toujours un message si la pièce est absente des stocks
	 * @param p {@link Piece} la pièce recherchée
	 * @param aff {@link Boolean} true si l'on veut afficher que la pièce a été trouvée, false sinon
	 * @return {@link Boolean} true si la pièce est dans les stocks, false sinon
	 */
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
	
	/**
	 * Augmente les stocks d'une pièce seulement si cette dernière est déjà présente dans les stocks
	 * @param p {@link Piece} la pièce dont on veut augmenter les stocks
	 * @param n {@link Integer} le nombre d'exemplaires de la pièce à ajouter aux stocks
	 */
	public void augmenteStocksPiece(Piece p, int n) {
		if(pieceExiste(p, false)) {
			stocks.replace(p, stocks.get(p)+n);
		}
	}
	
	public PieceCompositeMontee pieceMonteeFromKit(PieceCompositeEnKit piece) {	
		PieceCompositeMontee pcm = null;
		
		Iterator<Piece> it = stocks.keySet().iterator();
		while(it.hasNext() && pcm == null) {
			Piece p = it.next();
			if(p instanceof PieceCompositeMontee && piece.getNom().equals(p.getNom()) && piece.getRef().equals(("01" + p.getRef().substring(2)) )){
				pcm = (PieceCompositeMontee) p;
			}
		}
		return pcm;
	}
	
	public PieceCompositeEnKit pieceKitFromMontee(PieceCompositeMontee piece) {	
		PieceCompositeEnKit pck = null;
		
		Iterator<Piece> it = stocks.keySet().iterator();
		while(it.hasNext() && pck == null) {
			Piece p = it.next();
			if(p instanceof PieceCompositeEnKit && piece.getNom().equals(p.getNom()) && piece.getRef().equals(("02" + p.getRef().substring(2)) )){
				pck = (PieceCompositeEnKit) p;
			}
		}
		return pck;
	}
	
	/**
	 * Réduit les stocks d'une pièce seulement si cette dernière est déjà présente dans les stocks
	 * @param p {@link Piece} la pièce dont on veut augmenter les stocks
	 * @param n {@link Integer} le nombre d'exemplaires de la pièce à retirer aux stocks
	 */
	public void supprimeStocksPiece(Piece p, int n) {
		if(pieceExiste(p, false)) {
			int stock = stocks.get(p);
			
			if(p instanceof PieceCompositeEnKit) {
				PieceCompositeMontee pcm = pieceMonteeFromKit((PieceCompositeEnKit) p);
				if(pcm != null) stocks.replace(  pcm  , (stock - n > 0 ? stock-n : 0) );
			}else if(p instanceof PieceCompositeMontee) {
				PieceCompositeEnKit pck = pieceKitFromMontee((PieceCompositeMontee) p);
				if(pck != null) stocks.replace(  pck  , (stock - n > 0 ? stock-n : 0) );
			}
			stocks.replace(p, (stock - n > 0 ? stock-n : 0) );
		}
	}
	
	public void supprimeStocksPieces(Map<Piece, Integer> map) {
		for(Piece p : map.keySet()) {
			supprimeStocksPiece(p, map.get(p));
		}
	}
	
	/**
	 * Ajoute une nouvelle pièce dans les stocks, ainsi que son nombre d'exemplaires
	 * @param p {@link Piece} la pièce dont on veut augmenter les stocks
	 * @param n {@link Integer} le nombre d'exemplaires de la pièce
	 */
	public void nouvellePieceStocks(Piece p, int n) {
		if(stocks.putIfAbsent(p, n) != null) {	//attention, peut renvoyer null si la key est associée à la valeur null (normalement n'arrivera jamais)
			System.out.println("Cette pièce est déjà enregistrée dans les stocks");
		}
		
	}
	
	/**
	 * Supprime une pièce des stocks
	 * @param p {@link Piece} la pièce à supprimer des stocks
	 */
	public void supprimePiece(Piece p) {
		if(stocks.remove(p) != null){
			System.out.println("Pièce de nom \"" + p.getNom() + "\" et de ref \"" + p.getRef() + "\" supprimée");
		}
	}
	
	/**
	 * Donne le nombre d'exemplaires d'une pièce
	 * @param p {@link Piece} la pièce dont on souhaite connaitre le stock
	 * @return {@link Integer} le nombre d'exemplaires de la pièce en stocks
	 */
	public Integer stocksPiece(Piece p) {
		return stocks.get(p);
	}
	
	/**
	 * Vérifie si une pièce est en stocks, c'est à dire si elle a au moins 1 exemplaire en stocks
	 * @param p {@link Piece} la pièce dont on souhaite vérifier la disponibilité
	 * @return {@link Boolean} true si la pièce est disponible, false sinon
	 */
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
