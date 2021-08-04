package commandes;

import java.util.Date;
import java.util.Map;

import clients.Client;
import pieces.Piece;

public class Commande {
	
	private int num;
	private String nomQuinc;
	private Client client;
	private Date date;
	private Map<Piece, Integer> listePieces;
	private double prix;
	private Facture facture;
	private EtatCommande etat;
		
	public Commande(int num, String nomQuinc, Client client, Date date, Map<Piece, Integer> listePieces,
			EtatCommande etat) {
		setNum(num);
		setNomQuinc(nomQuinc);
		setClient(client);
		setDate(date);
		setListePieces(listePieces);
		setPrix(calculPrix());
		setFacture(null);
		setEtat();
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getNomQuinc() {
		return nomQuinc;
	}
	public void setNomQuinc(String nomQuinc) {
		this.nomQuinc = nomQuinc;
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

	public double calculPrix() {
		double prix = 0;
		for(Piece p : listePieces.keySet()) {
			prix += p.getPrix() * listePieces.get(p);
		}
		return prix;
	}
	
	public Facture editionFacture() {
		return new Facture(num, nomQuinc, client, new Date(), listePieces, prix);
	}
	
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
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * Passe une commande entre un client et la quincaillerie : procède à l'achat d'un ensemble de pièces par le client contre <br>
	 * de l'argent, uniquement si le client est connu de la quincaillerie, si il possède les fonds nécessaires et si la quincaillerie <br>
	 * a des stocks suffisants. <br>
	 * En particulier :<br>
	 * &emsp;&emsp;- vérifie les fonds du client et les stocks de la quincaillerie <br>
	 * &emsp;&emsp;- crée une nouvelle facture, la donne au client et à la quincaillerie<br>
	 * &emsp;&emsp;- actualise la trésorerie de la quincaillerie et les fonds du client<br>
	 * &emsp;&emsp;- actualise les stocks de la quincaillerie et les pièces possédées par le client<br>
	 * @param client {@link Client} le client passant la commande
	 * @param listePieces {@link Map} la liste des pièces que le client veut commander
	 * @return {@link Facture} renvoie la facture de la commande si cette dernière a été passée avec succès, <b>null</b> sinon
	 */
	/*
	public static void passerCommande(Quincaillerie quincaillerie, Client client, Map<Piece, Integer> listePieces) {
		Facture f = null;
		if(!quincaillerie.clientConnu(client)) {
			System.out.println("Vous devez d'abord vous enregistrer avant de passer commande");
		}else if(!client.creditSuffisant(client, listePieces)) {
			System.out.println("Vous n'avez pas assez d'argent pour effectuer cette commande");
		//ajouter condition if(!stocksSuffisants(listePieces))
		}else {
			int numFact = numFacture(quincaillerie) + 1;
			Date dateFact = new Date();
			f = new Facture(numFact, quincaillerie.getNom(), dateFact, client, listePieces);
			client.actualiseCredit(-f.getPrix());
			client.ajouterFacture(f);
			actualiseTresorerie(f.getPrix());
			ajouterFactureClient(client, f);
			System.out.println("Votre commande a bien été enregistrée");
		}
		return f;
	}
	*/
}
