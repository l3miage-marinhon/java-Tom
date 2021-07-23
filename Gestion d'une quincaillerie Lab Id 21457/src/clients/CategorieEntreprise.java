package clients;

public enum CategorieEntreprise { 
	GrandeEntreprise("GE"), EntrepriseTailleIntermediaire("ETI"), PetiteMoyenneEntreprise("PME"), ToutePetiteEntreprise("TPE");
	
	private String nom;
	
	private CategorieEntreprise(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
}