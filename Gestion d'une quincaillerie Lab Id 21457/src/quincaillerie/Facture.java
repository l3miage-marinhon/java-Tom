package quincaillerie;

import java.util.Collection;
import java.util.Date;

import clients.*;
import pieces.Piece;

public class Facture {
	
	private int num;
	private Date date;
	private Client client;
	private Collection<Piece> listePieces;
	private double prix;
	
	public Facture(int num, Date date, Client client, Collection<Piece> listePieces) {
		setNum(num);
		setDate(date);
		setClient(client);
		setListePieces(listePieces);
		setPrix();
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Piece> getListePieces() {
		return listePieces;
	}
	public void setListePieces(Collection<Piece> listePieces) {
		this.listePieces = listePieces;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix() {
		this.prix = calculPrix();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public  double calculPrix() {
		double prix = 0;
		for(Piece p : listePieces) {
			prix += p.getPrix();
		}
		return prix;
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
		for(Piece p : getListePieces()) {
			s += "\n   * \"" + p.getNom() + "\", \"" + p.getRef() + "\"";
		}
		s += "\nPrix : " + (getPrix()>1 ? getPrix() + " euros" : getPrix() + " euro") ;
		return s;
	}
}
