
package pieces;

import java.util.ArrayList;

// TODO Question 4: écrire la classe PieceComposite.
// une pieces composite est composé d'un ensemble de pieces , ça duree de garantie de base et celle de la pieces la plus basse 
// et la dureer de fabircation et correspond a la pieces la plus longue a fabriquer
public abstract class PieceComposite extends Piece{
	
	private ArrayList<PieceDeBase> composants;

	public PieceComposite(String nom, ArrayList<PieceDeBase> composants) {
		super(nom);
		setComposants(composants);
	}

	public ArrayList<PieceDeBase> getComposants(){
		return composants;
	}
	public void setComposants(ArrayList<PieceDeBase> composants) {
		this.composants = composants;
	}

	/**
	 * Calcule la durée de la garantie de base d'une pièce composite en fonction des garanties de base des pièces qui la composent
	 * @return {@link Integer} la garantie de base de la pièce composite
	 */
	public int dureeGarantieBase() {
		int min = Integer.MAX_VALUE;
		for(PieceDeBase p : composants) {
			if(p.dureeGarantieBase()<min) {
				min = p.dureeGarantieBase();
			}
		}
		return min;
	}

	/**
	 * Calcule la durée de fabrication d'une pièce composite en fonction des durées de fabrication des pièces qui la composent
	 * @return {@link Integer} la durée de fabrication de la pièce composite
	 */
	@Override
	public int dureeFabrication() {
		int max = Integer.MIN_VALUE;
		for(PieceDeBase p : composants) {
			if(p.dureeFabrication()>max) {
				max = p.dureeFabrication();
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nNombre de composants : " + composants.size()
								+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure");
	}
	
	public String toStringHTML() {
		return super.toStringHTML() + "\nNombre de composants : " + composants.size() + "<br>"
								+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure") + "<br>";
	}
	
}
