package quincaillerie;

import java.util.Set;

import pieces.Piece;

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
	
	public void ajoutePieceCatalogue(Piece p) {
		if(catalogue.add(p)==false) System.out.println("Cette piece existe déjà dans le catalogue");
	}
	
	public boolean pieceExisteCatalogue(String nom, String ref, boolean aff) {
		boolean f = false;
		Piece p = null;
		Iterator<Piece> it = catalogue.iterator();
		while(it.hasNext() && !f) {
			p = it.next();
			if(p.getNom()==nom && p.getRef()==ref) {
				f=true;
				if(aff) {
					System.out.println("\nPiece nom \"" + nom + "\", ref \"" + ref + "\" : \n" + p + "\n");;
				}else {
					System.out.println("\nLa piece de nom \"" + nom + "\" et de ref \"" + ref + "\" est dans le catalogue\n");
				}
			}
		}
		if(!f) System.out.println("\nLa piece de nom \"" + nom + "\" et de ref \"" + ref + "\" n'est pas dans le catalogue\n");
		return f;
	}
	
	public void affichePieceCatalogue(String nom, String ref) {
		pieceExisteCatalogue(nom, ref, true);
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
