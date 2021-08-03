package bataille;
//quand la classe  est terminé faire le TestCarte
//une carte est un objet qui est comparable

public class Carte implements Comparable<Carte>{
	//TODO mettre les attribues
	private Couleur couleur;
	private Valeur valeur;
	
	//TODO constructeur
	public Carte(Couleur couleur, Valeur valeur) {
		this.couleur = couleur;
		this.valeur = valeur;
	}

	
	//TODO decommenter lorsque tu codé le tupe enum Valeur
	public String getFileName() {
		return valeur.getNom()+"-de-"+couleur.getNom()+".png";
		
	}
	
	
	//TODO redéfinir la méthode toString;
	@Override
	public String toString() {
		return valeur.getNom()+" de "+couleur.getNom();
	}
	
	//renvoie 1 si la carte this est plus forte que la carte carte, 0 si égalité, -1 sinon
	@Override
	public int compareTo(Carte carte) {
		int res;
		if(this.valeur.ordinal() > carte.valeur.ordinal()) {
			res=1;
		}else if(this.valeur.ordinal() < carte.valeur.ordinal()) {
			res=-1;
		}else {
			res=0;
		}
		return res;
	}
	
	//TODO redéfinir la méthode equals
	@Override
	public boolean equals(Object carte) {
		boolean res;
		if(carte instanceof Carte && ((this.compareTo((Carte)carte)) == 0 && this.couleur.getNom() == ((Carte)carte).couleur.getNom())) {
			res=true;
		}else {
			res=false;
		}
		return res;
	}
	
	//TODO redéfinir la méthode hashCode
	@Override
	public int hashCode() {
		return couleur.ordinal() + 10 * valeur.ordinal();
	}

}
