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


public class MenuClientKnownUnknown implements Runnable{
	//FIXIT pourquoi ? ici une fois et dans l'application , pas ici x
	public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmClientKnUk;
	JButton btnReturn;
	JButton btnNewClient;
	JButton btnExistingClient;
			
	public MenuClientKnownUnknown() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuClientKnownUnknown());
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmClientKnUk = new JFrame();
		frmClientKnUk.setSize(new Dimension(500, 300));
		frmClientKnUk.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmClientKnUk.setLocationRelativeTo(null);
		frmClientKnUk.setMinimumSize(new Dimension(350, 130));
		frmClientKnUk.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmClientKnUk, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmClientKnUk.dispose();
					System.exit(0);
				}
			}
		});
		frmClientKnUk.setVisible(true);
		
		frmClientKnUk.setTitle("Menu client");
		JPanel content = (JPanel) frmClientKnUk.getContentPane();
		
		content.setLayout(new BorderLayout());
		content.add(createBtnReturn(), BorderLayout.NORTH);
		content.add(createMenu(), BorderLayout.CENTER);
		content.add(Application.version(), BorderLayout.SOUTH);
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuPrincipal.demarrer(frmClientKnUk);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
	
	private JPanel createMenu() {
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		btnNewClient = new JButton("Créer un compte");
		btnNewClient.setPreferredSize(new Dimension(150, 50));
		btnNewClient.addActionListener(ev->{MenuNewClient.demarrer(frmClientKnUk);});
		menu.add(btnNewClient, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnExistingClient = new JButton("Se connecter");
		btnExistingClient.setPreferredSize(new Dimension(150, 50));
		btnExistingClient.addActionListener(ev->{MenuClientConnexion.demarrer(frmClientKnUk);});
		menu.add(btnExistingClient, gbc);

		return menu;
	}
}

