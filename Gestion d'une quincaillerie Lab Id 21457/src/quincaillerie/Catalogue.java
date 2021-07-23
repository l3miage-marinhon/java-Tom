package quincaillerie;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

// TODO Questions 21: écrire la classe Catalogue
// un catalogue est un ensemble de pieces , on peut ajouter une pieces , regarder si elle existe dans le catalague a l'aide de son nom et ça ref
// on peut afficher tous le catalogue;
//on peut auusi afficher 1 peices grace a son nom et ça ref.
public class Catalogue{
	
	public final class Key{
		private String n;
		private String r;
		public Key(String n, String r) {
			setN(n);
			setR(r);
		}
		public String getN() {
			return n;
		}
		private void setN(String n) {
			this.n = n;
		}	
		public String getR() {
			return r;
		}
		private void setR(String r) {
			this.r = r;
		}
		
		@Override
		public boolean equals(Object key) {
			boolean eq = false;
			if(key instanceof Key && ((Key) key).getN() == n && ((Key) key).getR() == r) {
				eq = true;
			}
			return eq;
		}
		
		@Override
		public int hashCode() {
			return getN().hashCode() + getR().hashCode();
		}
		
	}
	
	private Map<Key,Piece> catalogue;

	public Catalogue() {
		catalogue  = new HashMap<>();
	}
	
	public Map<Key, Piece> getCatalogue() {
		return catalogue;
	}
	
	public void ajoutePiece(Piece p) {
		Key k = new Key(p.getNom(), p.getRef());
		if(!catalogue.containsKey(k)) {
			catalogue.put(k, p);
		}else {
			System.out.println("La piece \n" + p + "\nest deja dans le catalogue");
		}
		//exception plus tard, try catch
	}
	
	public static Catalogue creationCatalogue(Collection<Piece> l) {
		Catalogue catalogue = new Catalogue();
		for(Piece p : l) {
			catalogue.ajoutePiece(p);
		}
		return catalogue;
	}
	
	public boolean pieceExiste(String nom, String ref, boolean aff) {
		boolean exist = false;
		if (catalogue.containsKey(new Key(nom, ref))) {
			exist = true;
			if(aff) System.out.println("La piece de nom \"" + nom + "\" de référence \"" + ref + "\" existe dans le catalogue");
		}else {
			if(aff) System.out.println("La piece de nom \"" + nom + "\" de référence \"" + ref + "\" n'existe pas dans le catalogue");
		}
		return exist;
	}
	
	public void affichePiece(String nom, String ref) {
		if(this.pieceExiste(nom, ref, false)) {
			System.out.println(catalogue.get(new Key(nom, ref)));
		}else {
			System.out.println("La piece de nom \"" + nom + "\" de référence \"" + ref + "\" n'existe pas dans le catalogue");
		}
	}
	
	@Override
	public String toString() {
		String s = "\nCATALOGUE\n\n";
		int i = 1;
		Collection<Piece> listePieces = catalogue.values();
		for(Piece p : listePieces) {
			s += "Piece " + i + "\n" + p.toString() + "\n\n";
			i++;
		}
		return s;
	}
	
	
}
