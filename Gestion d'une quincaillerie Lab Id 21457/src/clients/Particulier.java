package clients;

// TODO Question 26: écrire la classe Particulier.
// 
public class Particulier extends Client{
	private Civilite civilite;
	private String nom;
	private String prenom;
	private boolean fidelite;
	
	public Particulier(String id, String adresse, String tel, String email, Civilite civilite, String nom,
			String prenom, boolean fidelite) {
		super(id, adresse, tel, email);
		setCivilite(civilite);;
		setNom(nom);;
		setPrenom(prenom);
		setFidelite(fidelite);
	}
	
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public boolean isFidelite() {
		return fidelite;
	}
	public void setFidelite(boolean fidelite) {
		this.fidelite = fidelite;
	}

	@Override
	public String toString() {
		return super.toString() 
			+ "\nCivilite : " + getCivilite()
			+ "\nNom : " + getNom()
			+ "\nPrenom : " + getPrenom()
			+ "\nFidelite : " + (isFidelite() ? "oui" : "non");
	}
}