package clients;

// TODO Question 24: écrire la classe Client.
// 
public abstract class Client{
	protected String nom;
	protected String adressePostal;
	protected String adresseElectronique;
	Civilite civilite;
	
	public Client(String nom, String adressePostal, String adresseElectronique, Civilite civilite) {
		this.nom=nom;
		this.adressePostal=adressePostal;
		this.adresseElectronique=adresseElectronique;
		this.civilite=civilite;
		
	}

}