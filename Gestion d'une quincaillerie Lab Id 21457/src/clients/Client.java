package clients;

import java.util.regex.*;
// TODO Question 24: écrire la classe Client.
// 
public abstract class Client{
	//TODO a toi de modéliser un client
	private String id;
	private String adresse;
	private String tel;
	private String email;
	private static int nbCommande;
	
	public Client(String id, String adresse, String tel, String email) {
		setId(id);
		setAdresse(adresse);
		setTel(tel);
		setEmail(email);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(Pattern.matches("\\d{4}[A-Z]{2}\\d{2}", id)) {
			this.id = id;
		}else {
			//exception plus tard
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
	
	public int getNbCommande() {
		return nbCommande;
	}
	
	@Override
	public String toString() {
		return "ID : " + getId() 
			+ "\nAdresse : " + getAdresse()
			+ "\nTel : " + getTel()
			+ "\nEmail : " + getEmail()
			+ "\nNombre de commandes : " + getNbCommande();
	}
	
}