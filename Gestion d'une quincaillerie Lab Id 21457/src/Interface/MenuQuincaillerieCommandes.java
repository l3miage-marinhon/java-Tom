package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import clients.Client;
import commandes.Commande;
import main.Application;
import pieces.Piece;
import servicesBancaires.ServiceBancaire;

public class MenuQuincaillerieCommandes implements Runnable {
	
	JFrame frmMenuQuincaillerieCommandes;
	JPanel pnlListeClientsCommandes;
	
	public MenuQuincaillerieCommandes() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuQuincaillerieCommandes());
		previousFrm.dispose();
	}

	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmMenuQuincaillerieCommandes = new JFrame();
		frmMenuQuincaillerieCommandes.setSize(new Dimension(600, 500));
		frmMenuQuincaillerieCommandes.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmMenuQuincaillerieCommandes.setLocationRelativeTo(null);
		frmMenuQuincaillerieCommandes.setMinimumSize(new Dimension(350, 130));
		frmMenuQuincaillerieCommandes.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmMenuQuincaillerieCommandes, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmMenuQuincaillerieCommandes.dispose();
					System.exit(0);
				}
			}
		});
		frmMenuQuincaillerieCommandes.setVisible(true);
		
		frmMenuQuincaillerieCommandes.setTitle("Commandes clients");
		JPanel content = (JPanel) frmMenuQuincaillerieCommandes.getContentPane();
		
		content.setLayout(new BorderLayout());
		content.add(Application.createBtnReturn(frmMenuQuincaillerieCommandes, MenuQuincailleriePiecesCommandes.class), BorderLayout.NORTH);
		pnlListeClientsCommandes = new JPanel();
		pnlListeClientsCommandes = createMenu();
		content.add(pnlListeClientsCommandes, BorderLayout.CENTER);
	}
	
	private JPanel createMenu() {
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbcMenu = new GridBagConstraints();
		gbcMenu.insets = new Insets(0, 0, 4, 0);
		gbcMenu.gridx = 0;
		int nClient = 0;
		for(Client client : Application.quincaillerie.getListeClientsCommandes().keySet()) {
			if(Application.quincaillerie.getListeClientsCommandes().get(client).size() != 0) {
				gbcMenu.gridy = nClient;
				
				JPanel pnlClientCommandes = new JPanel(new GridBagLayout());
				GridBagConstraints gbcClCo = new GridBagConstraints();
				pnlClientCommandes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				gbcClCo.gridx = 0;
				gbcClCo.gridy = 0;
				JPanel pnlInfosClient = new JPanel();
				pnlInfosClient.setLayout(new BoxLayout(pnlInfosClient, BoxLayout.Y_AXIS));
				pnlInfosClient.add(new JLabel("ID : "+client.getId()));
				pnlInfosClient.add(new JLabel("Email : "+client.getEmail()));
				
				pnlClientCommandes.add(pnlInfosClient, gbcClCo);
				
				gbcClCo.gridx = 1;
				
				JPanel pnlCommandes = new JPanel(new GridBagLayout());
				GridBagConstraints gbcCom = new GridBagConstraints();
				int nCommande = 0;
				for(Commande commande : Application.quincaillerie.getListeClientsCommandes().get(client)) {
					gbcCom.gridx = 0;
					gbcCom.gridy = nCommande;
					pnlCommandes.add(createPanelCommande(client, commande), gbcCom);
					nCommande++;
				}
				pnlClientCommandes.add(pnlCommandes, gbcClCo);
				
				menu.add(pnlClientCommandes, gbcMenu);
			}
			nClient++;
		}
		
		

		return menu;
	}
	
	private JPanel createPanelCommande(Client client, Commande commande) {
		JPanel pnl = new JPanel(new GridBagLayout());
		pnl.setBorder(BorderFactory.createDashedBorder(null));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnl.add(new JLabel("Numéro commande : " + commande.getNum()), gbc);
		gbc.gridy = 1;
		pnl.add(new JLabel("Date : " + commande.getDate()), gbc);
		gbc.gridy = 2;
		JPanel pnlListePieces = new JPanel();
		pnlListePieces.setLayout(new BoxLayout(pnlListePieces, BoxLayout.Y_AXIS));
		for(Piece p : commande.getListePieces().keySet()) {
			pnlListePieces.add(new JLabel(p.getRef() + "; " + p.getNom() + ";  x" + commande.getListePieces().get(p) 
												+ " : " + ( String.format("%.2f", p.prix()*commande.getListePieces().get(p)) + " €" )));
		}
		pnl.add(pnlListePieces, gbc);
		gbc.gridy = 3;
		pnl.add(new JLabel("Etat de la commande : " + commande.getEtat()), gbc);
		gbc.gridx = 1;
		JButton btnAvancerEtatCommande = new JButton("Avancer");
		//FIX t'es bouton devrai évoluer selon le status
		btnAvancerEtatCommande.addActionListener(ev->{
			int clickedButton = JOptionPane.showConfirmDialog(frmMenuQuincaillerieCommandes, "Avancer l'état de la commande ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if(clickedButton == JOptionPane.YES_OPTION) {
				commande.avancerEtat();
				pnlListeClientsCommandes.removeAll();
				pnlListeClientsCommandes.add(createMenu());
				pnlListeClientsCommandes.revalidate();
				pnlListeClientsCommandes.repaint();
			}
		});
		pnl.add(btnAvancerEtatCommande, gbc);
		
		gbc.gridx = 2;
		JButton btnAnnulerCommande = new JButton("Annuler");
		btnAnnulerCommande.addActionListener(ev->{
			int clickedButton = JOptionPane.showConfirmDialog(frmMenuQuincaillerieCommandes, "Annuler la commande ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if(clickedButton == JOptionPane.YES_OPTION) {
				if(commande.estAnnulable()) {
					double montant = commande.getPrix();
					ServiceBancaire.approvisionneCreditClient(client, montant);
					commande.annulerCommande();
					pnlListeClientsCommandes.removeAll();
					pnlListeClientsCommandes.add(createMenu());
					frmMenuQuincaillerieCommandes.revalidate();
					frmMenuQuincaillerieCommandes.repaint();
				}
			}
		});
		pnl.add(btnAnnulerCommande, gbc);
		
		
		return pnl;
	}
	
}
