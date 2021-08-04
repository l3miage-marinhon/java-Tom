package commandes;

import java.util.Date;
import java.util.Map;

import clients.*;
import pieces.Piece;

public class Facture {
	
	private int num;
	private String nomQuincaillerie;
	private Client client;
	private Date date;
	private Map<Piece, Integer> listePieces;
	private double prix;
		
	public Facture(int num, String nomQuincaillerie, Client client, Date date, Map<Piece, Integer> listePieces,
			double prix) {
		setNum(num);
		setNomQuincaillerie(nomQuincaillerie);
		setClient(client);
		setDate();
		setListePieces(listePieces);
		setPrix(prix);
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getNomQuincaillerie() {
		return nomQuincaillerie;
	}
	public void setNomQuincaillerie(String nomQuincaillerie) {
		this.nomQuincaillerie = nomQuincaillerie;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}
	public void setDate() {
		this.date = new Date();
	}

	public Map<Piece, Integer> getListePieces() {
		return listePieces;
	}
	public void setListePieces(Map<Piece, Integer> listePieces) {
		this.listePieces = listePieces;
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		String s = "";
		s += 	"\nNuméro facture : " + getNum()
				+ "\nDate : " + getDate()
				+ "\nClient : " + 	(getClient() instanceof Particulier ? 
									"Particulier, " + ((Particulier) getClient()).getNom() + ", " + ((Particulier) getClient()).getPrenom() :
									"Entreprise, " + ((Entreprise) getClient()).getNomCommercial() )
				+ "\nPièces :";
		
		for(Piece p : getListePieces().keySet()) {
			s += "\n   * \"" + p.getNom() + "\", \"" + p.getRef() + "\" : " + listePieces.get(p);
		}
		
		s += "\nPrix : " + (getPrix()>1 ? getPrix() + " euros" : getPrix() + " euro") ;
		return s;
	}
}
