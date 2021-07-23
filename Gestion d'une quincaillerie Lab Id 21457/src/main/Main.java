package main;

import java.util.ArrayList;

import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Entreprise;
import clients.Particulier;
import quincaillerie.Catalogue;
import quincaillerie.Piece;
import quincaillerie.PieceCompositeEnKit;
import quincaillerie.PieceCompositeMontee;
import quincaillerie.PieceDeBase;

public class Main {
	
	public static void main(String[] args) {
	    
	    	ArrayList<Piece> compRoueVelo = new ArrayList<>();
	    	compRoueVelo.add(new PieceDeBase("rayon", "00OF48",1,20,1));
	    	compRoueVelo.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
	    	compRoueVelo.add(new PieceDeBase("disque de jante", "00DJ42",5.5,36,2));
	    	
	    	ArrayList<Piece> compAmpoule = new ArrayList<>();
	    	compAmpoule.add(new PieceDeBase("verre", "00KF48",2,24,1));
	    	compAmpoule.add(new PieceDeBase("filament", "00FF92",5,24,1));
	    	
	    	ArrayList<Piece> listePieces = new ArrayList<>();
	    	listePieces.add(new PieceDeBase("pneu", "00BD41",12.5,60,2));
	    	listePieces.add(new PieceDeBase("chambre à air", "00AA65",4.0,20,2));
	    	listePieces.add(new PieceDeBase("disque de jante", "00DJ41",4.5,60,2));
	    	listePieces.add(new PieceDeBase("rayon", "00OF48",1,20,1));
	    	listePieces.add(new PieceDeBase("rayon", "00OF49",4,23,2));
	    	listePieces.add(new PieceCompositeEnKit("roue de vélo", "01TY87", compRoueVelo, 2));
	    	listePieces.add(new PieceCompositeMontee("ampoule", "02AM33", compAmpoule, 2, 5));
	    	
	    	Catalogue catalogue = Catalogue.creationCatalogue(listePieces);

	    	System.out.println(catalogue);
	    	
	    	catalogue.pieceExiste("pneu", "00BD41", true);
	    	catalogue.pieceExiste("jj", "00AA11", true);
	    	catalogue.pieceExiste("roue de vélo", "01TY87", true);
	    	
	    	catalogue.affichePiece("ampoule", "02AM33");
	    	catalogue.affichePiece("rayon", "02AZ56");
	    	
	    	Entreprise e1 = new Entreprise("0456AE94", "30 rue des prés", "08562205190", "entrepriseE1@gmail.com", "Paris", "Carroufino", CategorieEntreprise.PetiteMoyenneEntreprise);
			System.out.println("Entreprise 1 : \n" + e1);
			
			Particulier p1 = new Particulier("9877TY34", "12 rue marcel porte", "0629713873", "tom.frances97@yahoo.fr", Civilite.MONSIEUR, "Frances", "Tom", true);
			System.out.println("\nParticulier 1 : \n" + p1); 

	    }
}
