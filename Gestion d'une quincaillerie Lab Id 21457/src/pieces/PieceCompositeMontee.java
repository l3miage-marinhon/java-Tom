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
	
	public PieceCompositeMontee(String nom, String ref, ArrayList<Piece> composants, int dureeMontage, double prixMontage) {
		super(nom, composants);
		setRef(ref);
		setDureeMontage(dureeMontage);
		setPrixMontage(prixMontage);
		setPrix(prixTotalComp()+getPrixMontage());
		setDureeGarantie(getDureeGarantieBase()+6);
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
	
	@Override
	public void setPrix(double prix) {
		super.prix = prix;
	}
	
	@Override
	public void setDureeGarantie(int dureeGarantie) {
		super.dureeGarantie = dureeGarantie;
	}
	
	@Override
	public void setRef(String ref) {
		super.ref = !Pattern.matches("02[A-Z]{2}[0-9]{2}", ref) ? "02AA00" : ref;
		//exception plus tard si match false
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "\nDurée de montage : " + getDureeMontage() + (getDureeMontage()>1 ? " jours" : " jour")
				+ "\nPrix de montage : " + getPrixMontage() + (getPrixMontage()>1 ? " euros" : " euro");
	}
	
}