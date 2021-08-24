package pieces;

import java.util.ArrayList;
import java.util.regex.Pattern;

// TODO Question 13: écrire la classe PieceCompositeEnMontee.
// une pieces composite monté et une pieces qui a une durre de montage et un prix de montage
// on peut l'afficher
// son prix est la somme de toutes les pieces + le prix du montage
// sa garantie est celle de base + 6 mois
// la ref commence toujours par 02

public class PieceCompositeMontee extends PieceComposite{
	
	private int dureeMontage;
	private double prixMontage;
	private double prix;
	private int dureeGarantie;
	
	public PieceCompositeMontee(String nom, String ref, ArrayList<PieceDeBase> composants, int dureeMontage, double prixMontage) {
		super(nom, composants);
		setRef(ref);
		setDureeMontage(dureeMontage);
		setPrixMontage(prixMontage);
		setPrix(prixPieceCompMontee(composants));
		setDureeGarantie(super.getDureeGarantieBase()+6);
	}

	public int getDureeMontage() {
		return dureeMontage;
	}
	public void setDureeMontage(int dureeMontage) {
		this.dureeMontage = dureeMontage;
	}

	public double getPrixMontage() {
		return prixMontage;
	}
	public void setPrixMontage(double prixMontage) {
		this.prixMontage = prixMontage;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public int getDureeGarantie() {
		return dureeGarantie;
	}
	public void setDureeGarantie(int dureeGarantie) {
		this.dureeGarantie = dureeGarantie;
	}
	
	/**
	 * Calcule le prix total d'une pièce composite montée en fonction des prix des pièces qui la composent
	 * @return {@link Integer} le prix total de la pièce composite
	 */
	private double prixPieceCompMontee(ArrayList<PieceDeBase> composants) {
		double s = 0;
		for(PieceDeBase p : composants) {
			s += p.getPrix();
		}
		return s + getPrixMontage();
	}
	
	@Override
	public void setRef(String ref) {
		super.setRef(!Pattern.matches("02[A-Z]{2}[0-9]{2}", ref) ? "02AA00" : ref);
		//exception plus tard si match false
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "\nDurée de montage : " + getDureeMontage() + (getDureeMontage()>1 ? " jours" : " jour")
				+ "\nPrix de montage : " + getPrixMontage() + (getPrixMontage()>1 ? " euros" : " euro")
				+ "\nPrix : " + getPrix() + (getPrix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + getDureeGarantie() + " mois";
	}
	
}