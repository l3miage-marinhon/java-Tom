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
import java.util.Vector;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;
import pieces.Piece;

public class MenuQuincailleriePieces implements Runnable {
	
	public static final String PATH_TO_ICONS = "src/icons/";

	JFrame frmQuincailleriePieces;
	JFrame frmRachatStocks;
	JPanel centerMenu;
	JButton btnReturn;
	JButton btnAchatPieces;
	int nbOnLineScrollPane;


	public MenuQuincailleriePieces(JFrame previousFrm) {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuQuincailleriePieces(previousFrm));
		previousFrm.dispose();
	}
	
	public static void demarrer(JFrame frmQuincailleriePieces, JFrame previousFrm) {
		frmQuincailleriePieces.setVisible(true);
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frmQuincailleriePieces = new JFrame();
		frmQuincailleriePieces.setSize(new Dimension(1200, 800));
		frmQuincailleriePieces.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmQuincailleriePieces.setLocationRelativeTo(null);
		frmQuincailleriePieces.setMinimumSize(new Dimension(500, 300));
		frmQuincailleriePieces.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmQuincailleriePieces, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmQuincailleriePieces.dispose();
					System.exit(0);
				}
			}
		});
		
		frmQuincailleriePieces.setVisible(true);
		
		frmQuincailleriePieces.setTitle("Gestion des pièces");
		JPanel content = (JPanel) frmQuincailleriePieces.getContentPane();
		content.setLayout(new BorderLayout());
		
		
		JPanel northMenu = northPanelClient();
		content.add(northMenu, BorderLayout.NORTH);
		
		centerMenu = piecesMenu();
		content.add(centerMenu, BorderLayout.CENTER);
		
		frmQuincailleriePieces.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int width = (int) (frmQuincailleriePieces.getSize().getWidth() - /*westMenu.getWidth()*/ 50);
				int numOnLine = width / 180;
				int res = (width - (numOnLine)*10 - 20) / 180 ;	// 20 correction scrollbar's width
				if(res != nbOnLineScrollPane) {
					System.out.println("resize");
					content.remove(centerMenu);
					centerMenu = piecesMenu();
					content.add(centerMenu, BorderLayout.CENTER);
				}
			}
		});
	}
	
	private JPanel northPanelClient() {
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftPanel.add(createBtnReturn());
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		/*
		rightPanel.add(createBtnInfo());
		
		rightPanel.add(createBtnCart());
		*/
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(rightPanel, BorderLayout.EAST);
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel piecesMenu() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel listePieces = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		int width = (int) (frmQuincailleriePieces.getSize().getWidth() - /*westMenu.getWidth()*/ 40);
		int numOnLine = width / 180 ;
		int res = (width - (numOnLine)*10 - 20) / 180 ;	// 20 correction scrollbar's width
		nbOnLineScrollPane = res;
		
		for(Piece piece : Application.quincaillerie.getCatalogue().getCatalogue()) {
			JPanel panelPiece = panelPiece(piece);
			gbc.gridx = n % res;
			gbc.gridy = (int) n / res;
			gbc.insets = new Insets(5, 5, 5, 5);
			listePieces.add(panelPiece, gbc);
			n++;
		}
		JScrollPane jsp = new JScrollPane(listePieces, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		panel.add(jsp, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel panelPiece(Piece piece) {

		JPanel panelPiece = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelPiece.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelPiece.setPreferredSize(new Dimension(180, 200));
		panelPiece.setMinimumSize(new Dimension(180, 200));
		panelPiece.setMaximumSize(new Dimension(180, 200));
		
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
		JPanel pnlAddInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlAdd = new JPanel(new FlowLayout(FlowLayout.LEFT));
		SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spNb = new JSpinner(model);
		pnlAdd.add(spNb);
		
		JButton ajoutPanier = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "addcart_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		ajoutPanier.addActionListener(ev->{
			int nb = (int) spNb.getValue();
			System.out.println("Ajout de " + nb);
			int clickedButton = JOptionPane.showConfirmDialog(frmQuincailleriePieces, "Ajouter " + nb + " pièces ?", "", JOptionPane.YES_NO_OPTION);
			if(clickedButton == JOptionPane.YES_OPTION) {
				Application.quincaillerie.getStocks().augmenteStocksPiece(piece, (int) spNb.getValue());
			}
		});
		pnlAdd.add(ajoutPanier);
		
		JPanel pnlInfos = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlInfos.setToolTipText("<html>"+ piece.toStringHTML() +"</html>");
		pnlInfos.add(new JLabel(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "detail_icon.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH))));
		
		pnlAddInfos.add(pnlAdd);
		pnlAddInfos.add(pnlInfos);
		
		panelPiece.add(pnlAddInfos, gbc);
	
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
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuQuincailleriePiecesCommandes.demarrer(frmQuincailleriePieces);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
}
