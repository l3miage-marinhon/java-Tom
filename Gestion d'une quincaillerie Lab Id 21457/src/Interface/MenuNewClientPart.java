package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import clients.Civilite;
import clients.Particulier;
import main.Application;

public class MenuNewClientPart implements Runnable{
	
	public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmNewClientPart;
	JFrame previousFrm;
	JButton btnReturn;
	
	public MenuNewClientPart(JFrame previousFrm) {
		this.previousFrm = previousFrm;
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewClientPart(previousFrm));
		previousFrm.setVisible(false);
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
		content.add(createBtnReturn(), BorderLayout.NORTH);
		content.add(Application.version(), BorderLayout.SOUTH);
		
		
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
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = fields.length;
				int i=0;
				
				Civilite civilite = null;
				String nom = null;
				String prenom = null;
				String adresse = null;
				String tel = null;
				String email = null;
				boolean fid = false;
				
				String value;
				boolean correct = true;
				
				while(i < size && correct) {
					if(fields[i].getComponent(0) instanceof JTextField) {
						value = ((JTextField) fields[i].getComponent(0)).getText();
						if(value.isBlank()) {
							correct = false;
						}else if(labels[i].getText().equals("Nom :")) {
							nom = value;
						}else if(labels[i].getText().equals("Prénom :")) {
							prenom = value;
						}else if(labels[i].getText().equals("Adresse :")) {
							adresse = value;
						}else if(labels[i].getText().equals("Téléphone :")) {
							if(Pattern.matches("0\\d{9}", value)) {
								tel = value;
							}else {
								correct = false;
								JOptionPane.showMessageDialog(null, "Numéro de téléphone incorrect");
							}
						}else if(labels[i].getText().equals("Email :")) {
							if(!Application.quincaillerie.mailDisponible(value)) {
								correct = false;
								JOptionPane.showMessageDialog(null, "Email indisponible");
							}else if(!Pattern.matches("[\\w_.-]+@[a-z]+.(fr|com)", value)) {
								correct = false;
								JOptionPane.showMessageDialog(null, "Email incorrect");
							}else {
								email = value;
							}
						}
					}else if(fields[i].getComponent(0) instanceof JRadioButton) {
						if(labels[i].getText().equals("Civilité :")) {
							if(((JRadioButton) fields[i].getComponent(0)).isSelected()){
								civilite = Civilite.MONSIEUR;
							}else if(((JRadioButton) fields[i].getComponent(1)).isSelected()) {
								civilite = Civilite.MADAME;
							}else {
								correct = false;
								JOptionPane.showMessageDialog(null, "Veuillez renseigner votre civilité");
							}
						}else if(labels[i].getText().equals("Fidélité :")) {
							if(((JRadioButton) fields[i].getComponent(0)).isSelected()) {
								fid = true;
							}else if(( (JRadioButton) fields[i].getComponent(1)).isSelected()) {
								fid = false;
							}else {
								correct = false;
								JOptionPane.showMessageDialog(null, "Veuillez renseigner votre fidélisation");
							}
						}
					}
					i++;
				}
				
				if(correct) {
					System.out.println(tel);
					String id = Application.quincaillerie.idNouveauClient(true);
					Application.quincaillerie.ajouterClient(new Particulier(id, adresse, tel, email, 100, civilite, nom, prenom, fid));
					Application.quincaillerie.afficheClients();
				}else {
					System.out.println("Erreur saisie");
				}
			}
		});
		
		return p;
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuNewClient.demarrer(previousFrm, frmNewClientPart);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
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
		}else {
			System.out.println("aaaaaaaaaaaaa");
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
			//b1.addActionListener(ev->{civilite = Civilite.MONSIEUR;});
			b2 = new JRadioButton("Madame");
			//b2.addActionListener(ev->{civilite = Civilite.MADAME;});
		}else if(s.equals("Fidélité")) {
			b1 = new JRadioButton("Oui");
			//b1.addActionListener(ev->{fid = true;});
			b2 = new JRadioButton("Non");
			//b2.addActionListener(ev->{fid = false;});
		}
		
		btnGrp.add(b1);
		btnGrp.add(b2);

		radioPanel.add(b1);
		radioPanel.add(b2);
		
		return radioPanel;
	}

}
