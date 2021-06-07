package worldcompany;

// TODO Ecrire la classe Employe ici. Attention, rien ne pourra être évalué si vous ne commencez pas par écrire correctement cette classe !
// 
public class Employe implements Comparable<Employe>{
	private int codeEmploye;
	private String nom;
	private String prenom;
	private int anneeEmbauche;
	
	
	public Employe(String nom,String prenom,int anneEmbauche) {
		this.nom=nom;
		this.prenom=prenom;
		setAnneEmbauche(anneEmbauche);
	}
	
	public String presentation() {
		String str="";
		str+="["+codeEmploye+"] ";
		str+="Bonjour, je m'appelle "+prenom+" "+nom;
		str+=" et je gagne "+getSalaire()+" euros.";
		return str;
	}
	
	@Override
	public int compareTo(Employe e) {
		if(e.codeEmploye==codeEmploye) {
			if(e.nom.equals(nom)) {
				if(e.prenom.equals(prenom)) {
					if(e.anneeEmbauche==anneeEmbauche) {
						return 0;
					}else {
						return 1;
					}
				}else {
					return 2;
				}
			}else {
				return 3;
			}
		}else {
			return 4;
			}
}
	public double getSalaire() {
		return 800;
	}
	protected void setCodeEmployer(int codeEmployer) {
		this.codeEmploye=codeEmployer;
	}
	protected int getAnneEmbauche() {
		return anneeEmbauche;
	}
	private void setAnneEmbauche(int anneEmbauche) {
		if(anneEmbauche >=1975 ) {
			this.anneeEmbauche=anneEmbauche;
		}else {
			this.anneeEmbauche=1975;
		}
	}
}
