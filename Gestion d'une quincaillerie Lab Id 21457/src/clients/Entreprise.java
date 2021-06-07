package clients;

// TODO Question 27: écrire la classe Entreprise.
// 
public class Entreprise extends Client{
	protected String SIRET ; 
	
	public Entreprise(String nom, String adressePostal, String adresseElectronique, Civilite civilite, String SIRET) {
		super(nom, adressePostal, adresseElectronique, civilite);
		this.SIRET=SIRET;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	
}