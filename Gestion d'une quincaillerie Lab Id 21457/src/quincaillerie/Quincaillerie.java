package quincaillerie;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;

import clients.Client;
import commandes.Commande;
import commandes.Facture;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;
import servicesBancaires.ServiceBancaire;

public class Quincaillerie {
	
	private String nom;
	private double tresorerie;
	private Catalogue catalogue;
	private Stocks stocks;
	private Map<Client, LinkedHashSet<Commande>> listeClientsCommandes;

    public Quincaillerie(String nom, double tresorerie, Catalogue catalogue, Stocks stocks, Map<Client, Collection<Facture>> listeClientsFactures) {
		setNom(nom);
		setTresorerie(tresorerie);
		setCatalogue(catalogue);
		setStocks(stocks);
		setListeClientsCommandes();
	}
    
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getTresorerie() {
		return tresorerie;
	}
	public void setTresorerie(double tresorerie) {
		this.tresorerie = tresorerie;
	}
	
	public Catalogue getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public Stocks getStocks() {
		return stocks;
	}
	public void setStocks(Stocks stocks) {
		this.stocks = stocks;
	}

	public Map<Client, LinkedHashSet<Commande>> getListeClientsCommandes() {
		return listeClientsCommandes;
	}
	public void setListeClientsCommandes() {
		listeClientsCommandes = new HashMap<>();
	}

	/**
	 * Retourne le numéro de la prochaine facture à créer, basé sur le nombre de factures déjà éditées
	 * @return int le numéro de la prochaine facture
	 */
	private int numCommande() {
		int nb = 0;
		for(LinkedHashSet<Commande> listesCommandes : listeClientsCommandes.values()) {
			nb += listesCommandes.size();
		}
		return nb+1;
	}
	
	public String refNouveauClient(Boolean part) {
		int nb = listeClientsCommandes.keySet().size() + 1;
		String ref = "";
		if(nb>0 && nb<1000) ref += String.format("%04d", nb);
		if(part) {
			ref += "PA";
		}else {
			ref += "EN";
		}
		Random rn = new Random();
		int r = rn.nextInt(100);
		ref += String.format("%02d", r);
		System.out.println("ref " + ref);
		return ref;
	}
	
	public boolean mailDisponible(String mail) {
		boolean disp = true;
		Iterator<Client> it = listeClientsCommandes.keySet().iterator();
		while(it.hasNext() && disp) {
			if(mail.equals(it.next().getEmail())) disp = false;
		}
		return disp;
	}
	
	/**
	 * Calcule le prix de la commande en fonctions des pièces et du nombre d'exemplaires qui la composent
	 * @param listePiecesExemplaires {@link Map} la liste des pièces qui composent la commande
	 * @return {@link Double} le prix de la commande
	 */
	public double calculPrixCommande(Map<Piece, Integer> listePiecesExemplaires) {
		double prix = 0;
		for(Piece p : listePiecesExemplaires.keySet()) {
			if(p instanceof PieceCompositeEnKit) {
				prix += ((PieceCompositeEnKit) p).getPrix() * listePiecesExemplaires.get(p);
			}else if(p instanceof PieceCompositeMontee) {
				prix += ((PieceCompositeMontee) p).getPrix() * listePiecesExemplaires.get(p);
			}else if(p instanceof PieceDeBase) {
				prix += ((PieceDeBase) p).getPrix() * listePiecesExemplaires.get(p);
			}
		}
		return prix;
	}
	
	/**
	 * Vérifie l'existence d'un client dans la liste des clients de la quincaillerie
	 * @param client {@link Client} dont on veut vérifier l'existence
	 * @return {@link Boolean} true si le client est connu de la quincaillerie, false sinon
	 */
	public boolean clientConnu(Client client) {
		return listeClientsCommandes.containsKey(client);
	}
	
	/**
	 * Vérifie si la quincaillerie possède suffisement de stocks de chaque pièces de la liste donnée en paramètre
	 * @param listeArticles {@link Map} la liste des pièces et nombre d'exemplaires de chaque pièces
	 * @return stockSuff {@link Boolean} true si la quincaillerie a des stocks suffisants, false sinon
	 */
	public boolean stocksSuffisants(Map<Piece, Integer> listeArticles) {
		boolean stockSuff = true;
		Iterator<Piece> it = listeArticles.keySet().iterator();
		while(it.hasNext() && stockSuff) {
			Piece p = it.next();
			if(stocks.stocksPiece(p) < listeArticles.get(p)) stockSuff = false;
		}
		return stockSuff;
	}
	
