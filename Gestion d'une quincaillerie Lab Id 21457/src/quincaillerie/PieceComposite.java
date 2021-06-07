package quincaillerie;

import java.util.ArrayList;

// TODO Question 4: écrire la classe PieceComposite.
// 
public abstract class PieceComposite extends Piece{
	ArrayList<Piece> liste;
	
	
	public PieceComposite(String nom, String reference) {
		super(nom, reference);
		// TODO Auto-generated constructor stub
		liste=new ArrayList<Piece>();
	}
	
	public void ajoute(Piece p) {
		liste.add(p);
	}
	
	@Override
	public String toString() {
		String str=super.toString()+"\n";
		for(Piece p :liste) {
			str+="\t\t"+p.toString()+"\n";
		}
		return str;
	}
	
	@Override
	public int dureeGarantieDeBase() {
		int min=Integer.MAX_VALUE;
		for (Piece p : liste) {
			if(min>p.dureeGarantie() && !(p instanceof PieceComposite))min=p.dureeGarantie();
		}
		return min;
	}
	@Override
	public int dureeFabrication() {
		int max=0;
		for (Piece p : liste) {
			if(max<p.dureeFabrication())max=p.dureeFabrication();
		}
		return max;
		
	}
}