package quincaillerie;

import java.util.Set;

import pieces.*;

import java.util.Iterator;

// TODO Questions 21: écrire la classe Catalogue
// un catalogue est un ensemble de pieces , on peut ajouter une pieces , regarder si elle existe dans le catalague a l'aide de son nom et ça ref
// on peut afficher tous le catalogue;
//on peut auusi afficher 1 peices grace a son nom et ça ref.
public class Catalogue{
	
	public Set<Piece> catalogue;
	
	public Catalogue(Set<Piece> catalogue) {
		setCatalogue(catalogue);
	}

	public Set<Piece> getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(Set<Piece> catalogue) {
		this.catalogue = catalogue;
	}
	
	/**
	 * Vérifie l'existence d'une pièce dans le catalogue
	 * @param p {@link Piece} la pièce dont on souhaite vérifier l'existence
	 * @return {@link Boolean} true si la pièce existe dans le catalogue, false sinon
	 */
	public boolean pieceExiste(Piece p) {
		return catalogue.contains(p);
	}
	
	/**
	 * Vérifie l'existence d'une pièce dans le catalogue en fonction de son nom et de sa ref
	 * @param nom {@link String} le nom de la pièce
	 * @param ref {@link String} la référence de la pièce
	 * @param affichePiece {@link Boolean} true si l'on souhaite afficher le détail de la pièce recherchée, false sinon
	 * @return {@link Boolean} true si la pièce existe dans le catalogue, false sinon
	 */
	public Piece pieceExiste(String nom, String ref, boolean affichePiece) {
		boolean f = false;
		Piece piece = null;
		Iterator<Piece> it = catalogue.iterator();
		while(it.hasNext() && !f) {
			Piece ptemp = it.next();
			if(ptemp.getNom().equals(nom) && ptemp.getRef().equals(ref)) {
				f=true;
				piece = ptemp;
				if(affichePiece) System.out.println("\nPiece nom \"" + nom + "\", ref \"" + ref + "\" : \n" + piece + "\n");;
			}
		}
		if(!f && affichePiece) System.out.println("\nLa piece de nom \"" + nom + "\" et de ref \"" + ref + "\" n'est pas dans le catalogue\n");
		return piece;
	}
	
	/**
	 * Ajoute une pièce au catalogue sauf si elle existe
	 * @param p {@link Piece} la pièce à ajouter au catalogue
	 */
	public void ajoutePiece(Piece p) {
		if(catalogue.add(p)==false) System.out.println("Cette piece existe déjà dans le catalogue");
	}
	
	/**
	 * Supprime une pièce du catalogue en donnant son nom et sa référence 
	 * @param nom {@link String} le nom de la pièce
	 * @param ref {@link String} la référence de la pièce
	 */
	public void supprimePiece(String nom, String ref) {
		catalogue.remove(pieceExiste(nom, ref, false));
	}
	
	
	/**
	 * Supprime une pièce du catalogue
	 * @param p {@link Piece} la pièce à supprimer
	 */
	public void supprimePiece(Piece p) {
		catalogue.remove(p);
	}
		
	/**
	 * Affiche les informations d'une pièce du catalogue en donnant son nom et sa référence
	 * @param nom {@link String} le nom de la pièce
	 * @param ref {@link String} la référence de la pièce
	 */
	public void affichePieceCatalogue(String nom, String ref) {
		pieceExiste(nom, ref, true);
	}
	
	public PieceCompositeMontee pieceMonteeFromKit(PieceCompositeEnKit piece) {	
		return (PieceCompositeMontee) pieceExiste(piece.getNom(), ("02" + piece.getRef().substring(2)), false);
	}
	
	public PieceCompositeEnKit pieceKitFromMontee(PieceCompositeMontee piece) {	
		return (PieceCompositeEnKit) pieceExiste(piece.getNom(), ("01" + piece.getRef().substring(2)), false);
	}
	
	@Override
	public String toString() {
		String s = "\nCATALOGUE\n";
		int i = 1;
		for(Piece p : catalogue) {
			s += "\nPiece " + i + "\n" + p.toString() + "\n";
			i++;
		}
		return s;
	}
	
	
}
