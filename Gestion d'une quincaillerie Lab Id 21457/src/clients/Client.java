package clients;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.regex.*;

import commandes.Commande;
import pieces.*;
// TODO Question 24: écrire la classe Client.
// 
public abstract class Client{
	//TODO a toi de modéliser un client
	private String id;
	private String adresse;
	private String tel;
	private String email;
	private Map<Piece, Integer> piecesPossedees;
	private LinkedHashSet<Commande> historiqueCommandes; //vraiment utile ?
	private double credit;
	private int nbCommande;
	
	public Client(String id, String adresse, String tel, String email, double credit) {
		setId(id);
		setAdresse(adresse);
		setTel(tel);
		setEmail(email);
		setHistoriqueCommandes();
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
	
	public Map<Piece, Integer> getPiecesPossedees(){
		return piecesPossedees;
	}
	public void setPiecesPossedees() {
		piecesPossedees = new HashMap<>();
	}
	
	public LinkedHashSet<Commande> getHistoriqueCommandes(){
		return historiqueCommandes;
	}
	public void setHistoriqueCommandes() {
		historiqueCommandes = new LinkedHashSet<>();
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
		this.nbCommande = historiqueCommandes.size();
	}
	
	public void ajouterCommande(Commande c) {
		historiqueCommandes.add(c);
	}
	
	//public void 
	
	public void actualiseCredit(double montant) {
		setCredit(getCredit() + montant);
	}
	
	/**
	 * Vérifie si un client possède suffisement d'argent pour acheter un ensemble de pièces
	 * @param client {@link Client} le client dont on veut vérifier les fonds
	 * @param listeArticles {@link Map} la liste des pièces et nombre d'exemplaires que le client veut acheter 
	 * @return creditSuff {@link Boolean} <b>true</b> si le client possède suffisement d'argent, <b>false</b> sinon
	 */
	public boolean creditSuffisant(Client client, Map<Piece, Integer> listeArticles) {
		double prix = 0;
		boolean creditSuff = true;
		Iterator<Piece> it = listeArticles.keySet().iterator();
		while(it.hasNext() && creditSuff) {
			Piece p = it.next();
			prix += p.getPrix() * listeArticles.get(p);
			if(prix > client.getCredit()) creditSuff = false;
		}
		return creditSuff;
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