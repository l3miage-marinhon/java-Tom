package pieces;

import java.util.ArrayList;
import java.util.Map;
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
	
	public PieceCompositeEnKit(String nom, String ref, Map<PieceDeBase, Integer> composants, int tempsMontage) {
		super(nom, composants);
		setRef(ref);
		setTempsMontage(tempsMontage);
	}

	public int getTempsMontage() {
		return tempsMontage;
	}
	public void setTempsMontage(int tempsMontage) {
		this.tempsMontage = (tempsMontage>0 ? tempsMontage : 1);
	}
	
	@Override
	public void setRef(String ref) {
		super.ref = (!Pattern.matches("01[A-Z]{2}[0-9]{2}", ref) ? "01AA00" : ref);
	}

	@Override
	public double prix() {
		double s = 0;
		for(PieceDeBase p : super.getComposants().keySet()) {
			s += p.prix() * super.getComposants().get(p);
		}
		return s;
	}
	
	@Override
	public int dureeGarantie() {
		return super.dureeGarantieBase()/2;
	}
	
	@Override
	public String toString() {
		return super.toString() 
				+ "\nTemps de montage : " + getTempsMontage() + (getTempsMontage()>1 ? " heure" : " heure")
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro")
				+ "\nDurée garantie : " + dureeGarantie() + " mois";
	}
	
	public String toStringHTML() {
		return super.toStringHTML() 
				+ "\nTemps de montage : " + getTempsMontage() + (getTempsMontage()>1 ? " heures" : " heure") + "<br>"
				+ "\nPrix : " + prix() + (prix()>1 ? " euros" : " euro") + "<br>"
				+ "\nDurée garantie : " + dureeGarantie() + " mois" + "<br>";
	}
	
}