	/**
	 * Ajoute un client dans la listeClientsCommandes de la quincaillerie, et crée un set vide pour ses commandes
	 * @param client {@link Client} le client à ajouter
	 */
	public void ajouterClient(Client client) {
		if(listeClientsCommandes.putIfAbsent(client, new LinkedHashSet<>()) != null){
			System.out.println("Client déjà connu");
		}
	}
	
	/**
	 * Ajoute une commande à la liste des commandes du client passé en paramètre dans la listeClientsCOmmandes de la quincaillerie, uniquement
	 * si le client est déjà connu de la quincaillerie 
	 * @param client {@link Client} le client auquel on ajoute la commande
	 * @param commande {@link Commande} la commande à ajouter au client
	 */
	public void ajouterCommandeClient(Client client, Commande commande) {
		listeClientsCommandes.get(client).add(commande);
	}
	
	/**
	 * Recherche une commande d'un client suivant son numéro de commande
	 * @param client {@link Client} le client dont on souhaite cherche la commande
	 * @param numCommande {@link Integer} le numéro de la commande recherchée
	 * @return {@link Boolean} true si la commande a été trouvée, false sinon
	 */
	public Commande rechercheCommandeClient(Client client, int numCommande) {
		Commande commande = null;
		Iterator<Commande> it = listeClientsCommandes.get(client).iterator();
		while(it.hasNext() && commande == null) {
			Commande cInt = it.next();
			if(cInt.getNum() == numCommande) {
				commande = cInt;
			}
		}
		return commande;
	}
	
	/**
	 * Annule une commade d'un client suivant son numéro de commande, c'est à dire qu'elle est supprimée de la liste des clients et commandes de la quincaillerie
	 * @param client {@link Client} le client dont on souhaite cherche la commande
	 * @param numCommande {@link Integer} le numéro de la commande recherchée
	 * @return {@link Boolean} true si la commande a été annulée, false sinon
	 */
	public boolean annulerCommande(Client client, int numCommande) {
		boolean annule = false;
		Commande commande = rechercheCommandeClient(client, numCommande);
		if(commande == null) {
			System.out.println("Commande introuvable");
		}else if(!commande.estAnnulable()) {
			System.out.println("Vous ne pouvez plus annuler votre commande");
		}else {
			listeClientsCommandes.get(client).remove(commande);
			System.out.println("Commande annulée");
			annule = true;
		}
		return annule;
	}
	
	/**
	 * Crée une commande pour un client. Une commande ne peut être passée que sous 3 conditions:<br>
	 * &emsp; * le client est connu de la quincaillerie<br>
	 * &emsp; * la quincaillerie possède suffisement de stocks pour couvrir la commande<br>
	 * &emsp; * le client a suffisement de crédit pour acheter les pièces de la commande<br>
	 * @param client {@link Client} le client qui passe la commande
	 * @param listePiecesExemplaires {@link Map} la liste des pièces et leur nombre d'exemplaires que le client veut acheter
	 * @return {@link Commande} null si la commande n'a pas pu être passée, la commande créée sinon
	 */
	public Commande creationCommande(Client client, Map<Piece, Integer> listePiecesExemplaires) {
		Commande commande = null;
		double prixCommande = calculPrixCommande(listePiecesExemplaires);
		if(!clientConnu(client)) {
			System.out.println("Vous devez vous enregistrer avant de passer commande");
		}else if(!stocksSuffisants(listePiecesExemplaires)) {
			System.out.println("Stocks insuffisants");
		}else if(ServiceBancaire.prelevementCreditClient(client, prixCommande)) {
			commande = new Commande(numCommande(), this.getNom(), client, new Date(), listePiecesExemplaires, prixCommande);
			ajouterCommandeClient(client, commande);
			ServiceBancaire.approvisionneTresorerieQuincaillerie(this, prixCommande);
		}
		return commande;
	}
	
}
