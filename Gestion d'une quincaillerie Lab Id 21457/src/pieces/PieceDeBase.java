package pieces;

import java.util.regex.Pattern;

// TODO Question 2: Ecrire la classe PieceDeBase.
// une piece de base a un prix, une dureer de garantie et une duree de fabrication
//sa ref commence toujours par 00 
//on peut l'afficher 
public class PieceDeBase extends Piece{

	public PieceDeBase(String nom, String ref, double prix, int dureeGarantie, int dureeFabrication) {
		super(nom);
		setRef(ref);
		setPrix(prix);
		setDureeGarantieBase(dureeGarantie);
		setDureeGarantie(dureeGarantie);
		setDureeFabrication(dureeFabrication);
	}
	
	@Override
	public void setPrix(double prix) {
		super.prix = prix;
	}
	
	@Override
	public void setDureeGarantieBase(int dureeGarantie) {
		super.dureeGarantieBase = (dureeGarantieBase>0 ? dureeGarantieBase : 12);
	}
	
	@Override
	public void setDureeGarantie(int dureeGarantie) {
		super.dureeGarantie = dureeGarantie;
	}
	
	@Override
	public void setDureeFabrication(int dureeFabrication) {
		super.dureeFabrication = (dureeFabrication>0 ? dureeFabrication : 12);
	}
	
	@Override
	public void setRef(String ref) {
		super.ref = !Pattern.matches("00[A-Z]{2}[0-9]{2}", ref) ? "00AA00" : ref;
		//exception plus tard si match false
	}
	
}