package main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Interface.MenuPrincipal;
import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Client;
import clients.Entreprise;
import clients.Panier;
import clients.Particulier;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;
import quincaillerie.Catalogue;
import quincaillerie.Quincaillerie;
import quincaillerie.Stocks;


public class Application {
	
	public static Quincaillerie quincaillerie = initialize();
	public static Client clientCourant = null;
	public static Panier panier = new Panier();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new MenuPrincipal());
		
	}
	
	public Application() {
		initialize();
	}
	
	private static Quincaillerie initialize() {
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
    	
    	Quincaillerie quinc = new Quincaillerie("MaQuincaillerieGrenoble", 1000, catalogue, stocks, new HashMap<>());
    	Particulier p = new Particulier("0001PA54", "12 rue Marcel Porte", "0629713873", "tom@yahoo.fr", 120, Civilite.MONSIEUR, "Frances", "Tom", true); 
    	Entreprise e = new Entreprise("0002EN12", "30 rue Fernand Leger", "0623547899", "resto@gmail.com", 1000, "Grenoble", "O'Resto", CategorieEntreprise.TPE);
    	quinc.ajouterClient(p);
    	quinc.ajouterClient(e);
    	
    	return quinc;
	}
	
	public static JPanel version() {
		JPanel version = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		version.add(new JLabel("Version : Alpha-1")); 
		return version;
	}
}
