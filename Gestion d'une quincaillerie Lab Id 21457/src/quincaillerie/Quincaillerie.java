package quincaillerie;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import clients.Client;
import commandes.Commande;
import commandes.Facture;
import pieces.Piece;

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
	private int numCommande(Quincaillerie quinc) {
		int nb = 0;
		for(LinkedHashSet<Commande> listesCommandes : listeClientsCommandes.values()) {
			nb += listesCommandes.size();
		}
		return nb;
	}
	
	/**
	 * Actualise le montant de la trésorerie de la quincaillerie, plus ou moins
	 * @param montant à ajouter ou soustraire (suivant le signe) à la trésorerie
	 */
	public void actualiseTresorerie(double montant) {
		setTresorerie(getTresorerie() + montant);
	}
	
	/**
	 * Vérifie l'existence d'un client dans la liste des clients de la quincaillerie
	 * @param client {@link Client} dont on veut vérifier l'existence
	 * @return boolean <b>true</b> si le client est connu de la quincaillerie, <b>false</b> sinon
	 */
	public boolean clientConnu(Client client) {
		return listeClientsCommandes.containsKey(client);
	}
	
	/**
	 * Vérifie si la quincaillerie possède suffisement de stocks de chaque pièces de la liste donnée en paramètre
	 * @param listeArticles {@link Map} la liste des pièces et nombre d'exemplaires de chaque pièces
	 * @return stockSuff {@link Boolean} <b>true</b> si la quincaillerie a des stocks suffisants, <b>false</b> sinon
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
	 * Ajoute un client dans la listeClientsFactures de la quincaillerie, et crée un set vide pour ses factures
	 * @param client {@link Client} le client à ajouter
	 */
	public void ajouterClient(Client client) {
		if(!clientExiste(client)) {
			listeClientsFactures.put(client, new HashSet<>());
		}else {
			System.out.println("Client connu");
		}
	}
	
	/**
	 * Ajoute une facture à la liste des factures du client passé en paramètre dans la listeClientsFactures de la quincaillerie, uniquement
	 * si le client est déjà connu de lad quincaillerie <br>
	 * &emsp;&emsp;- ndjhd df <br>
	 * &emsp;&emsp;- <br>
	 * &emsp;&emsp;- <br>
	 * @param client {@link Client} le client auquel on ajoute la facture
	 * @param facture {@link Facture} la facture à ajouter au client
	 */
	public void ajouterFactureClient(Client client, Facture facture) {
		if(!clientExiste(client)) {
			System.out.println("Vous devez d'abord vous enregistrer avant de passer commande");
		}else {
			listeClientsFactures.get(client).add(facture);
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
	public Facture passerCommande(Client client, Map<Piece, Integer> listePieces) {
		Facture f = null;
		if(!clientExiste(client)) {
			System.out.println("Vous devez d'abord vous enregistrer avant de passer commande");
		}else if(!clientCreditSuffisant(client, listePieces)) {
			System.out.println("Vous n'avez pas assez d'argent pour effectuer cette commande");
		//ajouter condition if(!stocksSuffisants(listePieces))
		}else {
			int n = numFacture() + 1;
			Date d = new Date();
			f = new Facture(n, d, client, listePieces);
			client.actualiseCredit(-f.getPrix());
			client.ajouterFacture(f);
			actualiseTresorerie(f.getPrix());
			ajouterFactureClient(client, f);
			System.out.println("Votre commande a bien été enregistrée");
		}
		return f;
	}
	
}
