package pieces;

import java.util.regex.Pattern;

// TODO Question 2: Ecrire la classe PieceDeBase.
// une piece de base a un prix, une dureer de garantie et une duree de fabrication
//sa ref commence toujours par 00 
//on peut l'afficher 
public class PieceDeBase extends Piece{

	private double prix;
	private int dureeGarantie;
	private int dureeGarantieBase;
	private int dureeFabrication;
	
	public PieceDeBase(String nom, String ref, double prix, int dureeGarantie, int dureeFabrication) {
		super(nom);
		setRef(ref);
		setPrix(prix);
		setDureeGarantie(dureeGarantie);
		setDureeGarantieBase(dureeGarantie);
		setDureeFabrication(dureeFabrication);
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	@Override
	public double prix() {
		return prix;
	}
	
	public void setDureeGarantie(int dureeGarantie) {
		this.dureeGarantie = dureeGarantie;
	}
	
	@Override
	public int dureeGarantie() {
		return dureeGarantie;
	}
	
	public void setDureeGarantieBase(int dureeGarantieBase) {
		this.dureeGarantieBase = dureeGarantieBase;
	}
	
	@Override
	public int dureeGarantieBase() {
		return dureeGarantieBase;
	}
	
	public void setDureeFabrication(int dureeFabrication) {
		this.dureeFabrication = (dureeFabrication>0 ? dureeFabrication : 12);
	}
	
	@Override
	public int dureeFabrication() {
		return dureeFabrication;
	}
	
	//inutile ici car poas besion d'appeler super car ellen e doit pas etre defini avant....
	@Override
	public void setRef(String ref) {
		super.ref = (!Pattern.matches("00[A-Z]{2}[0-9]{2}", ref) ? "00AA00" : ref);
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + dureeGarantie() + " mois"
				+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure");
	}
	
	public String toStringHTML() {
		return super.toStringHTML()
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro") + "<br>"
				+ "\nDurée garantie : " + dureeGarantie() + " mois" + "<br>"
				+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure") + "<br>";
	}
	
}