package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;

public class MenuClientConnexion implements Runnable{
		
	JFrame frmClientConn;
	JButton btnReturn;
	JButton btnValider;
			
	public MenuClientConnexion() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuClientConnexion());
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmClientConn = new JFrame();
		frmClientConn.setSize(new Dimension(500, 300));
		frmClientConn.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmClientConn.setLocationRelativeTo(null);
		frmClientConn.setMinimumSize(new Dimension(350, 130));
		frmClientConn.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmClientConn, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmClientConn.dispose();
					System.exit(0);
				}
			}
		});
		frmClientConn.setVisible(true);
		
		frmClientConn.setTitle("Connexion");
		JPanel content = (JPanel) frmClientConn.getContentPane();
		content.setLayout(new BorderLayout());
		
		JLabel[] labels = {	new JLabel("Email :"), new JLabel("Mot de passe :")};
		JComponent[] fields = {	inputField("Email"), inputField("MotDePasse")};
		
		
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		
		menu.add(connMenu(labels, fields));
		menu.add(valider(fields));
		
		content.add(Application.createBtnReturn(frmClientConn, MenuClientKnownUnknown.class), BorderLayout.NORTH);
		content.add(menu, BorderLayout.CENTER);
	}
	
	private JPanel connMenu(JLabel[] labels, JComponent[] fields) {
		if (labels.length != fields.length) {
	    	String s = labels.length + " labels supplied for " + fields.length + " fields!";
	        throw new IllegalArgumentException(s);
		}
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
		 
		return panel;
	}
	
	private JPanel valider(JComponent[] fields) {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setVgap(30);
		JPanel p = new JPanel(layout);
		btnValider = new JButton("Valider");
		p.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				String email = null;
				String password = null;
				
				boolean correct = true;
				
				String value = ((JTextField) fields[0].getComponent(0)).getText();
				if(value.isBlank()) {
					correct = false;
				}else {	
					email = value;
					
					value = new String( ((JPasswordField) fields[1].getComponent(0)).getPassword());
					if(value.isBlank()) {
						correct = false;
					}else {
						password = value;
					}
				}
				
				if(correct) {
					Application.clientCourant = Application.quincaillerie.connexionClient(email, password);
					if(Application.clientCourant == null) {
						JOptionPane.showMessageDialog(null, "Echec de connexion");
					}else {
						System.out.println("Le client \n" + Application.clientCourant.getEmail() + "\n est bien connecté");
						MenuClientCatalogue.demarrer(frmClientConn);
					}
				}
			}
		});
		
		return p;
	}
	
	private JPanel inputField(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		if(s.equals("Email")) {
			t = new JTextField(20);
		}else if(s.equals("MotDePasse")) {
			t = new JPasswordField(20);
		}
		
		field.add(t);
		return field;
	}
	
}
