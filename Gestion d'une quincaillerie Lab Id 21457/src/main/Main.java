package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Entreprise;
import clients.Particulier;
import commandes.Commande;
import commandes.Facture;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;
import quincaillerie.Catalogue;
import quincaillerie.Quincaillerie;
import quincaillerie.Stocks;

public class Main {
	
	public static void main(String[] args) {
	    /* TODO Question 3: Afficher les caractéristiques de pneu, chambre à air e, disque de jante et rayon
	 Question 6: Afficher les fiches caractéristiques des 2 piièces composites jantes en kit et brouette en kit décrites dans l'énoncé.
	 //une jante en kit est constituer d'un disque de jante et de 3 Rayon
	 //et une brouette montée et constituer d'un pneu , d'une jante en kit et d'une chambre a air
	 Question 14: Afficher les fiches caractéristiques des 2 piièces composites jantes montée et brouette montée décrites dans l'énoncé.
	 Question 27: Ajouter un client particulier et une entreprise et afficher le résultat.*/
	    	//tu peut ici remplacer avec des Collections si tu trouve ça plus simple 
	    	ArrayList<PieceDeBase> compRoueVelo = new ArrayList<>();
	    	compRoueVelo.add(new PieceDeBase("rayon", "00OF48",1,20,1));
	    	compRoueVelo.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
	    	compRoueVelo.add(new PieceDeBase("disque de jante", "00DJ42",5.5,36,2));
	    	
	    	ArrayList<PieceDeBase> compAmpoule = new ArrayList<>();
	    	compAmpoule.add(new PieceDeBase("verre", "00KF48",2,24,1));
	    	compAmpoule.add(new PieceDeBase("filament", "00FF92",5,24,1));
	    	
	    	Set<Piece> listePieces = new HashSet<Piece>();
	    	listePieces.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
	    	listePieces.add(new PieceDeBase("chambre à air", "00AA65",4.0,20,2));
	    	listePieces.add(new PieceDeBase("disque de jante", "00DJ41",4.5,60,2));
	    	listePieces.add(new PieceDeBase("rayon", "00OF48",1,20,1));
	    	listePieces.add(new PieceDeBase("rayon", "00OF49",4,23,2));
	    	listePieces.add(new PieceCompositeEnKit("roue de vélo", "01TY87", compRoueVelo, 2));
	    	listePieces.add(new PieceCompositeMontee("ampoule", "02AM33", compAmpoule, 2, 5));
	    	
	    	Catalogue catalogue = new Catalogue(listePieces);
	    	System.out.println(catalogue);
	    	
	    	catalogue.pieceExiste("pneu", "00BD41", false);
	    	catalogue.pieceExiste("jj", "00AA11", false);
	    	catalogue.pieceExiste("roue de vélo", "01TY87", false);
	    	
	    	catalogue.affichePieceCatalogue("ampoule", "02AM33");
	    	catalogue.affichePieceCatalogue("rayon", "02AZ56");
	    	
	    	Entreprise e1 = new Entreprise("0456AE94", "30 rue des prés", "08562205190", "entrepriseE1@gmail.com", 1000,  "Paris", "Carroufino", CategorieEntreprise.PME);
			System.out.println("Entreprise 1 : \n" + e1);
			
			Particulier p1 = new Particulier("9877TY34", "12 rue marcel porte", "0629713873", "tom.frances97@yahoo.fr", 120, Civilite.MONSIEUR, "Frances", "Tom", true);
			System.out.println("\nParticulier 1 : \n" + p1); 

			Stocks stocks = new Stocks(listePieces);
	    	System.out.println(stocks);

	    	System.out.println("recherche piece ");
	    	System.out.println("ajoute stock piece ");
	    	//stocks.ajoutePiece("pneu", "00BD41", 5);
	    	System.out.println(stocks);
	    	System.out.println("recherche stocks piece");
	    	//stocks.afficheStocksPiece("pneu", "00BD41");
	    	//stocks.afficheStocksPiece("ddd", "00BD42");
	    	System.out.println("retire stocks piece");
	    	//stocks.retirePieceStocks("disque de jante", "00DJ41", 19);
	    	System.out.println(stocks);
	    	System.out.println("suppression piece stocks");
	    	//stocks.supprimePieceStocks("rayon", "00OF48");
	    	System.out.println(stocks);
	    	
	    	Quincaillerie leroyMaerlin = new Quincaillerie("LeroyMaerlin", 1000, catalogue, stocks, new HashMap<>());
	    	//Date date1 = new Date();
	    	//Facture fact1 = new Facture(1, date1, p1, compAmpoule);
	    	//System.out.println("Facture 1 : " + fact1);
	    	Map<Piece, Integer> listeachat = new HashMap<>();
	    	listeachat.put(catalogue.pieceExiste("ampoule", "02AM33", false), 2);
	    	listeachat.put(catalogue.pieceExiste("pneu", "00BD41", false), 4);
	    	
	    	leroyMaerlin.ajouterClient(p1);
	    	if(leroyMaerlin.creationCommande(p1, listeachat) != null) System.out.println("Commande passée avec succès");
	    	//Commande commande1 = new Commande(1, "AAA", p1, new Date(), listeachat, 0);
	    	leroyMaerlin.rechercheCommandeClient(p1, 1).avancerEtat();
	    	leroyMaerlin.rechercheCommandeClient(p1, 1).avancerEtat();
	    	leroyMaerlin.rechercheCommandeClient(p1, 1).avancerEtat();
	    	System.out.println("Commande 1 : " + leroyMaerlin.rechercheCommandeClient(p1, 1));
	    	System.out.println("\nFacture 1 : " + leroyMaerlin.rechercheCommandeClient(p1, 1).getFacture());
	    	System.out.println(p1);
	    }
	
	
	
	
	
	
	/*
	public void initialize() {
		ArrayList<PieceDeBase> compRoueVelo = new ArrayList<>();
    	compRoueVelo.add(new PieceDeBase("rayon", "00OF48",1,20,1));
    	compRoueVelo.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
    	compRoueVelo.add(new PieceDeBase("disque de jante", "00DJ42",5.5,36,2));
    	
    	ArrayList<PieceDeBase> compAmpoule = new ArrayList<>();
    	compAmpoule.add(new PieceDeBase("verre", "00KF48",2,24,1));
    	compAmpoule.add(new PieceDeBase("filament", "00FF92",5,24,1));
    	
    	ArrayList<PieceDeBase> compPommeauDouche = new ArrayList<>();
    	compPommeauDouche.add(new PieceDeBase("jet", "00JT24", 3, 24, 3));
    	compPommeauDouche.add(new PieceDeBase("joint", "00JN28", 0.5, 24, 1));
    	compPommeauDouche.add(new PieceDeBase("molette", "00ML02", 2, 24, 2));
    	
    	Set<Piece> listePieces = new HashSet<Piece>();
    	listePieces.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
    	listePieces.add(new PieceDeBase("chambre à air", "00AA65",4.0,20,2));
    	listePieces.add(new PieceDeBase("disque de jante", "00DJ41",4.5,60,2));
    	listePieces.add(new PieceDeBase("rayon", "00OF48",1,20,1));
    	listePieces.add(new PieceDeBase("rayon", "00OF49",4,23,2));
    	listePieces.add(new PieceDeBase("vis", "00OVS01",0.1,24,1));
    	listePieces.add(new PieceCompositeEnKit("roue de vélo", "01TY87", compRoueVelo, 2));
    	listePieces.add(new PieceCompositeMontee("ampoule", "02AM33", compAmpoule, 2, 5));
    	listePieces.add(new PieceCompositeMontee("pommeau de douche", "02PD77", compPommeauDouche, 1, 2.5));
    	
    	Catalogue catalogue = new Catalogue(listePieces);
    	Stocks stocks = new Stocks(listePieces);
    	
		quincaillerie = new Quincaillerie("Ma quincaillerie", 1000, catalogue, stocks, new HashMap<>());
	}
	*/
}
