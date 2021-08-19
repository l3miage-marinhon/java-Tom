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

import clients.CategorieEntreprise;
import clients.Entreprise;
import main.Application;

public class MenuNewClientEntr implements Runnable{

public static final String PATH_TO_ICONS = "src/icons/";
	
	JFrame frmNewClientEntr;
	JFrame previousFrm;
	JButton btnReturn;
	
	public MenuNewClientEntr(JFrame previousFrm) {
		this.previousFrm = previousFrm;
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewClientEntr(previousFrm));
		previousFrm.setVisible(false);
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmNewClientEntr = new JFrame();
		frmNewClientEntr.setSize(new Dimension(550, 450));
		frmNewClientEntr.setResizable(true);
		frmNewClientEntr.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmNewClientEntr.setLocationRelativeTo(null);
		frmNewClientEntr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmNewClientEntr, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmNewClientEntr.dispose();
					System.exit(0);
				}
			}
		});
		frmNewClientEntr.setVisible(true);
		
		frmNewClientEntr.setTitle("Entreprise");
		JPanel content = (JPanel) frmNewClientEntr.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(createBtnReturn(), BorderLayout.NORTH);
		content.add(Application.version(), BorderLayout.SOUTH);
		
		
		JLabel[] labels = {	new JLabel("Siège social :"), new JLabel("Nom commercial :"), new JLabel("Catégorie :"), new JLabel("Adresse :"), 
							new JLabel("Téléphone :"), new JLabel("Email :")};
		JComponent[] fields = {	inputField("SiègeSocial"), inputField("NomCommercial"), radioButtonPanel("Catégorie"), inputField("Adresse"), 
								inputField("Téléphone"), inputField("Email")};
		
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.add(entrMenu(labels, fields));
		
		menu.add(valider(labels, fields));
		content.add(menu, BorderLayout.CENTER);
		
	}
	
	private JPanel entrMenu(JLabel[] labels, JComponent[] fields) {
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
				
				CategorieEntreprise categorie = null;
				String nomComm = null;
				String siegeSocial = null;
				String adresse = null;
				String tel = null;
				String email = null;
				
				boolean correct = true;
				while(i < size && correct) {
					
					if(fields[i].getComponent(0) instanceof JTextField) {
						String value = ((JTextField) fields[i].getComponent(0)).getText();
						if(value.isBlank()) {
							correct = false;
						}else if(labels[i].getText().equals("Nom commercial :")) {
							nomComm = value;
						}else if(labels[i].getText().equals("Siège social :")) {
							siegeSocial = value;
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
						if(labels[i].getText().equals("Catégorie :")) {
							if(((JRadioButton) fields[i].getComponent(0)).isSelected()){
								categorie = CategorieEntreprise.GE;
							}else if(((JRadioButton) fields[i].getComponent(1)).isSelected()) {
								categorie = CategorieEntreprise.ETI;
							}else if(((JRadioButton) fields[i].getComponent(2)).isSelected()) {
								categorie = CategorieEntreprise.PME;
							}else if(((JRadioButton) fields[i].getComponent(3)).isSelected()) {
								categorie = CategorieEntreprise.TPE;
							}else {
								correct = false;
								JOptionPane.showMessageDialog(null, "Veuillez renseigner votre catégorie d'entreprise");
							}
						}
					}
					i++;
				}
				
				if(correct) {
					String id = Application.quincaillerie.idNouveauClient(false);
					Application.quincaillerie.ajouterClient(new Entreprise(id, adresse, tel, email, 500, siegeSocial, nomComm, categorie));
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
		btnReturn.addActionListener(ev->{MenuNewClient.demarrer(previousFrm, frmNewClientEntr);});
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
		}else if(s.equals("NomCommercial")) {
			t = new JTextField(20);
		}else if(s.equals("SiègeSocial")) {
			t = new JTextField(30);
		}
		
		field.add(t);
		return field;
	}
	
	private JPanel radioButtonPanel(String s) {
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

}
