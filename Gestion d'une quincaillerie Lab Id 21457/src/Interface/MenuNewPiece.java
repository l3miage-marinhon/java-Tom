package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;

public class MenuNewPiece implements Runnable {

	JFrame frmNewPiece;
	JButton btnValider;
	JButton btnAnnuler;
	JPanel content;
	JPanel pnlInfosPiece;
	JPanel pnlBtnType;
	
	JLabel[] labels = {	new JLabel("Nom : "), new JLabel("Prix : "), new JLabel("Durée fabrication : "), new JLabel("Durée garantie : ")};
	JComponent[] fields = { new JTextField(14), new JTextField(14), new JTextField(14), new JTextField(14)};
	
	ArrayList<JLabel> labelsA = new ArrayList<>();
	ArrayList<JComponent> fieldsA = new ArrayList<>();
	
	public static final String PATH_TO_ICONS = "src/icons/";
	
	protected static void initUI() {
        ToolTipManager.sharedInstance().setInitialDelay(500);
        ToolTipManager.sharedInstance().setDismissDelay(60000);
    }
	
	public MenuNewPiece(JFrame previousFrm) {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewPiece(previousFrm));
		previousFrm.dispose();
	}
	
	public static void demarrer(JFrame frmNewPiece, JFrame previousFrm) {
		frmNewPiece.setVisible(true);
		previousFrm.dispose();
	}
	
	@Override
	public void run() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		System.out.println(Application.clientCourant);
		initUI();
		frmNewPiece = new JFrame();
		frmNewPiece.setSize(new Dimension(600, 500));
		frmNewPiece.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmNewPiece.setLocationRelativeTo(null);
		frmNewPiece.setMinimumSize(new Dimension(500, 300));
		frmNewPiece.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int clickedButton = JOptionPane.showConfirmDialog(frmNewPiece, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					frmNewPiece.dispose();
					System.exit(0);
				}
			}
		});
		content = (JPanel) frmNewPiece.getContentPane();
		content.setLayout(new BorderLayout());
		ArrayList<Integer> kj = new ArrayList<>();
		kj.add(3);
		kj.add(4);
		System.out.println(kj);
		kj.clear();
		System.out.println(kj);
		content.add(typePiece(true), BorderLayout.NORTH);
		
		pnlInfosPiece = new JPanel();
		pnlInfosPiece.add(infosPiece(true));
		content.add(pnlInfosPiece, BorderLayout.CENTER);

		frmNewPiece.setVisible(true);
		frmNewPiece.setTitle("Nouvelle pièce");

		content.add(createPanelValiderAnnuler(labels, fields, true), BorderLayout.SOUTH);		
	}
	
	private void refreshFrame(boolean isTypeBase) {
		frmNewPiece.getContentPane().removeAll();
		content = (JPanel) frmNewPiece.getContentPane();
		content.setLayout(new BorderLayout());
		
		content.add(typePiece(isTypeBase), BorderLayout.NORTH);
		
		pnlInfosPiece = new JPanel();
		pnlInfosPiece.add(infosPiece(isTypeBase));
		content.add(pnlInfosPiece, BorderLayout.CENTER);
		content.add(createPanelValiderAnnuler(labels, fields, isTypeBase), BorderLayout.SOUTH);
	}
	
	private JPanel typePiece(boolean isTypeBase) {
		JPanel typePiece = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("Pièce de base");
		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		b1.addActionListener(ev->{
			System.out.println("base");
			refreshFrame(true);
			frmNewPiece.revalidate();
		});
		JRadioButton b2 = new JRadioButton("Pièce composite");
		b2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		b2.addActionListener(ev->{
			System.out.println("composite");
			refreshFrame(false);
			frmNewPiece.revalidate();
		});
		(isTypeBase ? b1 : b2).setSelected(true);
		
		btnGrp.add(b1);
		btnGrp.add(b2);

		typePiece.add(b1);
		typePiece.add(b2);
		
		return typePiece;
	}
	
	private JPanel infosPiece(Boolean base) {
		JPanel infosPiece = new JPanel();
		infosPiece.setLayout(new BoxLayout(infosPiece, BoxLayout.Y_AXIS));
		
		if(base) {
			infosPiece.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			infosPiece.add(partMenu(labels, fields));
			
		}else {
			infosPiece.add(new JLabel(" kjh"));
		}
		
		return infosPiece;
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
			gbc.anchor = GridBagConstraints.WEST;
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
	
	private JPanel createPanelValiderAnnuler(JLabel[] labels, JComponent[] fields, boolean isTypeBase) {
		JPanel pnlValCan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		
		btnValider = createBtnValider(isTypeBase);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(ev->{System.out.println("annuler");});
		pnlValCan.add(btnAnnuler);
		pnlValCan.add(btnValider);
		
		return pnlValCan;
	}
	
	private JButton createBtnValider(boolean isTypeBase) {
		JButton btnValider = new JButton("Valider");
		if(isTypeBase) {
			btnValider.addActionListener(ev->{
				System.out.println("valider base");
				
				String nom = ((JTextField) fields[0]).getText();
				
				Double prix = null;
				try {
					prix = Double.parseDouble(((JTextField) fields[1]).getText());
				} catch(NumberFormatException e) {
					System.out.println("Erreur format prix");
				}
				
				Integer dFab = null;
				try {
					dFab = Integer.parseInt(((JTextField) fields[2]).getText());
				} catch(NumberFormatException e) {
					System.out.println("Erreur format durée fabrication");
				}
				
				Integer dGar = null;
				try {
					dGar = Integer.parseInt(((JTextField) fields[3]).getText());
				} catch(NumberFormatException e) {
					System.out.println("Erreur format durée garantie");
				}
				
				System.out.println( nom + " " + prix + " " + dFab + " " + dGar);
				
			});
		}else {
			btnValider.addActionListener(ev->{
				System.out.println("valider comp");
			});
		}
		return btnValider;
	}
	
}
