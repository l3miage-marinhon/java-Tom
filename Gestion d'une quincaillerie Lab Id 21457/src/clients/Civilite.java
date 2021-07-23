package clients;

// TODO Questioin 23: Définir l'énumération Civlite.
// 
public enum Civilite { 
	MADAME("Mme."), MONSIEUR("M.");
	
	private String nom;
	
	private Civilite(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}