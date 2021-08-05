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
	
	public PieceCompositeEnKit(String nom, String ref, ArrayList<Piece> composants, int tempsMontage) {
		super(nom, composants);
		setRef(ref);
		setTempsMontage(tempsMontage);
		setPrix(prixTotalComp());
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
		super.ref = !Pattern.matches("01[A-Z]{2}[0-9]{2}", ref) ? "01AA00" : ref;
		//exception plus tard si match false
	}

	@Override
	public void setPrix(double prix) {
		super.prix = prix;
	}
	
	@Override
	public void setDureeGarantie(int dureeGarantie) {
		super.dureeGarantie = dureeGarantie;
	}
	
	/**
	 * Calcule la durée de garantie de la pièce en fonction de sa garantie de base
	 * @return {@link Integer} la durée de garantie de la pièce 
	 */
	private int garCompKit() {
		return getDureeGarantieBase()/2;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTemps de montage : " + getTempsMontage() + (getTempsMontage()>1 ? " jours" : " jour");
	}
	
}