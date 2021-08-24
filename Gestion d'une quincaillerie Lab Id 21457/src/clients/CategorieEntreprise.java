package clients;

public enum CategorieEntreprise { 
	GE("Grande entreprise"), ETI("Entreprise taille intermédiaire"), PME("Petite et moyenne entreprise"), TPE("Toute petite entreprise");
	
	private String nom;
	
	private CategorieEntreprise(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
}