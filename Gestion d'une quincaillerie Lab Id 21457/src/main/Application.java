package main;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Interface.MenuPrincipal;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;
import quincaillerie.Catalogue;
import quincaillerie.Quincaillerie;
import quincaillerie.Stocks;


public class Application {
	
	public static Quincaillerie quincaillerie = initialize();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new MenuPrincipal());
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
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
    	
		return new Quincaillerie("MaQuincaillerieGrenoble", 1000, catalogue, stocks, new HashMap<>());
	}
	
	public void setMenuClientQuinc(JFrame frmApplication) {
		JPanel content = (JPanel) frmApplication.getContentPane();
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JButton btnClient = new JButton("CLIENT");
		btnClient.addActionListener(ev->{setMenuClientKnownUnknown(frmApplication);});
		menu.add(btnClient, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton btnQuinc = new JButton("QUINCAILLERIE");
		btnQuinc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 System.out.println("Quincaillerie cliquée");
			}
		});
		menu.add(btnQuinc, gbc);

		content.add(menu);
	}
	
	public void setMenuClientKnownUnknown(JFrame frmApplication) {
		JPanel content = (JPanel) frmApplication.getContentPane();
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JButton btnInscription = new JButton("S'inscrire");
		btnInscription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("S'inscrire cliqué");
				
			}
		});
		menu.add(btnInscription, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JButton btnConnecter = new JButton("Se connecter");
		btnConnecter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 System.out.println("Se connecter cliqué");
			}
		});
		menu.add(btnConnecter, gbc);

		content.add(menu);
	}
}
