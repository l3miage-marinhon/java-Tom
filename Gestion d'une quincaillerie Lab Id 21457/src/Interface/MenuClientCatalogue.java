package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Client;
import clients.Entreprise;
import clients.Particulier;
import commandes.Commande;
import main.Application;
import pieces.*;


//BUG cette fenetre ne charge pas toutes ces info quand on la deplace un peut rapidement ou en pleine ecran
//BUG la page refait trop de chose , les maj sont mal gerer .... 
public class MenuClientCatalogue implements Runnable{

	
	JFrame frmClientCatalogue;
	JFrame frmDetailPiece;
	JFrame frmDetailPanier;
	JFrame frmModifInfos;
	JButton btnReturn;
	JButton btnInfos;
	JButton btnCommandes;
	JDialog detailPanier;
	JDialog modifInfos;
	JDialog mesCommandes;
	JButton btnPanier;
	JPanel listePieces;
	JPanel pnlCreditClient = new JPanel();
	JScrollPane jsp;
	JPanel westMenu;
	JPanel centerMenu = new JPanel();
	JPanel pnlBtnCart;
	int nbOnLineScrollPane;
	JButton btnValider;
			
	public MenuClientCatalogue() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuClientCatalogue());
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmClientCatalogue = new JFrame();
		frmClientCatalogue.setSize(new Dimension(1200, 800));
		frmClientCatalogue.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmClientCatalogue.setLocationRelativeTo(null);
		frmClientCatalogue.setMinimumSize(new Dimension(500, 300));
		frmClientCatalogue.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmClientCatalogue, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmClientCatalogue.dispose();
					System.exit(0);
				}
			}
		});
		JPanel content = (JPanel) frmClientCatalogue.getContentPane();
		content.setLayout(new BorderLayout());
		
		frmClientCatalogue.setVisible(true);
		frmClientCatalogue.setTitle("Catalogue");

		JPanel northMenu = northPanelClient();
		westMenu = westPanelPieces();
		centerMenu.add(catalogueMenu());
		content.add(westMenu, BorderLayout.WEST);
		content.add(northMenu, BorderLayout.NORTH);
		content.add(centerMenu, BorderLayout.CENTER);
		
		frmClientCatalogue.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent componentEvent) {
				int width = (int) (frmClientCatalogue.getSize().getWidth() - westMenu.getWidth());
				int numOnLine = width / 180;
				int res = (width - (numOnLine)*10 - 20) / 180 ;	// 20 correction scrollbar's width
				if(res != nbOnLineScrollPane) {
					content.remove(centerMenu);
					//FIX trop lour ici car tu refait tous a chaque mouvement 
					centerMenu = catalogueMenu();
					content.add(centerMenu, BorderLayout.CENTER);
				}
			}
		});
	}
	
	private JPanel catalogueMenu() {
		JPanel panel = new JPanel(new BorderLayout());
		
		listePieces = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		int width = (int) (frmClientCatalogue.getSize().getWidth() - westMenu.getWidth());
		int numOnLine = width / 180 ;
		int res = (width - (numOnLine)*10 - 20) / 180 ;	// 20 correction scrollbar's width
		nbOnLineScrollPane = res;
		
		for(Piece piece : Application.quincaillerie.getCatalogue().getCatalogue()) {
			//FIXIT ici a quoi sert cette condition ? 
			if(piece instanceof PieceDeBase || piece instanceof PieceCompositeEnKit) {
				PanelPiece panelPiece = new PanelPiece(piece, pnlBtnCart);
				gbc.gridx = n % res;
				gbc.gridy = (int) n / res;
				gbc.insets = new Insets(5, 5, 5, 5);
				listePieces.add(panelPiece, gbc);
				n++;
			}
		}
		jsp = new JScrollPane(listePieces, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		panel.add(jsp, BorderLayout.CENTER);
		
		return panel;
	}
	
	private void refreshPanier(Boolean inCart) {
		if(inCart) {
			detailPanier.getContentPane().removeAll();
			detailPiecesPanier(detailPanier);
			detailPanier.revalidate();
		}
		String nbArt = Integer.toString(Application.panier.nbArticles());
		String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
		pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
		pnlBtnCart.revalidate();
	}
	
	private void refreshPanelPiece(Piece piece) {

		int size = listePieces.getComponentCount();
		Component[] liste = listePieces.getComponents();
		int i = 0;
		while(i < size ) {
			if(liste[i] instanceof PanelPiece && ((PanelPiece) liste[i]).getPiece() == piece) {
				((PanelPiece) liste[i]).refreshComboBoxAndPanier(pnlBtnCart);
				i = size;
			}else {
				i++;
			}
		}
	}
	
	@SuppressWarnings("serial")
	private void detailPiecesPanier(JDialog detailPanier) {
		JPanel content = (JPanel) detailPanier.getContentPane();
		frmClientCatalogue.setFocusableWindowState(false);
		detailPanier.setAlwaysOnTop(true);
		content.setLayout(new BorderLayout());
		//content.removeAll();	//utile ?
		
		JPanel listeArticles = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(Piece p : Application.panier.getPanier().keySet()) {
			
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.insets = new Insets(15, 0, 0, 0);
			
			JPanel piece = new JPanel(new BorderLayout());
			piece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			piece.setPreferredSize(new Dimension(420, 40));
			GridBagConstraints gbcPiece = new GridBagConstraints();
			
			gbcPiece.gridx = 0;
			gbcPiece.gridy = 0;
			gbcPiece.fill = GridBagConstraints.HORIZONTAL;
			JPanel pP = new JPanel(new FlowLayout(FlowLayout.CENTER));
			pP.add(new JLabel(p.getRef() + " : " + p.getNom() + (p instanceof PieceCompositeEnKit ? " (kit) | " : (p instanceof PieceCompositeMontee ? " (montée) | " : " | ")) + p.prix() + (" €")));
			piece.add(pP, BorderLayout.WEST);
			
			gbcPiece.gridx = 1;
			JPanel pB = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton btnSuppr = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "delete_icon.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
			btnSuppr.addActionListener(ev->{
				int clickedButton = JOptionPane.showConfirmDialog(detailPanier, "Supprimer l'article ?", "", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) Application.panier.supprimePiecePanier(p);
				refreshPanier(true);
			});
			
			Vector<Integer> nbs1 = new Vector<>(){{for(int i : IntStream.range(0, Application.quincaillerie.getStocks().stocksPiece(p)+1).toArray()) add(i);}};
			JComboBox<Integer> nbValues = new JComboBox<>(nbs1);
			
			nbValues.setSelectedIndex(Application.panier.getPanier().get(p));
			nbValues.addActionListener(ev->{
				int newNb = nbValues.getItemAt(nbValues.getSelectedIndex());
				if(newNb == 0) {
					nbValues.setFocusable(false);
					int clickedButton = JOptionPane.showConfirmDialog(detailPanier, "Supprimer l'article ?", "", JOptionPane.YES_NO_OPTION);
					if(clickedButton == JOptionPane.YES_OPTION) Application.panier.supprimePiecePanier(p);
				}else {
					Application.panier.modifiePiecePanier(p, nbValues.getSelectedIndex());
				}
				refreshPanelPiece(p);
				refreshPanier(true);
			});
			pB.add(nbValues);
			pB.add(btnSuppr);
			piece.add(pB, BorderLayout.EAST);
			
			listeArticles.add(piece, gbc);
			n++;
		}
		
		double total = Application.panier.total();
		gbc.gridy = n;
		JPanel pTot = new JPanel(new BorderLayout());
		JPanel tot = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tot.add(new JLabel("Total : "));
		JPanel montantT = new JPanel(new FlowLayout(FlowLayout.LEFT));
		montantT.add(new JLabel(String.format("%.2f", total) + " €"));
		pTot.add(tot, BorderLayout.CENTER);
		pTot.add(montantT, BorderLayout.EAST);
		listeArticles.add(pTot, gbc);
		
		
		if(Application.clientCourant instanceof Particulier && ((Particulier) Application.clientCourant).isFidelite()) {
			
			double reduction = 0.1 * total;
			total -= reduction;
			
			gbc.gridy++;
			JPanel pRed = new JPanel(new BorderLayout());
			JPanel red = new JPanel(new FlowLayout(FlowLayout.LEFT));
			red.add(new JLabel("Réduction : "));
			JPanel montantR = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			montantR.add(new JLabel(String.format("%.2f", reduction) + " €"));
			pRed.add(red, BorderLayout.CENTER);
			pRed.add(montantR, BorderLayout.EAST);
			listeArticles.add(pRed, gbc);
			
			gbc.gridy++;
			JPanel pNTot = new JPanel(new BorderLayout());
			JPanel ntot = new JPanel(new FlowLayout(FlowLayout.LEFT));
			ntot.add(new JLabel("Nouveau total : "));
			JPanel montantNT = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			montantNT.add(new JLabel(String.format("%.2f", total) + " €"));
			pNTot.add(ntot, BorderLayout.CENTER);
			pNTot.add(montantNT, BorderLayout.EAST);
			listeArticles.add(pNTot, gbc);
			
		}	
		
		gbc.gridy++;
		JPanel pComm = new JPanel(new BorderLayout());
		JButton btnCommander = new JButton("Commander");
		if(Application.panier.nbArticles() == 0) btnCommander.setEnabled(false);
		btnCommander.addActionListener(ev->{
			actionCommander();
		});
		pComm.add(btnCommander, BorderLayout.EAST);
		
		listeArticles.add(pComm, gbc);
				
		jsp = new JScrollPane(listeArticles, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		content.add(jsp);
	}
	
	private void actionCommander() {
		Commande commande = Application.quincaillerie.creationCommande(Application.clientCourant, Application.panier.getPanier());
		if(commande != null) {
			Application.quincaillerie.getStocks().supprimeStocksPieces(commande.getListePieces());
			JOptionPane.showMessageDialog(detailPanier, "Commande acceptée !");
			pnlCreditClient.removeAll();
			pnlCreditClient.add(new JLabel("Crédit : " + String.format("%.2f", Application.clientCourant.getCredit()) + "€  "));
			pnlCreditClient.revalidate();
			pnlCreditClient.repaint();
			Application.panier.viderPanier();
			detailPanier.dispose();
			//refreshPanier(false);
		}else {
			JOptionPane.showMessageDialog(detailPanier, "Commande refusée, crédits insuffisants");
		}
	}
	
	private JDialog detailPanier() {
		detailPanier = new JDialog(frmDetailPanier, "Panier");
		detailPiecesPanier(detailPanier);
		return detailPanier;
	}	
	
	private JPanel northPanelClient() {
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.add(Application.createBtnReturn(frmClientCatalogue, MenuClientConnexion.class));
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlCreditClient.add(new JLabel("Crédit : " + String.format("%.2f", Application.clientCourant.getCredit()) + "€  "));
		rightPanel.add(pnlCreditClient);
		rightPanel.add(createBtnMyParts());
		rightPanel.add(createBtnInfo());
		rightPanel.add(createBtnOrder());
		rightPanel.add(createBtnCart());
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(rightPanel, BorderLayout.EAST);
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel westPanelPieces() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Filtres"));
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		content.add(new JLabel("Nom :"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		content.add(inputFieldFilter("Nom"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		content.add(new JLabel("Reférence :"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		content.add(inputFieldFilter("Ref"), gbc);
		
		JPanel ps = new JPanel(new FlowLayout());
		JSlider s = new JSlider(0, 40);
		s.setPreferredSize(new Dimension(100, 40));
		s.setPaintLabels(true);
		s.setPaintTicks(true);
		s.setPaintTrack(true);
		s.setMajorTickSpacing(20); 
        s.setMinorTickSpacing(5); 
        ps.add(s);

        gbc.gridx = 0;
		gbc.gridy = 4;
		content.add(ps, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		content.add(new JLabel("slider"), gbc);
		
		panel.add(content, BorderLayout.NORTH);
		return panel;
	}
	
	private JPanel pnlBtnModifier(JLabel[] labels, JComponent[] fields, JPanel pnlButtons) {
		JButton btnModifierInfos = new JButton("Modifier");
		JPanel pnlBtnModif = new JPanel();
		btnModifierInfos.addActionListener(ev->{
			pnlButtons.removeAll();
			pnlButtons.add(pnlBtnsValiderAnnuler(labels, fields, pnlButtons), BorderLayout.CENTER);
			setFieldsEnablingState(fields, true);
			pnlButtons.revalidate();
		});
		pnlBtnModif.add(btnModifierInfos);
		
		return pnlBtnModif;
	}
	
	private JPanel pnlBtnsValiderAnnuler(JLabel[] labels, JComponent[] fields, JPanel pnlButtons) {
		JButton btnValider = new JButton("Valider");
		JButton btnAnnuler = new JButton("Annuler");
		JPanel pnlBtns = new JPanel(new BorderLayout());
		JPanel pnlBtnVA = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnValider.addActionListener(ev->{
			Object b = 	Application.clientCourant instanceof Particulier ? 
						FormValidation.validerInfosParticulier(labels, fields, false) : 
						FormValidation.validerInfosEntreprise(labels, fields, false);
			if((Boolean) b) {
				pnlButtons.removeAll();
				pnlButtons.add(pnlBtnModifier(labels, fields, pnlButtons), BorderLayout.CENTER);
				setFieldsEnablingState(fields, false);
				pnlButtons.revalidate();
				Application.quincaillerie.afficheClients();
			}
		});
		
		btnAnnuler.addActionListener(ev->{
			modifInfos.dispose();
		});
		
		pnlBtnVA.add(btnAnnuler);
		pnlBtnVA.add(btnValider);
		pnlBtns.add(pnlBtnVA, BorderLayout.CENTER);
		
		return pnlBtnVA;
	}
	
	private JDialog modifInfosPart() {
		modifInfos = new JDialog(frmModifInfos, "Mes informations");
		JPanel content = (JPanel) modifInfos.getContentPane();
		JPanel p = new JPanel(new BorderLayout());
		content.add(p);
		
		JLabel[] labels = {	new JLabel("Civilité :"), new JLabel("Nom :"), new JLabel("Prénom :"), new JLabel("Adresse :"), 
							new JLabel("Téléphone :"), new JLabel("Email :"), new JLabel("Fidélité :")};
		JComponent[] fields = {	radioButtonPanelInfosPart("Civilité"), inputFieldInfosPart("Nom"), inputFieldInfosPart("Prénom"), 
								inputFieldInfosPart("Adresse"), inputFieldInfosPart("Téléphone"), inputFieldInfosPart("Email"), 
								radioButtonPanelInfosPart("Fidélité")};
		
		JPanel panelFields = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(JLabel label : labels) {
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.EAST;
			panelFields.add(label, gbc);
			n++;
		}
		
		n = 0;
		for(JComponent field : fields) {
			gbc.gridx = 1;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.WEST;
			panelFields.add(field, gbc);
			n++;
		}
		
		JPanel pnlButtons = new JPanel(new BorderLayout());
		pnlButtons.add(pnlBtnModifier(labels, fields, pnlButtons), BorderLayout.CENTER);
		
		p.add(panelFields, BorderLayout.CENTER);		
		p.add(pnlButtons, BorderLayout.SOUTH);
		
		content.add(p);
	
		return modifInfos;
	}
	
	private JDialog modifInfosEntr() {
		
		modifInfos = new JDialog(frmModifInfos, "Mes informations");
		JPanel content = (JPanel) modifInfos.getContentPane();
		JPanel p = new JPanel(new BorderLayout());
		content.add(p);
		
		JLabel[] labels = {	new JLabel("Siège social :"), new JLabel("Nom commercial :"), new JLabel("Catégorie :"), new JLabel("Adresse :"), 
				new JLabel("Téléphone :"), new JLabel("Email :")};
		JComponent[] fields = {	inputFieldInfosEntr("SiègeSocial"), inputFieldInfosEntr("NomCommercial"), radioButtonPanelInfosEntr("Catégorie"), 
					inputFieldInfosEntr("Adresse"), inputFieldInfosEntr("Téléphone"), inputFieldInfosEntr("Email")};
		
		JPanel panelFields = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(JLabel label : labels) {
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.EAST;
			panelFields.add(label, gbc);
			n++;
		}
		
		n = 0;
		for(JComponent field : fields) {
			gbc.gridx = 1;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.WEST;
			panelFields.add(field, gbc);
			n++;
		}
		
		JPanel pnlButtons = new JPanel(new BorderLayout());
		pnlButtons.add(pnlBtnModifier(labels, fields, pnlButtons), BorderLayout.CENTER);
		
		p.add(panelFields, BorderLayout.CENTER);		
		p.add(pnlButtons, BorderLayout.SOUTH);
		
		content.add(p);
		
		return modifInfos;
	}
	
	private void setFieldsEnablingState(JComponent[] fields, Boolean state) {
		for(JComponent jc : fields) {
			if(jc.getComponent(0) instanceof JTextField) {
				((JTextField) jc.getComponent(0)).setEnabled(state);
			}else if(jc.getComponent(0) instanceof JRadioButton){
				for(int i=0; i<jc.getComponentCount(); i++) {
					((JRadioButton) jc.getComponent(i)).setEnabled(state);
				}
			}
		}
	}
	
	private JPanel inputFieldFilter(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		if(s.equals("Nom")) {
			t = new JTextField(8);
		}else if(s.equals("Ref")) {
			t = new JTextField(8);
		}
		
		field.add(t);
		return field;
	}
	
	private JPanel inputFieldInfosPart(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		Client c = Application.clientCourant;
		if(c instanceof Particulier) {
			if(s.equals("Adresse")) {
				t = new JTextField(30);
				t.setText( ((Particulier) c).getAdresse() );
			}else if(s.equals("Téléphone")) {
				t = new JTextField(10);
				t.setText( ((Particulier) c).getTel());
			}else if(s.equals("Email")) {
				t = new JTextField(30);
				t.setText( ((Particulier) c).getEmail() );
			}else if(s.equals("Nom")) {
				t = new JTextField(20);
				t.setText( ((Particulier) c).getNom() );
			}else if(s.equals("Prénom")) {
				t = new JTextField(20);
				t.setText( ((Particulier) c).getPrenom() );
			}
		}
		
		t.setEnabled(false);
		field.add(t);
		return field;
	}
	
	private JPanel inputFieldInfosEntr(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		Client c = Application.clientCourant;
		if(s.equals("Adresse")) {
			t = new JTextField(30);
			t.setText( ((Entreprise) c).getAdresse() );
		}else if(s.equals("Téléphone")) {
			t = new JTextField(10);
			t.setText( ((Entreprise) c).getTel() );
		}else if(s.equals("Email")) {
			t = new JTextField(30);
			t.setText( ((Entreprise) c).getEmail());
		}else if(s.equals("NomCommercial")) {
			t = new JTextField(20);
			t.setText( ((Entreprise) c).getNomCommercial());
		}else if(s.equals("SiègeSocial")) {
			t = new JTextField(30);
			t.setText( ((Entreprise) c).getSiegeSocial());
		}
		
		t.setEnabled(false);
		field.add(t);
		return field;
	}
	
	private JPanel radioButtonPanelInfosPart(String s) {
		JPanel radioPanel = new JPanel(new GridLayout(1,2));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = null;
		JRadioButton b2 = null;
		
		Client c = Application.clientCourant;

		if(s.equals("Civilité")) {
			b1 = new JRadioButton("Monsieur");
			b2 = new JRadioButton("Madame");
			if(c instanceof Particulier) {
				if(((Particulier) c).getCivilite() == Civilite.MONSIEUR) {
					b1.setSelected(true);
				}else {
					b2.setSelected(true);
				}
			}
		}else if(s.equals("Fidélité")) {
			b1 = new JRadioButton("Oui");
			b2 = new JRadioButton("Non");
			if(c instanceof Particulier) {
				if(((Particulier) c).isFidelite()) {
					b1.setSelected(true);
				}else {
					b2.setSelected(true);
				}
			}
		}
		
		b1.setEnabled(false);
		b2.setEnabled(false);

		btnGrp.add(b1);
		btnGrp.add(b2);

		radioPanel.add(b1);
		radioPanel.add(b2);
		
		return radioPanel;
	}

	private JPanel radioButtonPanelInfosEntr(String s) {
		JPanel radioPanel = new JPanel(new GridLayout(1,4));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = null;
		JRadioButton b2 = null;
		JRadioButton b3 = null;
		JRadioButton b4 = null;
		
		Client c = Application.clientCourant;
		
		if(s.equals("Catégorie")) {
			b1 = new JRadioButton("GE");
			b2 = new JRadioButton("ETI");
			b3 = new JRadioButton("PME");
			b4 = new JRadioButton("TPE");
		}
		
		if(c instanceof Entreprise) {
			if(((Entreprise) c).getCategorie() == CategorieEntreprise.GE) {
				b1.setSelected(true);
			}else if(((Entreprise) c).getCategorie() == CategorieEntreprise.ETI) {
				b2.setSelected(true);
			}else if(((Entreprise) c).getCategorie() == CategorieEntreprise.PME) {
				b3.setSelected(true);
			}else {
				b4.setSelected(true);
			}	
		}
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		
		btnGrp.add(b1);
		btnGrp.add(b2);
		btnGrp.add(b3);
		btnGrp.add(b4);

		radioPanel.add(b1);
		radioPanel.add(b2);
		radioPanel.add(b3);
		radioPanel.add(b4);
				
		return radioPanel;
	}
	
	private JPanel createBtnInfo() {
		JPanel pnlBtnInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnInfos = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "info_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnInfos.addActionListener(ev->{
			if(Application.clientCourant instanceof Particulier) {
				modifInfos = modifInfosPart();
				modifInfos.setSize(new Dimension(500, 500));
				modifInfos.setLocationRelativeTo(null);
				modifInfos.setVisible(true);
			}else {
				modifInfos = modifInfosEntr();
				modifInfos.setSize(new Dimension(500, 500));
				modifInfos.setLocationRelativeTo(null);
				modifInfos.setVisible(true);
			}
				
		});
		pnlBtnInfo.add(btnInfos);
		return pnlBtnInfo;
	}
	
	private JPanel createBtnOrder() {
		JPanel pnlBtnOrder = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnCommandes = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "order_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnCommandes.addActionListener(ev->{ 
			mesCommandes = new JDialog(frmClientCatalogue, "Mes commandes");
			JPanel content = (JPanel) mesCommandes.getContentPane();
			content.setLayout(new BorderLayout());
			JPanel pnlCommandes = new JPanel();
			pnlCommandes.setLayout(new BoxLayout(pnlCommandes, BoxLayout.Y_AXIS));			
			
			
			for(Commande commande : Application.quincaillerie.getCommandesClient(Application.clientCourant)) {
				JPanel pnl = createPanelCommande(commande);
				pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pnlCommandes.add(pnl);
			}
			
			jsp = new JScrollPane(pnlCommandes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.getVerticalScrollBar().setUnitIncrement(10);
			content.add(jsp, BorderLayout.CENTER);
			
			mesCommandes.setSize(new Dimension(400, 400));
			mesCommandes.setLocationRelativeTo(null);
			mesCommandes.setVisible(true);
		});
		pnlBtnOrder.add(btnCommandes);
		
		return pnlBtnOrder;
	}
	
	private JPanel createPanelCommande(Commande commande) {
		JPanel pnl = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnl.add(new JLabel("Numéro commande : " + commande.getNum()), gbc);
		gbc.gridy++;
		pnl.add(new JLabel("Date : " + commande.getDate()), gbc);
		gbc.gridy++;
		pnl.add(new JLabel("Adresse de livraison : " + commande.getClient().getAdresse()), gbc);
		gbc.gridy++;
		if(commande.getClient() instanceof Particulier) {
			pnl.add(new JLabel("Nom : " + ((Particulier) commande.getClient()).getNom()), gbc);
			gbc.gridy++;
			pnl.add(new JLabel("Prénom : " + ((Particulier) commande.getClient()).getPrenom()), gbc);
		}else {
			pnl.add(new JLabel("Adresse de livraison : " + ((Entreprise) commande.getClient()).getNomCommercial()), gbc);
		}
		gbc.gridy++;
		pnl.add(new JLabel("Téléphone : " + commande.getClient().getTel()), gbc);
		gbc.gridy++;
		JPanel pnlRecapPieces = new JPanel(new BorderLayout());
		
		JPanel pnlListePieces = new JPanel();
		pnlListePieces.setLayout(new BoxLayout(pnlListePieces, BoxLayout.Y_AXIS));
		pnlRecapPieces.add(new JLabel("Articles"), BorderLayout.NORTH);
		for(Piece p : commande.getListePieces().keySet()) {
			pnlListePieces.add(new JLabel(p.getRef() + "; " + p.getNom() + ";  x" + commande.getListePieces().get(p) 
												/*+ " : " + ( String.format("%.2f", p.prix()*commande.getListePieces().get(p)) + " €" )*/
					
					));
		}
		pnlRecapPieces.add(pnlListePieces, BorderLayout.CENTER);
		pnlRecapPieces.add(new JLabel("Prix : " + commande.getPrix() + " €"), BorderLayout.SOUTH);
		pnl.add(pnlRecapPieces, gbc);
		gbc.gridy++;
		pnl.add(new JLabel("Etat de la commande : " + commande.getEtat()), gbc);
		
		return pnl;
	}
	
	private JDialog piecesPossedeesClient() {
		JDialog j = new JDialog(frmClientCatalogue, "Mes pièces");
		JPanel content = (JPanel) j.getContentPane();
		content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int n = 0;
		for(Piece piece : Application.clientCourant.getPiecesPossedees().keySet()) {
			gbc.gridx = 0;
			gbc.gridy = n;
			content.add(new JLabel(piece.getRef() + " : " + piece.getNom()), gbc);
			gbc.gridx = 1;
			content.add(new JLabel("  x" +  Application.clientCourant.getPiecesPossedees().get(piece)), gbc);
		}
		
		return j;
	}
	
	
	private JPanel createBtnCart() {
		pnlBtnCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanier = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "cart_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnPanier.addActionListener(ev->{
			//frmClientCatalogue.setEnabled(false);
			detailPanier = detailPanier();
			detailPanier.setSize(600, 400);
			detailPanier.setLocationRelativeTo(null);
			detailPanier.setVisible(true);
		});
		String nbArt = Integer.toString(Application.panier.nbArticles());
		String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
		pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
		pnlBtnCart.add(btnPanier);
		return pnlBtnCart;
	}
	
	private JPanel createBtnMyParts() {
		JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnParts = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "my_parts.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnParts.addActionListener(ev->{
			JDialog dlgMyParts = piecesPossedeesClient();
			dlgMyParts.setSize(600, 400);
			dlgMyParts.setLocationRelativeTo(null);
			dlgMyParts.setVisible(true);
		});
		pnl.add(btnParts);
		return pnl;
	}
}
