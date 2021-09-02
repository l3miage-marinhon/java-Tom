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


public class MenuNewClient implements Runnable{

	public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmNewClient;
	JButton btnReturn;
	JButton btnNewClient;
	JButton btnExistingClient;
			
	public MenuNewClient() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewClient());
		previousFrm.dispose();
	}
	
	public static void demarrer(JFrame frmNewClient, JFrame previousFrm) {
		frmNewClient.setVisible(true);
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmNewClient = new JFrame();
		frmNewClient.setSize(new Dimension(500, 300));
		frmNewClient.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmNewClient.setLocationRelativeTo(null);
		frmNewClient.setMinimumSize(new Dimension(350, 130));
		frmNewClient.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmNewClient, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmNewClient.dispose();
					System.exit(0);
				}
			}
		});
		frmNewClient.setVisible(true);
		
		frmNewClient.setTitle("Créer un compte client");
		JPanel content = (JPanel) frmNewClient.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(createBtnReturn(), BorderLayout.NORTH);
		content.add(createInfoInputMenu(), BorderLayout.CENTER);
		content.add(Application.version(), BorderLayout.SOUTH);
	}
	
	private JPanel createInfoInputMenu() {
		JPanel menuInfo = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 4, 0);
		JButton btnPart = new JButton("Je suis un particulier");
		btnPart.setPreferredSize(new Dimension(180, 35));
		btnPart.addActionListener(ev->{MenuNewClientPart.demarrer(frmNewClient);});
		menuInfo.add(btnPart, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		JButton btnEn = new JButton("Je suis un professionnel");
		btnEn.setPreferredSize(new Dimension(180, 35));
		btnEn.addActionListener(ev->{MenuNewClientEntr.demarrer(frmNewClient);});
		menuInfo.add(btnEn, gbc);
		return menuInfo;
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuClientKnownUnknown.demarrer(frmNewClient);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
	
	
}
