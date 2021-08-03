package clients;

// TODO Question 27: écrire la classe Entreprise.
// 
public class Entreprise extends Client{
	private String siegeSocial;
	private String nomCommercial;
	private CategorieEntreprise categorie;
	
	public Entreprise(String id, String adresse, String tel, String email, double credit, String siegeSocial, String nomCommercial,
			CategorieEntreprise categorie) {
		super(id, adresse, tel, email, credit);
		setSiegeSocial(siegeSocial);;
		setNomCommercial(nomCommercial);
		setCategorie(categorie);
	}
	
	public String getSiegeSocial() {
		return siegeSocial;
	}
	public void setSiegeSocial(String siegeSocial) {
		this.siegeSocial = siegeSocial;
	}
	
	public String getNomCommercial() {
		return nomCommercial;
	}
	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}
	
	public CategorieEntreprise getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieEntreprise categorie) {
		this.categorie = categorie;
	}
	
	@Override
	public String toString() {
		return super.toString() 
			+ "\nSiege social : " + getSiegeSocial() 
			+ "\nNom commercial : " + getNomCommercial()
			+ "\nCatégorie : " + getCategorie();
	}
}