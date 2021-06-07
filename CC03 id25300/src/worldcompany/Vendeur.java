package worldcompany;

// TODO Ecrire la classe Vendeur ici.
// 
public class Vendeur extends Commercial{ 
	private final static double POURCENT_VENDEUR=0.2;
	private final static double BONUS_VENDEUR=800;
	private final static int PREFIX_VENDEUR=1000;
	private static int nbVendeur;

	public Vendeur(String nom, String prenom, int anneEmbauche, double chiffreAffaire) {
		super(nom, prenom, anneEmbauche, chiffreAffaire);
		// TODO Auto-generated constructor stub
		nbVendeur++;
	}
	
	public String presentation() {
		String str=super.presentation();
		str+=" Je suis vendeur depuis ";
		str+=super.getAnneEmbauche()+".";
		return str;
	}
	@Override
	public double getSalaire() {
		return BONUS_VENDEUR+(super.getChiffreAffaire()*POURCENT_VENDEUR);
	}
	
}