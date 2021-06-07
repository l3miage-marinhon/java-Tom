package bataille;

public class Carte implements Comparable<Carte> {
	private Valeur valeur;
	private Couleur couleur;
	public Carte(Couleur couleur, Valeur valeur) {
		// TODO Auto-generated constructor stub
		this.couleur=couleur;
		this.valeur=valeur;
	}
	
	public String getFileName() {
		return valeur.getNom()+"-de-"+couleur.getNom()+".png";
		
	}
	
	@Override
	public String toString() {
		return valeur.getNom()+" de "+couleur.getNom();
	}
	@Override
	public boolean equals(Object o) {
		boolean equals=false;
			if(o instanceof Carte) {
				Carte carte=(Carte)o;
				equals=(carte.valeur==valeur && carte.couleur==couleur);
			}
		return equals;
	}
	@Override
	public int hashCode() {
		 int code = 0;
		 code = couleur.ordinal() + 10 * valeur.ordinal();
		  return code;
	}

	@Override
	public int compareTo(Carte carte) {
		// TODO Auto-generated method stub
		return valeur.compareTo(carte.valeur);
	}

}
