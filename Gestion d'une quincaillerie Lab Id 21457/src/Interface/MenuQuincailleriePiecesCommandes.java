package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;

public class MenuQuincailleriePiecesCommandes implements Runnable {

	public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmQuincPiecesCommandes;
	JButton btnReturn;
	JButton btnPieces;
	JButton btnCommandes;
			
	public MenuQuincailleriePiecesCommandes() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuQuincailleriePiecesCommandes());
		previousFrm.dispose();
	}

	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmQuincPiecesCommandes = new JFrame();
		frmQuincPiecesCommandes.setSize(new Dimension(500, 300));
		frmQuincPiecesCommandes.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmQuincPiecesCommandes.setLocationRelativeTo(null);
		frmQuincPiecesCommandes.setMinimumSize(new Dimension(350, 130));
		frmQuincPiecesCommandes.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmQuincPiecesCommandes, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmQuincPiecesCommandes.dispose();
					System.exit(0);
				}
			}
		});
		frmQuincPiecesCommandes.setVisible(true);
		
		frmQuincPiecesCommandes.setTitle("Menu quincaillerie");
		JPanel content = (JPanel) frmQuincPiecesCommandes.getContentPane();
		
		content.setLayout(new BorderLayout());
		content.add(createBtnReturn(), BorderLayout.NORTH);
		content.add(createMenu(), BorderLayout.CENTER);
		content.add(Application.version(), BorderLayout.SOUTH);
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuPrincipal.demarrer(frmQuincPiecesCommandes);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
	
	private JPanel createMenu() {
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		btnPieces = new JButton("Pièces");
		btnPieces.setPreferredSize(new Dimension(150, 50));
		btnPieces.addActionListener(ev->{MenuQuincailleriePieces.demarrer(frmQuincPiecesCommandes);});
		menu.add(btnPieces, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnCommandes = new JButton("Commandes");
		btnCommandes.setPreferredSize(new Dimension(150, 50));
		btnCommandes.addActionListener(ev->{MenuQuincaillerieCommandes.demarrer(frmQuincPiecesCommandes);});
		menu.add(btnCommandes, gbc);

		return menu;
	}
}
