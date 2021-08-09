package pieces;

import java.util.regex.Pattern;

// TODO Question 2: Ecrire la classe PieceDeBase.
// une piece de base a un prix, une dureer de garantie et une duree de fabrication
//sa ref commence toujours par 00 
//on peut l'afficher 
public class PieceDeBase extends Piece{

	private double prix;
	private int dureeGarantie;
	private int dureeFabrication;
	
	public PieceDeBase(String nom, String ref, double prix, int dureeGarantie, int dureeFabrication) {
		super(nom);
		setRef(ref);
		setPrix(prix);
		setDureeGarantie(dureeGarantie);
		setDureeFabrication(dureeFabrication);
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPrix() {
		return prix;
	}
	
	public void setDureeGarantie(int dureeGarantie) {
		this.dureeGarantie = dureeGarantie;
	}
	public int getDureeGarantie() {
		return dureeGarantie;
	}
	
	public void setDureeFabrication(int dureeFabrication) {
		this.dureeFabrication = (dureeFabrication>0 ? dureeFabrication : 12);
	}
	public int getDureeFabrication() {
		return dureeFabrication;
	}
	
	@Override
	public void setRef(String ref) {
		super.setRef(!Pattern.matches("00[A-Z]{2}[0-9]{2}", ref) ? "00AA00" : ref);
		//exception plus tard si match false
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ "\nPrix : " + getPrix() + (getPrix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + getDureeGarantie() + " mois"
				+ "\nDurée fabrication : " + getDureeFabrication() + (getDureeFabrication()>1 ? " heures" : " heure");
	}
	
}