package commandes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import clients.Client;
import clients.Entreprise;
import clients.Particulier;
import pieces.Piece;

public class Commande {
	
	private int num;
	private String nomQuincaillerie;
	private Client client;
	private Date date;
	private Map<Piece, Integer> listePieces;
	private double prix;
	private Facture facture;
	private EtatCommande etat;
		
	public Commande(int num, String nomQuincaillerie, Client client, Date date, Map<Piece, Integer> listePieces, double prixCommande) {
		setNum(num);
		setNomQuincaillerie(nomQuincaillerie);
		setClient(client);
		setDate(date);
		setListePieces(listePieces);
		setPrix(prixCommande);
		setFacture(null);
		setEtat();
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

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.date);
	}
	public void setDate(Date date) {
		this.date = date;
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

	public Facture getFacture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
	public EtatCommande getEtat() {
		return etat;
	}
	public void setEtat() {
		etat = EtatCommande.Acceptation;
	}	
	
	/**
	 * Crée une facture basée sur les informations de la commande
	 * @return {@link Facture} la facture éditée
	 */
	public Facture editionFacture() {
		return new Facture(num, nomQuincaillerie, client, new Date(), listePieces, prix);
	}
	
	/**
	 * Avance l'état de la commande suivant l'ordre Acceptation / Preparation / Livraison / Terminee,
	 * et édite la facture une fois l'état Livraison atteint.
	 */
	public void avancerEtat() {
		switch(etat) {
		case Acceptation:
			etat = EtatCommande.Preparation;
			break;
		case Preparation:
			etat = EtatCommande.Livraison;
			setFacture(editionFacture());
			break;
		case Livraison:
			etat = EtatCommande.Terminee;
			this.client.ajoutePiecePossedees(this.listePieces);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Vérifie si la commande est annulable. Une commande est annulable si elle est à l'état Acceptation ou Préparation.
	 * @return {@link Boolean} true si la facture est annulable, false sinon.
	 */
	public boolean estAnnulable() {
		return getEtat() == EtatCommande.Acceptation || getEtat() == EtatCommande.Preparation;
	}
	
	/**
	 * Annule une commade d'un client suivant son numéro de commande, c'est à dire qu'elle est supprimée de la liste des clients et commandes de la quincaillerie
	 * @param client {@link Client} le client dont on souhaite cherche la commande
	 * @param numCommande {@link Integer} le numéro de la commande recherchée
	 * @return {@link Boolean} true si la commande a été annulée, false sinon
	 */
	public boolean annulerCommande() {
		boolean annule = false;
		if(!this.estAnnulable()) {
			System.out.println("Vous ne pouvez plus annuler votre commande");
		}else {
			System.out.println("Commande annulée");
			this.etat = EtatCommande.Annulee;
			annule = true;
		}
		return annule;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += 	"\nNuméro commande : " + getNum();
		if(getEtat() == EtatCommande.Livraison || getEtat() == EtatCommande.Terminee) s += "\nFacture : " + getFacture().getNum();
				
		s += "\nMagasin : " + getNomQuincaillerie()
			+ "\nDate : " + getDate()
			+ "\nClient : " + 	(getClient() instanceof Particulier ? 
								"Particulier, " + ((Particulier) getClient()).getNom() + ", " + ((Particulier) getClient()).getPrenom() :
								"Entreprise, " + ((Entreprise) getClient()).getNomCommercial() )
			+ "\nPièces :";
		
		for(Piece p : getListePieces().keySet()) {
			s += "\n   * \"" + p.getNom() + "\", \"" + p.getRef() + "\" : " + listePieces.get(p);
		}
		
		s += "\nPrix : " + String.format("%.2f", getPrix()) + (getPrix()>1 ? " euros" : " euro") 
			+ "\nEtat commande : " + getEtat();
		return s;
	}
}
