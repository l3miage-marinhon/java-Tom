package quincaillerie;

import java.util.ArrayList;

// TODO Questions 21: écrire la classe PieceCompositeEnMontee.
// 
public class Catalogue{
	public ArrayList<Piece> liste;
	
	public Catalogue() {
		// TODO Auto-generated constructor stub
		liste=new ArrayList<>();
	}
	
	private boolean estPresente(Piece p) {
		boolean present=false;
		for(Piece pi :liste) {
			if(p==pi)present=true;
		}
		return present;
	}
	
	public void ajoute(Piece p) {
		if(estPresente(p))liste.add(p);
	}
	
	@Override
	public String toString() {
		String str="";
		for(Piece p :liste) {
			str+=p.toString();
		}
		return str;
	}
	
	public void affichePiece(String nom, String reference) {
		boolean trouve=false;
		Piece pieceTrouve=null;
		for(Piece p : liste) {
			if(p.nom==nom && p.reference==reference) {
				trouve=true;
				pieceTrouve=p;
			}
		}
		if(trouve) { 
			System.out.println(pieceTrouve.toString()) ;
		}else{
			System.out.println("cette piece n'est pas dans le catalogue") ;
		}
	}

	
	
	
}
