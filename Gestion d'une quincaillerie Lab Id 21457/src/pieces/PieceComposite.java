package pieces;

import java.util.ArrayList;

// TODO Question 4: écrire la classe PieceComposite.
// une pieces composite est composé d'un ensemble de pieces , ça duree de garantie de base et celle de la pieces la plus basse 
// et la dureer de fabircation et correspond a la pieces la plus longue a fabriquer
public abstract class PieceComposite extends Piece{
	
	private ArrayList<PieceDeBase> composants;
	private int dureeGarantieBase;
	private int dureeFabrication;
	
	public PieceComposite(String nom, ArrayList<PieceDeBase> composants) {
		super(nom);
		setComposants(composants);
		setDureeGarantieBase(garBaseComp());
		setDureeFabrication(fabComp());
	}

	public ArrayList<PieceDeBase> getComposants(){
		return composants;
	}
	public void setComposants(ArrayList<PieceDeBase> composants) {
		this.composants = composants;
	}
	
	public int getDureeGarantieBase() {
		return dureeGarantieBase;
	}
	public void setDureeGarantieBase(int dureeGarantieBase) {
		this.dureeGarantieBase = dureeGarantieBase;
	}
	
	public int getDureeFabrication() {
		return dureeFabrication;
	}
	public void setDureeFabrication(int dureeFabrication) {
		this.dureeFabrication = dureeFabrication;
	}
	
	/**
	 * Calcule la durée de la garantie de base d'une pièce composite en fonction des garanties de base des pièces qui la composent
	 * @return {@link Integer} la garantie de base de la pièce composite
	 */
	private int garBaseComp() {
		int min = Integer.MAX_VALUE;
		for(PieceDeBase p : composants) {
			if(p.getDureeGarantie()<min) {
				min = p.getDureeGarantie();
			}
		}
		return min;
	}
	
	/**
	 * Calcule la durée de fabrication d'une pièce composite en fonction des durées de fabrication des pièces qui la composent
	 * @return {@link Integer} la durée de fabrication de la pièce composite
	 */
	private int fabComp() {
		int max = Integer.MIN_VALUE;
		for(PieceDeBase p : composants) {
			if(p.getDureeFabrication()>max) {
				max = p.getDureeFabrication();
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nNombre de composants : " + composants.size()
								+ "\nDurée fabrication : " + getDureeFabrication() + (getDureeFabrication()>1 ? " heures" : " heure");
	}
	
}