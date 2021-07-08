package bataille;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import exceptions.UnknownPlayer;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Bataille {
	private static final String PATH_TO_IMAGES="src/images/";

	private JFrame frmJeuDeBataille;
	
	private JLabel commentaire;
	private JLabel labelJoueur1;
	private JLabel tasJoueur1Label;
	private JLabel carteJoueur1Label;

	private JLabel labelJoueur2;
	private JLabel tasJoueur2Label;
	private JLabel carteJoueur2Label;
	
	private Jeu jeu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bataille window = new Bataille();
					window.frmJeuDeBataille.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Bataille() {
		initialize();
		resetCards();
		initializeJeuDeCartes();
	}
	private void initializeJeuDeCartes() {
		String nomJoueur1 = "Alphonse";
		String nomJoueur2 = "Nathalie";
		jeu = new Jeu(nomJoueur1, nomJoueur2);

		jeu.melangerCartes();
		jeu.distribuer();
		jeu.printJeuDeCarte();
		
		labelJoueur1.setText(nomJoueur1 + " : " + jeu.getJoueur1().nbCartes() + " carte(s)");
		labelJoueur2.setText(nomJoueur2 + " : " + jeu.getJoueur2().nbCartes() + " carte(s)");		

	}

	private void resetCards() {
		commentaire.setText("  Bonjour");
		tasJoueur1Label.setText("");
		tasJoueur1Label.setIcon(new ImageIcon(PATH_TO_IMAGES + "dos-de-carte.png"));
		carteJoueur1Label.setText("");
		tasJoueur2Label.setText("");
		tasJoueur2Label.setIcon(new ImageIcon(PATH_TO_IMAGES + "dos-de-carte.png"));
		carteJoueur2Label.setText("");
	}
	public void joueur1Joue() throws UnknownPlayer{
		//commentaire.setText("Le joueur + " + jeu.getNomJoueur(1) + " joue.");
		Carte carte = jeu.joueurJoue(1);
		if (carte != null) {
			String carteFileName = PATH_TO_IMAGES + carte.getFileName();
			carteJoueur1Label.setIcon(new ImageIcon(carteFileName));
		} else {
			carteJoueur1Label.setIcon(null);
		}
		
		if (jeu.getNbCartesJoueur(1) == 0) {
			tasJoueur1Label.setIcon(null);
		}
	}
	
	public void joueur2Joue() throws UnknownPlayer{
		//commentaire.setText("Le joueur + " + jeu.getNomJoueur(2) + " joue.");
		Carte carte = jeu.joueurJoue(2);
		if (carte != null) {
			String carteFileName = PATH_TO_IMAGES + carte.getFileName();
			carteJoueur2Label.setIcon(new ImageIcon(carteFileName));
		} else {
			carteJoueur2Label.setIcon(null);
		}
		if (jeu.getNbCartesJoueur(2) == 0) {
			tasJoueur2Label.setIcon(null);
		}
	}
	
	public void joueur1DefileTas() throws UnknownPlayer{
		commentaire.setText("Joueur 1 dÃ©file tas");
		jeu.printTasJoueur(1);	
	}
	
	public void joueur2DefileTas() throws UnknownPlayer{
		commentaire.setText("Joueur 2 dÃ©file tas");
		jeu.printTasJoueur(2);		
	}
	
	public int remporter() throws UnknownPlayer{		//return 0 si egalité, 1 si qqn gagner le pli, 2 si victoire, 3 si égalité et j1 rejoue, 4 si égalite et j2 rejoue, 5 si match nul
		int res;
		String quiRemporte = new String();
		quiRemporte = jeu.remporter();
		if(quiRemporte.equals("TIE")) {
			commentaire.setText("  Egalité, vous devez rejouer");
			res = 0;
		}else if(quiRemporte.equals("Draw")) {
			commentaire.setText("  Match nul !");
			res = 5;
		}else if(quiRemporte.equals(jeu.getJoueur1().getNom().toUpperCase()) || quiRemporte.equals(jeu.getJoueur2().getNom().toUpperCase())) {
			commentaire.setText("  " + quiRemporte.toLowerCase() + " a gagné la partie !");
			res = 2;
		}else if(quiRemporte.equals(jeu.getJoueur1().getNom() + "PlayAgain")) {
			commentaire.setText("Egalité : " + jeu.getJoueur1().getNom() + " doit continuer à jouer");
			res = 3;
		}else if(quiRemporte.equals(jeu.getJoueur2().getNom() + "PlayAgain")) {
			commentaire.setText("Egalité : " + jeu.getJoueur2().getNom() + " doit continuer à jouer");
			res = 4;
		}else {
			commentaire.setText("  " + quiRemporte + " remporte le pli");
			res = 1;
		}
		return res;	
	}
	
	public void finirPli() throws UnknownPlayer{	//actualise l'affichage du tas et du deck des 2 joueurs
		Carte carte = jeu.derniereCarteJouee(1);
		if (carte == null) {
			carteJoueur1Label.setIcon(new ImageIcon(""));
		} else {
			carteJoueur1Label.setIcon(new ImageIcon(PATH_TO_IMAGES + carte.getFileName()));			
		}
		if (jeu.getNbCartesJoueur(1) == 0) {
			tasJoueur1Label.setIcon(null);
		}else {
			tasJoueur1Label.setIcon(new ImageIcon(PATH_TO_IMAGES + "dos-de-carte.png"));
		}
		
		carte = jeu.derniereCarteJouee(2);
		if (carte == null) {
			carteJoueur2Label.setIcon(new ImageIcon(""));
		} else {
			carteJoueur2Label.setIcon(new ImageIcon(PATH_TO_IMAGES + carte.getFileName()));			
		}
		if (jeu.getNbCartesJoueur(2) == 0) {
			tasJoueur2Label.setIcon(null);
		}else {
			tasJoueur2Label.setIcon(new ImageIcon(PATH_TO_IMAGES + "dos-de-carte.png"));
		}
		
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJeuDeBataille = new JFrame();
		frmJeuDeBataille.getContentPane().setMinimumSize(new Dimension(1000, 550));
		frmJeuDeBataille.getContentPane().setPreferredSize(new Dimension(1000, 550));
		frmJeuDeBataille.setTitle("Jeu de Bataille");
		frmJeuDeBataille.getContentPane().setBackground(new Color(0, 100, 0));
		frmJeuDeBataille.getContentPane().setLayout(new BoxLayout(frmJeuDeBataille.getContentPane(), BoxLayout.Y_AXIS));
		
		//Panel Joueurs
		JPanel joueursPanel = new JPanel();
		frmJeuDeBataille.getContentPane().add(joueursPanel);
		joueursPanel.setLayout(new BoxLayout(joueursPanel, BoxLayout.X_AXIS));
		
		//Panel Joueur1
		JPanel joueur1Panel = new JPanel();
		joueur1Panel.setBackground(new Color(100, 149, 237));
		joueursPanel.add(joueur1Panel);
		joueur1Panel.setLayout(new BoxLayout(joueur1Panel, BoxLayout.Y_AXIS));
		
		labelJoueur1 = new JLabel("Joueur 1");
		labelJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
		joueur1Panel.add(labelJoueur1);
		
		JPanel joueur1CartesPanel = new JPanel();
		joueur1CartesPanel.setPreferredSize(new Dimension(500, 400));
		joueur1CartesPanel.setMinimumSize(new Dimension(500, 400));
		joueur1CartesPanel.setBackground(new Color(0, 100, 0));
		joueur1Panel.add(joueur1CartesPanel);
		joueur1CartesPanel.setLayout(new GridLayout(1, 0, 20, 0));
		
		tasJoueur1Label = new JLabel("tas joueur 1");
		tasJoueur1Label.setHorizontalAlignment(SwingConstants.CENTER);
		tasJoueur1Label.setPreferredSize(new Dimension(240, 360));
		tasJoueur1Label.setMaximumSize(new Dimension(240, 360));
		tasJoueur1Label.setMinimumSize(new Dimension(240, 360));
		joueur1CartesPanel.add(tasJoueur1Label);
		
		carteJoueur1Label = new JLabel("carte jouÃ©e");
		carteJoueur1Label.setHorizontalAlignment(SwingConstants.CENTER);
		carteJoueur1Label.setPreferredSize(new Dimension(240, 360));
		carteJoueur1Label.setMaximumSize(new Dimension(240, 360));
		carteJoueur1Label.setMinimumSize(new Dimension(240, 360));
		joueur1CartesPanel.add(carteJoueur1Label);
		
		JPanel joueur1BoutonsPanel = new JPanel();
		joueur1BoutonsPanel.setBackground(new Color(100, 149, 237));
		joueur1Panel.add(joueur1BoutonsPanel);
		joueur1BoutonsPanel.setLayout(new GridLayout(0, 2, 20, 0));
		
		JButton btnJouer_1 = new JButton("Jouer");
		joueur1BoutonsPanel.add(btnJouer_1);
		
		JButton btnFaireDfiler_1 = new JButton("afficher les cartes du tas");
		joueur1BoutonsPanel.add(btnFaireDfiler_1);

		//Panel Joueur2
		JPanel joueur2Panel = new JPanel();
		joueur2Panel.setBackground(new Color(245, 222, 179));
		joueursPanel.add(joueur2Panel);
		joueur2Panel.setLayout(new BoxLayout(joueur2Panel, BoxLayout.Y_AXIS));
		
		labelJoueur2 = new JLabel("Joueur 2");
		labelJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
		joueur2Panel.add(labelJoueur2);
		
		JPanel joueur2CartesPanel = new JPanel();
		joueur2CartesPanel.setPreferredSize(new Dimension(500, 400));
		joueur2CartesPanel.setMinimumSize(new Dimension(500, 400));
		joueur2CartesPanel.setBackground(new Color(0, 100, 0));
		joueur2Panel.add(joueur2CartesPanel);
		joueur2CartesPanel.setLayout(new GridLayout(1, 0, 20, 0));
		
		carteJoueur2Label = new JLabel("carte jouÃ©e");
		carteJoueur2Label.setHorizontalAlignment(SwingConstants.CENTER);
		carteJoueur2Label.setPreferredSize(new Dimension(240, 360));
		carteJoueur2Label.setMaximumSize(new Dimension(240, 360));
		carteJoueur2Label.setMinimumSize(new Dimension(240, 360));
		joueur2CartesPanel.add(carteJoueur2Label);
		
		tasJoueur2Label = new JLabel("tas joueur 2");
		tasJoueur2Label.setHorizontalAlignment(SwingConstants.CENTER);
		tasJoueur2Label.setPreferredSize(new Dimension(240, 360));
		tasJoueur2Label.setMaximumSize(new Dimension(240, 360));
		tasJoueur2Label.setMinimumSize(new Dimension(240, 360));
		joueur2CartesPanel.add(tasJoueur2Label);
		
		JPanel joueur2BoutonsPanel = new JPanel();
		joueur2BoutonsPanel.setBackground(new Color(245, 222, 179));
		joueur2Panel.add(joueur2BoutonsPanel);
		joueur2BoutonsPanel.setLayout(new GridLayout(0, 2, 20, 5));
		
		JButton btnFaireDfiler_2 = new JButton("afficher les cartes du tas");
		joueur2BoutonsPanel.add(btnFaireDfiler_2);
		
		JButton btnJouer_2 = new JButton("jouer");
		joueur2BoutonsPanel.add(btnJouer_2);
		
		//Panel commentaires
		JPanel commentairePanel = new JPanel();
		commentairePanel.setBackground(new Color(0, 100, 0));
		frmJeuDeBataille.getContentPane().add(commentairePanel);
		commentairePanel.setLayout(new BoxLayout(commentairePanel, BoxLayout.X_AXIS));
		
		JButton btnFinPli = new JButton("Finir pli");
		commentairePanel.add(btnFinPli);
		btnFinPli.setEnabled(false);

		//Action listeners
		btnFaireDfiler_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joueur1DefileTas();
				}catch(UnknownPlayer u) {
					u.printStackTrace();
				}
			}
		});

		btnJouer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joueur1Joue();
					labelJoueur1.setText(jeu.getJoueur1().getNom() + " : " + jeu.getJoueur1().nbCartes() + " carte(s)");
					btnJouer_1.setEnabled(false);
					if (!btnJouer_2.isEnabled()) {
						int res = remporter();
						switch (res) {
						case 0:		//egalite simple, les 2 joueurs doivent rejouer
							btnFinPli.setEnabled(false);
							btnJouer_1.setEnabled(true);
							btnJouer_2.setEnabled(true);
							break;
						case 1:		//pli gagné
							btnFinPli.setEnabled(true);
							btnFaireDfiler_1.setEnabled(false);
							btnFaireDfiler_2.setEnabled(false);
							break;
						case 2:		//Victoire partie
						case 5:		//Match nul
							btnFaireDfiler_1.setEnabled(false);
							btnFaireDfiler_2.setEnabled(false);
							btnFinPli.setEnabled(false);
							btnJouer_1.setEnabled(false);
							btnJouer_2.setEnabled(false);
							break;
						case 3:		//égalité et j2 n'a plus de cartes
							btnJouer_1.setEnabled(true);
							break;
						case 4:		//égalité et j1 n'a plus de cartes
							btnJouer_2.setEnabled(true);
							break;
						}
					}
				}catch(UnknownPlayer u) {
					u.printStackTrace();
				}
			}
		});

		btnJouer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joueur2Joue();
					labelJoueur2.setText(jeu.getJoueur2().getNom() + " : " + jeu.getJoueur2().nbCartes() + " carte(s)");
					btnJouer_2.setEnabled(false);
					if (!btnJouer_1.isEnabled()) {
						int res = remporter();
						switch (res) {
						case 0:		//egalite simple, les 2 joueurs doivent rejouer
							btnJouer_1.setEnabled(true);
							btnJouer_2.setEnabled(true);
							break;
						case 1:		//pli gagné
							btnFinPli.setEnabled(true);
							btnFaireDfiler_1.setEnabled(false);
							btnFaireDfiler_2.setEnabled(false);
							break;
						case 2:		//Victoire partie
						case 5:		//Match nul
							btnFaireDfiler_1.setEnabled(false);
							btnFaireDfiler_2.setEnabled(false);
							btnFinPli.setEnabled(false);
							btnJouer_1.setEnabled(false);
							btnJouer_2.setEnabled(false);
							break;
						case 3:		//égalité et j2 n'a plus de cartes
							btnJouer_1.setEnabled(true);
							break;
						case 4:		//égalité et j1 n'a plus de cartes
							btnJouer_2.setEnabled(true);
							break;
						}
					}
				}catch(UnknownPlayer u) {
					u.printStackTrace();
				}
			}
		});
		
		
		btnFaireDfiler_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joueur2DefileTas();
				}catch(UnknownPlayer u) {
					u.printStackTrace();
				}
			}
		});
			
		btnFinPli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					finirPli();
					btnJouer_1.setEnabled(true);
					btnJouer_2.setEnabled(true);
					btnFinPli.setEnabled(false);
					btnFaireDfiler_1.setEnabled(true);
					btnFaireDfiler_2.setEnabled(true);
					labelJoueur1.setText(jeu.getJoueur1().getNom() + " : " + jeu.getJoueur1().nbCartes() + " carte(s)");
					labelJoueur2.setText(jeu.getJoueur2().getNom() + " : " + jeu.getJoueur2().nbCartes() + " carte(s)");
				}catch(UnknownPlayer u) {
					u.printStackTrace();
				}
			}
		});
		
		commentaire = new JLabel("Commentaire");
		commentairePanel.add(commentaire);
		commentaire.setForeground(new Color(255, 255, 240));
		commentaire.setHorizontalAlignment(SwingConstants.CENTER);
		frmJeuDeBataille.setBounds(100, 100, 1054, 490);
		frmJeuDeBataille.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}