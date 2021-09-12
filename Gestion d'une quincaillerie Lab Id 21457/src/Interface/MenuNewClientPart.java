package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import clients.Particulier;
import main.Application;

public class MenuNewClientPart implements Runnable{
		
	JFrame frmNewClientPart;
	JButton btnReturn;
	
	public MenuNewClientPart() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewClientPart());
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmNewClientPart = new JFrame();
		frmNewClientPart.setSize(new Dimension(550, 450));
		frmNewClientPart.setResizable(false);
		frmNewClientPart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmNewClientPart.setLocationRelativeTo(null);
		frmNewClientPart.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmNewClientPart, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmNewClientPart.dispose();
					System.exit(0);
				}
			}
		});
		frmNewClientPart.setVisible(true);
		
		frmNewClientPart.setTitle("Particulier");
		JPanel content = (JPanel) frmNewClientPart.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(Application.createBtnReturn(frmNewClientPart, MenuNewClient.class), BorderLayout.NORTH);
		
		
		JLabel[] labels = {	new JLabel("Civilité :"), new JLabel("Nom :"), new JLabel("Prénom :"), new JLabel("Adresse :"), 
							new JLabel("Téléphone :"), new JLabel("Email :"), new JLabel("Fidélité :")};
		JComponent[] fields = {	radioButtonPanel("Civilité"), inputField("Nom"), inputField("Prénom"), inputField("Adresse"), 
								inputField("Téléphone"), inputField("Email"), radioButtonPanel("Fidélité")};
		
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.add(partMenu(labels, fields));
		menu.add(valider(labels, fields));
		content.add(menu, BorderLayout.CENTER);
		
		
	}
	
	private JPanel partMenu(JLabel[] labels, JComponent[] fields) {
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
	
	private JPanel valider(JLabel[] labels, JComponent[] fields) {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		layout.setVgap(30);
		JPanel p = new JPanel(layout);
		JButton btnValider = new JButton("Valider");
		p.add(btnValider);
		btnValider.addActionListener(ev->{
				
			Object part = FormValidation.validerInfosParticulier(labels, fields, true);
			if(part instanceof Particulier && part != null) {
				Application.quincaillerie.ajouterClient((Particulier) part);
				Application.quincaillerie.afficheClients();
				JOptionPane.showMessageDialog(null, "Enregistrement réussi !");
				MenuClientConnexion.demarrer(frmNewClientPart);
			}
		});
		
		return p;
	}
	
	private JPanel inputField(String s) {
		JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField t = null;
		if(s.equals("Adresse")) {
			t = new JTextField(30);
		}else if(s.equals("Téléphone")) {
			t = new JTextField(10);
		}else if(s.equals("Email")) {
			t = new JTextField(30);
		}else if(s.equals("Nom")) {
			t = new JTextField(20);
		}else if(s.equals("Prénom")) {
			t = new JTextField(20);
		}
		field.add(t);
		return field;
	}
	
	private JPanel radioButtonPanel(String s) {
		JPanel radioPanel = new JPanel(new GridLayout(1,2));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = null;
		JRadioButton b2 = null;
		if(s.equals("Civilité")) {
			b1 = new JRadioButton("Monsieur");
			b2 = new JRadioButton("Madame");
		}else if(s.equals("Fidélité")) {
			b1 = new JRadioButton("Oui");
			b2 = new JRadioButton("Non");
		}
		
		btnGrp.add(b1);
		btnGrp.add(b2);

		radioPanel.add(b1);
		radioPanel.add(b2);
		
		return radioPanel;
	}

}
