package quincaillerie;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import clients.Client;
import pieces.Piece;

public class Quincaillerie {
	
	private String nom;
	private double tresorerie;
	private Catalogue catalogue;
	private Stocks stocks;
	private Map<Client, Collection<Facture>> listeClientsFactures;

    public Quincaillerie(String nom, double tresorerie, Catalogue catalogue, Stocks stocks, Map<Client, Collection<Facture>> listeClientsFactures) {
		setNom(nom);
		setTresorerie(tresorerie);
		setCatalogue(catalogue);
		setStocks(stocks);
		setListeClientsFactures();
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

	public Map<Client, Collection<Facture>> getListeClientsFactures() {
		return listeClientsFactures;
	}
	public void setListeClientsFactures() {
		listeClientsFactures = new HashMap<>();
	}
	
	//retourne le numéro de la prochaine facture basé sur le nombre de facture déjà créées
	public int numFacture() {
		int n = 0;
		for(Collection<Facture> lf : listeClientsFactures.values()) {
			n += lf.size();
		}
		return n;
	}
	
	//ajoute ou retire le montant à la trésorerie (suivant le signe de montant)
	public void actualiseTresorerie(double montant) {
		setTresorerie(getTresorerie() + montant);
	}
	
	//vérifie l'existance du client dans la liste des clients et factures de la quincaillerie
	public boolean clientExiste(Client c) {
		return listeClientsFactures.containsKey(c);
	}
	
	//vérifie que le client a des fonds suffisants pour l'achat des pièces
	public boolean clientCreditSuffisant(Client c, Collection<Piece> l) {
		double prix = 0;
		for(Piece p : l) {
			prix += p.getPrix();
		}
		return c.getCredit() >= prix;
	}
	
	//ajoute un client à la liste des clients et factures de la quincaillerie
	public void ajouterClient(Client client) {
		if(!clientExiste(client)) {
			listeClientsFactures.put(client, new HashSet<>());
		}else {
			System.out.println("Client connu");
		}
	}
	
	//ajoute une facture à la liste des clients et factures de la quincaillerie pour un client donné
	public void ajouterFactureClient(Client client, Facture facture) {
		if(!clientExiste(client)) {
			System.out.println("Vous devez d'abord vous enregistrer avant de passer commande");
		}else {
			listeClientsFactures.get(client).add(facture);
		}
	}

	//effectue une commande faite par un client pour une liste de pieces
	public void passerCommande(Client client, Collection<Piece> listePieces) {
		if(!clientExiste(client)) {
			System.out.println("Vous devez d'abord vous enregistrer avant de passer commande");
		}else {
			if(!clientCreditSuffisant(client, listePieces)) {
				System.out.println("Vous n'avez pas assez d'argent pour effectuer cette commande");
			}else {
				int n = numFacture() + 1;
				Date d = new Date();
				Facture f = new Facture(n, d, client, listePieces);
				client.actualiseCredit(-f.getPrix());
				client.ajouterFacture(f);
				actualiseTresorerie(f.getPrix());
				ajouterFactureClient(client, f);
				System.out.println("Votre commande a bien été enregistrée");
			}
		}
	}
	
}
