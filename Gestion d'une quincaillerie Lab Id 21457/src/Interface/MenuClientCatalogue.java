package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;
import pieces.Piece;

public class MenuClientCatalogue implements Runnable{

public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmClientCatalogue;
	JFrame previousFrm;
	JButton btnReturn;
	JButton btnInfos;
	JButton btnCommandes;
	JButton btnPanier;
	JPanel listePieces;
	JScrollPane jsp;
	JPanel westMenu;
	JPanel centerMenu;
	int nbOnLineScrollPane;
	JButton btnValider;
			
	public MenuClientCatalogue(JFrame previousFrm) {
		this.previousFrm = previousFrm;
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuClientCatalogue(previousFrm));
		previousFrm.setVisible(false);
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
		frmClientCatalogue.setSize(new Dimension(900, 600));
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
				int numOnLine = width / 150;
				int res = (width - (numOnLine)*10 - 20) / 150 ;	// 20 correction scrollbar's width
				if(res != nbOnLineScrollPane) {
					System.out.println("resize");
					content.remove(centerMenu);
					centerMenu = catalogueMenu();
					content.add(centerMenu, BorderLayout.CENTER);
					centerMenu.repaint();
					centerMenu.revalidate();
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
		int numOnLine = width / 150 ;
		int res = (width - (numOnLine)*10 - 20) / 150 ;	// 20 correction scrollbar's width
		nbOnLineScrollPane = res;
		
		for(Piece piece : Application.quincaillerie.getCatalogue().getCatalogue()) {
			
			JPanel panelPiece = panelPiece(piece);
			
			gbc.gridx = n % res;
			gbc.gridy = (int) n / res;
			gbc.insets = new Insets(5, 5, 5, 5);
			
			/*
			JPanel panelPiece = new JPanel();
			panelPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panelPiece.setPreferredSize(new Dimension(150, 120));
			panelPiece.setMinimumSize(new Dimension(150, 120));
			panelPiece.setMaximumSize(new Dimension(150, 120));
			panelPiece.add(new JLabel(piece.getNom()));
			panelPiece.add(new JLabel(piece.getRef()));
			String t[] = piece.getClass().getName().split("\\.");
			
			panelPiece.add(new JLabel(t[1]));
			*/
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
		panelPiece.setPreferredSize(new Dimension(150, 120));
		panelPiece.setMinimumSize(new Dimension(150, 120));
		panelPiece.setMaximumSize(new Dimension(150, 120));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelPiece.add(new JLabel(piece.getNom()), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelPiece.add(new JLabel(Double.toString(piece.prix())), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		JButton btnDetail = new JButton("Détail");
		btnDetail.addActionListener(ev->{System.out.println("détail piece");});
		panelPiece.add(btnDetail, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		JLabel image = new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "image_vis.jpeg").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panelPiece.add(image, gbc);
		
		return panelPiece;
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
		content.add(inputField("Nom"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		content.add(new JLabel("Reférence :"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		content.add(inputField("Ref"), gbc);
		
		
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
	
	private JPanel inputField(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		if(s.equals("Nom")) {
			//field.setBorder(BorderFactory.createTitledBorder("Nom"));
			t = new JTextField(8);
		}else if(s.equals("Ref")) {
			t = new JTextField(8);
			//field.setBorder(BorderFactory.createTitledBorder("Reférence"));
		}
		
		field.add(t);
		return field;
	}
	
	private JPanel createBtnInfo() {
		JPanel pnlBtnInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnInfos = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "info_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnInfos.addActionListener(ev->{System.out.println("Informations");;});
		pnlBtnInfo.add(btnInfos);
		return pnlBtnInfo;
	}
	
	private JPanel createBtnOrder() {
		JPanel pnlBtnOrder = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnCommandes = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "order_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnCommandes.addActionListener(ev->{ System.out.println("Commandes"); });
		pnlBtnOrder.add(btnCommandes);
		return pnlBtnOrder;
	}
	
	private JPanel createBtnCart() {
		JPanel pnlBtnCart = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanier = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "cart_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnPanier.addActionListener(ev->{System.out.println("Panier");;});
		pnlBtnCart.add(btnPanier);
		return pnlBtnCart;
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuClientConnexion.demarrer(previousFrm, frmClientCatalogue);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
}
