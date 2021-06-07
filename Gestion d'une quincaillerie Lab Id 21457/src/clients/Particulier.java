package clients;

// TODO Question 26: écrire la classe Particulier.
// 
public class Particulier extends Client{
	
	protected String prenom;
	
	public Particulier(String nom, String adressePostal, String adresseElectronique, Civilite civilite, String prenom) {
		super(nom, adressePostal, adresseElectronique, civilite);
		this.prenom=prenom;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "";
	}

	
}