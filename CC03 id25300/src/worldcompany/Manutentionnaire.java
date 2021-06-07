package worldcompany;

// TODO Ecrire la classe Manutentionnaire ici.
// 
public class Manutentionnaire extends Employe{
	private double heures;
	private static final int PREFIX_MANUTENTIONNAIRE=4000;
	private static int nbManutentionnaires;
	private static final double SALAIRE_HORAIRE=34.50;
	
	public Manutentionnaire(String nom, String prenom, int anneEmbauche,double heures) {
		super(nom, prenom, anneEmbauche);
		// TODO Auto-generated constructor stub
		this.heures=heures;
		nbManutentionnaires++;
	}
	
	public String presentation(){
			String str=super.presentation();
			str+="Je suis manutentionnaire depuis "+super.getAnneEmbauche();
			return str;
	}
	
	public double getSalaire() {
		return heures*SALAIRE_HORAIRE;
	}
	
	
}