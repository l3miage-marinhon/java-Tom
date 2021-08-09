package pieces;

import java.util.ArrayList;
import java.util.regex.Pattern;

// TODO Question 5: écrire la classe PieceCompositeEnKit.
// une pieces composite en kit est une pieces qui a besoins d'un certain temps de montage
//son prix est la somme de toutes ses pièces et son temps de garantie et celui de base /2
//ici les pieces composites on une REF doit toujours commencer avec 01 
//ici on peut afficher l'information de cette pieces de cette manière
//nom : 
// ref : 
// etc .... 
public class PieceCompositeEnKit extends PieceComposite{
	
	private int tempsMontage;
	private double prix;
	private int dureeGarantie;

	
	public PieceCompositeEnKit(String nom, String ref, ArrayList<PieceDeBase> composants, int tempsMontage) {
		super(nom, composants);
		setRef(ref);
		setTempsMontage(tempsMontage);
		setPrix(prixPieceCompKit(composants));
		setDureeGarantie(garCompKit());
	}

	public int getTempsMontage() {
		return tempsMontage;
	}
	public void setTempsMontage(int tempsMontage) {
		this.tempsMontage = (tempsMontage>0 ? tempsMontage : 1);
	}
	
	@Override
	public void setRef(String ref) {
		super.setRef(!Pattern.matches("01[A-Z]{2}[0-9]{2}", ref) ? "01AA00" : ref);
		//exception plus tard si match false
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		super.prix = prix;
	}
	
	public int getDureeGarantie() {
		return dureeGarantie;
	}
	public void setDureeGarantie(int dureeGarantie) {
		super.dureeGarantie = dureeGarantie;
	}
	
	/**
	 * Calcule le prix total d'une pièce composite en kit en fonction des prix des pièces qui la composent
	 * @return {@link Integer} le prix total de la pièce composite
	 */
	private double prixPieceCompKit(ArrayList<PieceDeBase> composants) {
		double s = 0;
		for(PieceDeBase p : composants) {
			s += p.getPrix();
		}
		return s;
	}
	
	/**
	 * Calcule la durée de garantie de la pièce en fonction de sa garantie de base
	 * @return {@link Integer} la durée de garantie de la pièce 
	 */
	private int garCompKit() {
		return super.getDureeGarantieBase()/2;
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "\nTemps de montage : " + getTempsMontage() + (getTempsMontage()>1 ? " jours" : " jour")
				+ "\nPrix : " + getPrix() + (getPrix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + getDureeGarantie() + " mois";
	}
	
}