package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import main.Application;

public class MenuPrincipal implements Runnable{

	JFrame frmMenuPrinc;
	JButton btnClient;
	JButton btnQuinc;
	
	
	public static void demarrer() {
		SwingUtilities.invokeLater(new MenuPrincipal());
	}
	
	public static void demarrer(JFrame frmMenuPrinc, JFrame previousFrm) {
		frmMenuPrinc.setVisible(true);
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
				
		frmMenuPrinc = new JFrame();
		frmMenuPrinc.setSize(new Dimension(500, 300));
		frmMenuPrinc.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmMenuPrinc.setLocationRelativeTo(null);
		frmMenuPrinc.setMinimumSize(new Dimension(350, 130));
		frmMenuPrinc.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmMenuPrinc, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmMenuPrinc.dispose();
					System.exit(0);
				}
			}
		});
		frmMenuPrinc.setVisible(true);
		
		frmMenuPrinc.setTitle("Application TP Gestion d'une quincaillerie");
		JPanel content = (JPanel) frmMenuPrinc.getContentPane();
		content.setLayout(new BorderLayout());
		JPanel nomQuinc = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nomQuinc.add(new JLabel(Application.quincaillerie.getNom()));
		content.add(nomQuinc, BorderLayout.NORTH);
		content.add(createMenu(), BorderLayout.CENTER);
		content.add(version(), BorderLayout.SOUTH);
	}
	
	
	
	private JPanel createMenu() {
		JPanel menu = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		btnClient = new JButton("CLIENT");
		btnClient.setPreferredSize(new Dimension(150, 50));
		btnClient.addActionListener(ev->{MenuClientKnownUnknown.demarrer(frmMenuPrinc);});
		menu.add(btnClient, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnQuinc = new JButton("QUINCAILLERIE");
		btnQuinc.setPreferredSize(new Dimension(150, 50));
		btnQuinc.addActionListener(ev->{System.out.println("quincaillerie");});
		menu.add(btnQuinc, gbc);

		return menu;
	}
	
	private JPanel version() {
		JPanel version = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		version.add(new JLabel("Version : Alpha-1")); 
		return version;
	}
	
}
