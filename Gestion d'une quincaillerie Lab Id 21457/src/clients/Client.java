package clients;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.*;

import quincaillerie.Facture;
// TODO Question 24: écrire la classe Client.
// 
public abstract class Client{
	//TODO a toi de modéliser un client
	private String id;
	private String adresse;
	private String tel;
	private String email;
	private Collection<Facture> listeFactures;
	private double credit;
	private int nbCommande;
	
	public Client(String id, String adresse, String tel, String email, double credit) {
		setId(id);
		setAdresse(adresse);
		setTel(tel);
		setEmail(email);
		setListeFactures();
		setCredit(credit);
		setNbCommande();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(Pattern.matches("\\d{4}[A-Z]{2}\\d{2}", id)) {
			this.id = id;
		}else {
			//exception plus tard, ou gestion directement à la saisie au clavier
			this.id = "0000AA00";
		}
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		if(Pattern.matches("0\\d{9}", tel)) {
			this.tel = tel;
		}else {
			this.tel = "0123456789";
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(Pattern.matches("[\\w_.-]+@[a-z]+.(fr|com)", email)) {
			this.email = email;
		}else {
			this.email = "test@test.fr";
		}
	}
	
	public Collection<Facture> getListeFactures(){
		return listeFactures;
	}
	public void setListeFactures() {
		listeFactures = new HashSet<>();
	}
	
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public int getNbCommande() {
		return nbCommande;
	}
	public void setNbCommande() {
		this.nbCommande = listeFactures.size();
	}
	
	public void ajouterFacture(Facture f) {
		listeFactures.add(f);
	}
	
	public void actualiseCredit(double montant) {
		setCredit(getCredit() + montant);
	}
	
	@Override
	public String toString() {
		return "ID : " + getId() 
			+ "\nAdresse : " + getAdresse()
			+ "\nTel : " + getTel()
			+ "\nEmail : " + getEmail()
			+ "\nNombre de commandes : " + getNbCommande()
			+ "\nCrédit : " + getCredit() + (getCredit()>1 ? " euros" : " euro");
	}
	
}