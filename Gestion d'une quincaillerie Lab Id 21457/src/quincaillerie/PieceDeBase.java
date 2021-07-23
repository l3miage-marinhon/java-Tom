package quincaillerie;

import java.util.regex.Pattern;

// TODO Question 2: Ecrire la classe PieceDeBase.
// une piece de base a un prix, une dureer de garantie et une duree de fabrication
//sa ref commence toujours par 00 
//on peut l'afficher 
public class PieceDeBase extends Piece{

	public PieceDeBase(String nom, String ref, double prix, int dureeGarantie, int dureeFabrication) {
		super(nom, prix, dureeGarantie, dureeFabrication);
		setRef(ref);
	}
	
	@Override
	public void setRef(String ref) {
		super.setRef(!Pattern.matches("00[A-Z]{2}[0-9]{2}", ref) ? "00AA00" : ref);
		//exception plus tard si match false
	}
	
}