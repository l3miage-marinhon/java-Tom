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
	
	public PieceCompositeMontee(String nom, String ref, ArrayList<PieceDeBase> composants, int dureeMontage, double prixMontage) {
		super(nom, composants);
		setRef(ref);
		setDureeMontage(dureeMontage);
		setPrixMontage(prixMontage);
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
	
	/**
	 * Calcule le prix total d'une pièce composite montée en fonction des prix des pièces qui la composent
	 * @return {@link Integer} le prix total de la pièce composite
	 */
	@Override
	public double prix() {
		double s = 0;
		for(PieceDeBase p : super.getComposants()) {
			s += p.prix();
		}
		return s + getPrixMontage();
	}

	@Override
	public int dureeGarantie() {
		return super.dureeGarantieBase()+6;
	}
	
	@Override
	public void setRef(String ref) {
		super.ref = (!Pattern.matches("02[A-Z]{2}[0-9]{2}", ref) ? "02AA00" : ref);
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "\nDurée de montage : " + getDureeMontage() + (getDureeMontage()>1 ? " heures" : " heure")
				+ "\nPrix de montage : " + getPrixMontage() + (getPrixMontage()>1 ? " euros" : " euro")
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + dureeGarantie() + " mois";
	}
	
	public String toStringHTML() {
		return super.toStringHTML() 
				+ "\nDurée de montage : " + getDureeMontage() + (getDureeMontage()>1 ? " heures" : " heure") + "<br>"
				+ "\nPrix de montage : " + getPrixMontage() + (getPrixMontage()>1 ? " euros" : " euro") + "<br>"
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro") + "<br>"
				+ "\nDurée garantie : " + dureeGarantie() + " mois" + "<br>";
	}
	
}