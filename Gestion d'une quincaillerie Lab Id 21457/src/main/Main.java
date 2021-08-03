package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Entreprise;
import clients.Particulier;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;
import quincaillerie.Catalogue;
import quincaillerie.Facture;
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
	    	ArrayList<Piece> compRoueVelo = new ArrayList<>();
	    	compRoueVelo.add(new PieceDeBase("rayon", "00OF48",1,20,1));
	    	compRoueVelo.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
	    	compRoueVelo.add(new PieceDeBase("disque de jante", "00DJ42",5.5,36,2));
	    	
	    	ArrayList<Piece> compAmpoule = new ArrayList<>();
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
	    	
	    	catalogue.pieceExisteCatalogue("pneu", "00BD41", false);
	    	catalogue.pieceExisteCatalogue("jj", "00AA11", false);
	    	catalogue.pieceExisteCatalogue("roue de vélo", "01TY87", false);
	    	
	    	catalogue.affichePieceCatalogue("ampoule", "02AM33");
	    	catalogue.affichePieceCatalogue("rayon", "02AZ56");
	    	
	    	Entreprise e1 = new Entreprise("0456AE94", "30 rue des prés", "08562205190", "entrepriseE1@gmail.com", 1000,  "Paris", "Carroufino", CategorieEntreprise.PetiteMoyenneEntreprise);
			System.out.println("Entreprise 1 : \n" + e1);
			
			Particulier p1 = new Particulier("9877TY34", "12 rue marcel porte", "0629713873", "tom.frances97@yahoo.fr", 120, Civilite.MONSIEUR, "Frances", "Tom", true);
			System.out.println("\nParticulier 1 : \n" + p1); 

			Stocks stocks = new Stocks(listePieces);
	    	System.out.println(stocks);

	    	System.out.println("recherche piece ");
	    	stocks.pieceExisteStocks("pneu", "00BD41", true);
	    	System.out.println("ajoute stock piece ");
	    	stocks.ajoutePieceStocks("pneu", "00BD41", 5);
	    	System.out.println(stocks);
	    	System.out.println("recherche stocks piece");
	    	stocks.afficheStocksPiece("pneu", "00BD41");
	    	stocks.afficheStocksPiece("ddd", "00BD42");
	    	System.out.println("retire stocks piece");
	    	stocks.retirePieceStocks("disque de jante", "00DJ41", 19);
	    	System.out.println(stocks);
	    	System.out.println("suppression piece stocks");
	    	stocks.supprimePieceStocks("rayon", "00OF48");
	    	System.out.println(stocks);
	    	
	    	Date date1 = new Date();
	    	Facture fact1 = new Facture(1, date1, p1, compAmpoule);
	    	System.out.println("Facture 1 : " + fact1);
	    }
	
}
