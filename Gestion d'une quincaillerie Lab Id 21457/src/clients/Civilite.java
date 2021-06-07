package clients;

// TODO Questioin 23: Définir l'énumération Civlite.
// 
public enum Civilite{
	M("Monsieur"),Mme("Madame"),Mlle("Mademoiselle");
	
	private String denomination;
		
	Civilite(String s){
		denomination=s;
	}
	
	@Override
	public String toString() {
		return denomination;
	}
	
}