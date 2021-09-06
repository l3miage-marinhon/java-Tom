package clients;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

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
	private double credit;
	
	public Client(String id, String adresse, String tel, String email, double credit) {
		setId(id);
		setAdresse(adresse);
		setTel(tel);
		setEmail(email);
		setCredit(credit);
		setPiecesPossedees();
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
	
	public void ajoutePiecePossedees(Map<Piece, Integer> map) {
		for(Piece p : map.keySet()) {
			piecesPossedees.put(p, map.get(p));
		}
	}
	
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	

	@Override
	public String toString() {
		return "ID : " + getId() 
			+ "\nAdresse : " + getAdresse()
			+ "\nTel : " + getTel()
			+ "\nEmail : " + getEmail()
			+ "\nCrédit : " + getCredit() + (getCredit()>1 ? " euros" : " euro");
	}
	
}