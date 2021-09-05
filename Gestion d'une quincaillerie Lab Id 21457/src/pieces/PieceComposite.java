
package pieces;

import java.util.Map;

// TODO Question 4: écrire la classe PieceComposite.
// une pieces composite est composé d'un ensemble de pieces , ça duree de garantie de base et celle de la pieces la plus basse 
// et la dureer de fabircation et correspond a la pieces la plus longue a fabriquer
public abstract class PieceComposite extends Piece{
	
	private Map<PieceDeBase, Integer> composants;

	public PieceComposite(String nom, Map<PieceDeBase, Integer> composants) {
		super(nom);
		setComposants(composants);
	}

	public Map<PieceDeBase, Integer> getComposants(){
		return composants;
	}
	public void setComposants(Map<PieceDeBase, Integer> composants) {
		this.composants = composants;
	}
	
	public int nbComposants() {
		int nb = 0;
		for(PieceDeBase p : composants.keySet()) {
			nb += composants.get(p);
		}
		return nb;
	}

	/**
	 * Calcule la durée de la garantie de base d'une pièce composite en fonction des garanties de base des pièces qui la composent
	 * @return {@link Integer} la garantie de base de la pièce composite
	 */
	public int dureeGarantieBase() {
		int min = Integer.MAX_VALUE;
		for(PieceDeBase p : composants.keySet()) {
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
		for(PieceDeBase p : composants.keySet()) {
			if(p.dureeFabrication()>max) {
				max = p.dureeFabrication();
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + "\nNombre de composants : " + nbComposants()
								+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure");
	}
	
	public String toStringHTML() {
		return super.toStringHTML() + "\nNombre de composants : " + nbComposants() + "<br>"
								+ "\nDurée fabrication : " + dureeFabrication() + (dureeFabrication()>1 ? " heures" : " heure") + "<br>";
	}
	
}