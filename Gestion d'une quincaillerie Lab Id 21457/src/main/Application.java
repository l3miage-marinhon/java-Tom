package main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
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
		Map<PieceDeBase, Integer> compRoueVelo = new HashMap<>();
    	compRoueVelo.put(new PieceDeBase("rayon", "00OF48",1,20,1), 20);
    	compRoueVelo.put(new PieceDeBase("pneu", "00BD41",12.5,60,2), 1);
    	compRoueVelo.put(new PieceDeBase("disque de jante", "00DJ42",5.5,36,2), 1);
    	
    	Map<PieceDeBase, Integer> compAmpoule = new HashMap<>();
    	compAmpoule.put(new PieceDeBase("verre", "00KF48",2,24,1), 1);
    	compAmpoule.put(new PieceDeBase("filament", "00FF92",5,24,1), 1);
    	
    	Map<PieceDeBase, Integer> compPommeauDouche = new HashMap<>();
    	compPommeauDouche.put(new PieceDeBase("jet", "00JT24", 3, 24, 3), 1);
    	compPommeauDouche.put(new PieceDeBase("joint", "00JN28", 0.5, 24, 1), 1);
    	compPommeauDouche.put(new PieceDeBase("molette", "00ML02", 2, 24, 2), 1);
    	
    	Set<Piece> listePieces = new HashSet<Piece>();
    	listePieces.add(new PieceDeBase("pneu", "00PN01",12.5,60,2));
    	listePieces.add(new PieceDeBase("chambre à air", "00CA01",4.0,20,2));
    	listePieces.add(new PieceDeBase("disque de jante", "00DJ01",4.5,60,2));
    	listePieces.add(new PieceDeBase("rayon", "00RA01",1,20,1));
    	listePieces.add(new PieceDeBase("rayon", "00RA02",4,23,2));
    	listePieces.add(new PieceDeBase("vis", "00VI01",0.1,24,1));
    	listePieces.add(new PieceDeBase("vis", "00VI02",0.2,36,1));
    	listePieces.add(new PieceDeBase("vis", "00VI03",0.15,12,2));
    	listePieces.add(new PieceCompositeEnKit("roue de vélo", "01RV01", compRoueVelo, 2));
    	listePieces.add(new PieceCompositeMontee("roue de vélo", "02RV01", compRoueVelo, 1, 5));
    	listePieces.add(new PieceCompositeEnKit("ampoule", "01AM01", compAmpoule, 1));
    	listePieces.add(new PieceCompositeMontee("ampoule", "02AM01", compAmpoule, 2, 5));
    	listePieces.add(new PieceCompositeEnKit("pommeau de douche", "01PD01", compPommeauDouche, 1));
    	
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
