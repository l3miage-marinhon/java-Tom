package quincaillerie;

import java.util.ArrayList;

// TODO Question 4: écrire la classe PieceComposite.
// une pieces composite est composé d'un ensemble de pieces , ça duree de garantie de base et celle de la pieces la plus basse 
// et la dureer de fabircation et correspond a la pieces la plus longue a fabriquer
public abstract class PieceComposite extends Piece{
	
	private ArrayList<Piece> composants;
	
	public PieceComposite(String nom, ArrayList<Piece> composants) {
		super(nom, 0, 0, 0);
		setComposants(composants);
		setDureeGarantieBase(garBaseComp());
		setDureeFabrication(fabComp());
	}

	public ArrayList<Piece> getComposants(){
		return composants;
	}
	public void setComposants(ArrayList<Piece> composants) {
		this.composants = composants;
	}
	
	private int garBaseComp() {
		int min = Integer.MAX_VALUE;
		for(Piece p : composants) {
			if(p.getDureeGarantieBase()<min) {
				min = p.getDureeGarantieBase();
			}
		}
		return min;
	}
	
	private int fabComp() {
		int max = Integer.MIN_VALUE;
		for(Piece p : composants) {
			if(p.getDureeFabrication()>max) {
				max = p.getDureeFabrication();
			}
		}
		return max;
	}
	
	public double prixTotalComp() {
		double s = 0;
		for(Piece p : composants) {
			s += p.getPrix();
		}
		return s;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nNombre de composants : " + composants.size();
	}
	
}