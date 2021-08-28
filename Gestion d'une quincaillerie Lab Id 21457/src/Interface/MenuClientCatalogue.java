package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthStyleFactory;

import clients.Client;
import clients.Civilite;
import clients.Particulier;
import main.Application;
import pieces.Piece;

public class MenuClientCatalogue implements Runnable{

public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmClientCatalogue;
	JFrame frmDetailPiece;
	JFrame frmDetailPanier;
	JFrame frmModifInfos;
	JButton btnReturn;
	JButton btnInfos;
	JButton btnCommandes;
	JDialog detailPanier;
	JDialog modifInfos;
	JButton btnPanier;
	JPanel listePieces;
	JScrollPane jsp;
	JPanel westMenu;
	JPanel centerMenu;
	JPanel pnlBtnCart;
	int nbOnLineScrollPane;
	JButton btnValider;
			
	public MenuClientCatalogue(JFrame previousFrm) {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuClientCatalogue(previousFrm));
		previousFrm.dispose();
	}
	
	public static void demarrer(JFrame frmClientCatalogue, JFrame previousFrm) {
		frmClientCatalogue.setVisible(true);
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
		
		frmClientCatalogue.setVisible(true);
		
		frmClientCatalogue.setTitle("Catalogue");
		JPanel content = (JPanel) frmClientCatalogue.getContentPane();
		content.setLayout(new BorderLayout());
		

		JPanel northMenu = northPanelClient();
		content.add(northMenu, BorderLayout.NORTH);
		
		westMenu = westPanelPieces();
		content.add(westMenu, BorderLayout.WEST);
		
		JPanel southMenu = Application.version();
		content.add(southMenu, BorderLayout.SOUTH);
		
		centerMenu = catalogueMenu();
		content.add(centerMenu, BorderLayout.CENTER);
		
		frmClientCatalogue.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int width = (int) (frmClientCatalogue.getSize().getWidth() - westMenu.getWidth());
				int numOnLine = width / 160;
				int res = (width - (numOnLine)*10 - 20) / 160 ;	// 20 correction scrollbar's width
				if(res != nbOnLineScrollPane) {
					System.out.println("resize");
					content.remove(centerMenu);
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
		int numOnLine = width / 160 ;
		int res = (width - (numOnLine)*10 - 20) / 160 ;	// 20 correction scrollbar's width
		nbOnLineScrollPane = res;
		
		for(Piece piece : Application.quincaillerie.getCatalogue().getCatalogue()) {
			
			JPanel panelPiece = panelPiece(piece);
			
			gbc.gridx = n % res;
			gbc.gridy = (int) n / res;
			gbc.insets = new Insets(5, 5, 5, 5);
			listePieces.add(panelPiece, gbc);
			n++;
		}
		jsp = new JScrollPane(listePieces, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		panel.add(jsp, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelPiece(Piece piece) {

		JPanel panelPiece = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelPiece.setPreferredSize(new Dimension(160, 180));
		panelPiece.setMinimumSize(new Dimension(160, 180));
		panelPiece.setMaximumSize(new Dimension(160, 180));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JPanel image = imagePiece(piece);
		panelPiece.add(image, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPiece.add(new JLabel(piece.getNom()), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelPiece.add(new JLabel(Double.toString(piece.prix()) + " €"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		JButton btnDetail = new JButton("Détail");
		btnDetail.addActionListener(ev->{
			
			System.out.println("détail piece");
			JDialog detailPiece = detailPiece(piece);

			detailPiece.setSize(new Dimension(250, 230));
			detailPiece.setLocationRelativeTo(null);
			detailPiece.setVisible(true);
			
		
		});
		panelPiece.add(btnDetail, gbc);
	
		return panelPiece;
	}
	
	private JPanel imagePiece(Piece p) {
		JPanel panel = new JPanel();
		JLabel image = null;
		
		if(p.getNom().equals("vis")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "vis.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("rayon")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "rayon_velo.jpg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("roue de vélo")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "roue_velo.jpg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("disque de jante")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "disque_jante.jpg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("chambre à air")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "chambre_air.jpg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("pneu")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "pneu_velo.jpg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("ampoule")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "ampoule.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}else if(p.getNom().equals("pommeau de douche")) {
			image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "pommeau_douche.png").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		}

		panel.add(image);
		return panel;
	}
	
	private void refreshPanier() {
		detailPanier.getContentPane().removeAll();
		detailPiecesPanier(detailPanier);
		String nbArt = Integer.toString(Application.panier.nbArticles());
		String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
		pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
		pnlBtnCart.revalidate();
		pnlBtnCart.repaint();	
		detailPanier.revalidate();
		detailPanier.repaint();		
	}
	
	private void detailPiecesPanier(JDialog detailPanier) {
		JPanel content = (JPanel) detailPanier.getContentPane();
		content.setLayout(new BorderLayout());
		
		content.removeAll();
		
		JPanel listeArticles = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(Piece p : Application.panier.getPanier().keySet()) {
			
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.insets = new Insets(15, 0, 0, 0);
			
			JPanel piece = new JPanel(new BorderLayout());
			piece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			piece.setPreferredSize(new Dimension(300, 25));
			GridBagConstraints gbcPiece = new GridBagConstraints();
			
			gbcPiece.gridx = 0;
			gbcPiece.gridy = 0;
			gbcPiece.fill = GridBagConstraints.HORIZONTAL;
			JPanel pP = new JPanel(new FlowLayout(FlowLayout.CENTER));
			pP.add(new JLabel(p.getRef() + " : " + p.getNom() + " x"+Integer.toString(Application.panier.getPanier().get(p))+" "));
			piece.add(pP, BorderLayout.WEST);
			
			gbcPiece.gridx = 1;
			JPanel pB = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton btnSuppr = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "delete_icon.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
			btnSuppr.addActionListener(ev->{
				Application.panier.supprimePanier(p);
				refreshPanier();
			});
			pB.setSize(new Dimension(30, 30));
			pB.setPreferredSize(new Dimension(30, 30));
			pB.setMaximumSize(new Dimension(30, 30));
			pB.add(btnSuppr);
			piece.add(btnSuppr, BorderLayout.EAST);
			
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
			
			gbc.gridy = n+1;
			JPanel pRed = new JPanel(new BorderLayout());
			JPanel red = new JPanel(new FlowLayout(FlowLayout.LEFT));
			red.add(new JLabel("Réduction : "));
			JPanel montantR = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			montantR.add(new JLabel(String.format("%.2f", reduction) + " €"));
			pRed.add(red, BorderLayout.CENTER);
			pRed.add(montantR, BorderLayout.EAST);
			listeArticles.add(pRed, gbc);
			
			gbc.gridy = n+2;
			JPanel pNTot = new JPanel(new BorderLayout());
			JPanel ntot = new JPanel(new FlowLayout(FlowLayout.LEFT));
			ntot.add(new JLabel("Total : "));
			JPanel montantNT = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			montantNT.add(new JLabel(String.format("%.2f", total) + " €"));
			pNTot.add(ntot, BorderLayout.CENTER);
			pNTot.add(montantNT, BorderLayout.EAST);
			listeArticles.add(pNTot, gbc);
			
			gbc.gridy = n+3;
			JPanel pComm = new JPanel(new BorderLayout());
			JButton btnCommander = new JButton("Commander");
			btnCommander.addActionListener(ev->{
				System.out.println("Passer la commande");
			});
			pComm.add(btnCommander, BorderLayout.EAST);
			
			listeArticles.add(pComm, gbc);
		}	
				
		jsp = new JScrollPane(listeArticles, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		content.add(jsp);
	}
	
	private JDialog detailPanier() {
		detailPanier = new JDialog(frmDetailPanier, "Panier");
		detailPiecesPanier(detailPanier);
		return detailPanier;
	}	
	
	private JDialog detailPiece(Piece piece) {
		JDialog detailPiece = new JDialog(frmDetailPiece, piece.getNom());
		
		JPanel content = (JPanel) detailPiece.getContentPane();
		content.setLayout(new BorderLayout());
		
		JTextArea dp = new JTextArea(piece.toString());
		content.add(dp, BorderLayout.CENTER);
		
		JPanel spinnerBtn = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		int max = Application.quincaillerie.getStocks().stocksPiece(piece);
		SpinnerModel model = new SpinnerNumberModel(1, 0, max, 1);
		JSpinner spNb = new JSpinner(model);
		spinnerBtn.add(spNb);
		
		gbc.gridy = 1;
		JButton ajoutPanier = new JButton("Ajouter au panier");
		ajoutPanier.addActionListener(ev->{
			System.out.println("Ajout de " + spNb.getValue());
			Application.panier.ajoutPanier(piece, (Integer) spNb.getValue());
			String nbArt = Integer.toString(Application.panier.nbArticles());
			String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
			pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
			pnlBtnCart.revalidate();
			pnlBtnCart.repaint();			
			
		});
		
		spinnerBtn.add(ajoutPanier);
		content.add(spinnerBtn, BorderLayout.SOUTH);
		
		return detailPiece;
	}
	
	private JPanel northPanelClient() {
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.add(createBtnReturn());
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
		pnlButtons.add(pnlBtnModifier(fields, pnlButtons), BorderLayout.CENTER);
		
		p.add(panelFields, BorderLayout.CENTER);		
		p.add(pnlButtons, BorderLayout.SOUTH);
		
		content.add(p);
	
		return modifInfos;
	}
	
	private JPanel pnlBtnModifier(JComponent[] fields, JPanel pnlButtons) {
		JButton btnModifierInfos = new JButton("Modifier");
		JPanel pnlBtnModif = new JPanel();
		btnModifierInfos.addActionListener(ev->{
			for(JComponent jc : fields) {
				if(jc.getComponent(0) instanceof JTextField) {
					((JTextField) jc.getComponent(0)).setEnabled(true);
				}else if(jc.getComponent(0) instanceof JRadioButton){
					((JRadioButton) jc.getComponent(0)).setEnabled(true);
					((JRadioButton) jc.getComponent(1)).setEnabled(true);

				}
				System.out.println(jc.getComponent(0).getClass().getName());
				

			}
			pnlButtons.removeAll();
			pnlButtons.add(pnlBtnsValiderAnnuler(fields, pnlButtons), BorderLayout.CENTER);
			pnlButtons.repaint();
			pnlButtons.revalidate();
		});
		pnlBtnModif.add(btnModifierInfos);
		
		return pnlBtnModif;
	}
	
	private JPanel pnlBtnsValiderAnnuler(JComponent[] fields, JPanel pnlButtons) {
		JButton btnValider = new JButton("Valider");
		JButton btnAnnuler = new JButton("Annuler");
		JPanel pnlBtnVA = new JPanel(new BorderLayout());
		
		btnValider.addActionListener(ev->{
			System.out.println("valider");
			pnlButtons.removeAll();
			pnlButtons.add(pnlBtnModifier(fields, pnlButtons), BorderLayout.CENTER);
			pnlButtons.repaint();
			pnlButtons.revalidate();
		});
		
		btnAnnuler.addActionListener(ev->{
			System.out.println("annuler");
			pnlButtons.removeAll();
			pnlButtons.add(pnlBtnModifier(fields, pnlButtons), BorderLayout.CENTER);
			pnlButtons.repaint();
			pnlButtons.revalidate();
		});
		
		
		pnlBtnVA.add(btnValider, BorderLayout.WEST);
		pnlBtnVA.add(btnAnnuler, BorderLayout.EAST);
		
		return pnlBtnVA;
	}
	
	private JDialog modifInfosEntr() {
		modifInfos = new JDialog(frmModifInfos, "Mes informations");
		JPanel content = (JPanel) modifInfos.getContentPane();
		
		JLabel[] labels = {	new JLabel("Siège social :"), new JLabel("Nom commercial :"), new JLabel("Catégorie :"), new JLabel("Adresse :"), 
							new JLabel("Téléphone :"), new JLabel("Email :")};
		JComponent[] fields = {	inputFieldInfosEntr("SiègeSocial"), inputFieldInfosEntr("NomCommercial"), radioButtonPanelInfosEntr("Catégorie"), 
								inputFieldInfosEntr("Adresse"), inputFieldInfosEntr("Téléphone"), inputFieldInfosEntr("Email")};
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(JLabel label : labels) {
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.EAST;
			panel.add(label, gbc);
			n++;
		}
		
		n = 0;
		for(JComponent field : fields) {
			gbc.gridx = 1;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.WEST;
			panel.add(field, gbc);
			n++;
		}
		
		return modifInfos;
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
		if(s.equals("Adresse")) {
			t = new JTextField(30);
		}else if(s.equals("Téléphone")) {
			t = new JTextField(10);
		}else if(s.equals("Email")) {
			t = new JTextField(30);
		}else if(s.equals("NomCommercial")) {
			t = new JTextField(20);
		}else if(s.equals("SiègeSocial")) {
			t = new JTextField(30);
		}
		
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
		if(s.equals("Catégorie")) {
			b1 = new JRadioButton("GE");
			b2 = new JRadioButton("ETI");
			b3 = new JRadioButton("PME");
			b4 = new JRadioButton("TPE");
		}
		
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
		btnInfos = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "info_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnInfos.addActionListener(ev->{
			System.out.println("Informations");
			if(Application.clientCourant instanceof Particulier) {
				modifInfos = modifInfosPart();
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
		btnCommandes = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "order_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnCommandes.addActionListener(ev->{ System.out.println("Commandes"); });
		pnlBtnOrder.add(btnCommandes);
		return pnlBtnOrder;
	}
	
	private JPanel createBtnCart() {
		pnlBtnCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanier = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "cart_icon.png").getImage().getScaledInstance(30, 22, Image.SCALE_SMOOTH)));
		btnPanier.addActionListener(ev->{
			System.out.println(Application.panier);
			JDialog detailPanier = detailPanier();
			detailPanier.setSize(400, 400);
			detailPanier.setLocationRelativeTo(null);
			detailPanier.setVisible(true);
		});
		String nbArt = Integer.toString(Application.panier.nbArticles());
		String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
		pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
		pnlBtnCart.add(btnPanier);
		return pnlBtnCart;
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuClientConnexion.demarrer(frmClientCatalogue);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
}
