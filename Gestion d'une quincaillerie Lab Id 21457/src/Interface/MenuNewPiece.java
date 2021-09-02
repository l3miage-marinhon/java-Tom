package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;
import pieces.PieceDeBase;

public class MenuNewPiece implements Runnable {

	JFrame frmNewPiece;
	JButton btnValider;
	JButton btnAnnuler;
	JButton btnBaseConnue;
	JButton btnNvlBase;
	JCheckBox ckbMontable;
	JSpinner spnExemplairesPiece;
	JPanel content;
	JPanel pnlInfosPiece;
	JPanel pnlListeComposants = new JPanel();
	JPanel pnlBtnType;
	
	JLabel[] labels = {	new JLabel("Nom : "), new JLabel("Prix : "), new JLabel("Durée fabrication : "), new JLabel("Durée garantie : ")};
	JComponent[] fields = { new JTextField(14), new JTextField(14), new JTextField(14), new JTextField(14)};
	
	ArrayList<JLabel> labelsBase = new ArrayList<>();
	ArrayList<JComponent> fieldsBase = new ArrayList<>();
	ArrayList<JLabel> labelsComp = new ArrayList<>();
	ArrayList<JComponent> fieldsComp = new ArrayList<>();
	ArrayList<JLabel> labelsMontable = new ArrayList<>();
	ArrayList<JComponent> fieldsMontable = new ArrayList<>();
	
	ArrayList<JPanel> listeComposants = new ArrayList<>();
	
	public static final String PATH_TO_ICONS = "src/icons/";
	
	protected static void initUI() {
        ToolTipManager.sharedInstance().setInitialDelay(500);
        ToolTipManager.sharedInstance().setDismissDelay(60000);
    }
	
	public MenuNewPiece() {
	}
	
	public static void demarrer(JFrame previousFrm) {
		SwingUtilities.invokeLater(new MenuNewPiece());
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
		initLabelsFields();
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
		pnlInfosPiece.add(infosPieceType(true));
		content.add(pnlInfosPiece, BorderLayout.CENTER);

		frmNewPiece.setVisible(true);
		frmNewPiece.setTitle("Nouvelle pièce");

		content.add(createPanelValiderAnnuler(labels, fields, true), BorderLayout.SOUTH);		
	}
	
	private void initLabelsFields() {
		SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
		spnExemplairesPiece = new JSpinner(model);
		
		labelsBase.add(new JLabel("Nom : "));
		labelsBase.add(new JLabel("Prix : "));
		labelsBase.add(new JLabel("Durée fabrication : "));
		labelsBase.add(new JLabel("Durée garantie : "));
		labelsBase.add(new JLabel("Exemplaires : "));
		
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(spnExemplairesPiece);
		
		
		labelsComp.add(new JLabel("Nom : "));
		labelsComp.add(new JLabel("Composants : "));
		labelsComp.add(new JLabel());
		labelsComp.add(new JLabel("Temps assemblage : "));
		labelsComp.add(new JLabel("Montable : "));
		
		fieldsComp.add(new JTextField(13));
		JPanel pnlBtnComp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnBaseConnue = new JButton("Known");
		pnlBtnComp.add(btnBaseConnue, gbc);
		gbc.gridx = 1;
		btnNvlBase = new JButton("New");
		btnNvlBase.addActionListener(ev->{
			JPanel recapComposant = new JPanel(new GridBagLayout());
			GridBagConstraints g = new GridBagConstraints();
			g.gridx = 0;
			g.gridy = 0;
			recapComposant.add(new JLabel("pièce"), g);
			g.gridx = 1;
			JButton suppr = new JButton("suppr");
			recapComposant.add(suppr, g);
			listeComposants.add(recapComposant);
			System.out.println(listeComposants.size());
			suppr.addActionListener(ev2->{
				listeComposants.remove(recapComposant);
				pnlListeComposants.removeAll();
				pnlListeComposants.add(createDetailListeComposants());
				pnlListeComposants.revalidate();
				refreshFrame(false);
				System.out.println(listeComposants.size());
			});
			pnlListeComposants.add(createDetailListeComposants());
			pnlListeComposants.revalidate();
			refreshFrame(false);
			
		});
		pnlBtnComp.add(btnNvlBase, gbc);
		fieldsComp.add(pnlBtnComp);
		//pnlListeComposants.add(createDetailListeComposants());
		fieldsComp.add(pnlListeComposants);
		fieldsComp.add(new JTextField(13));
		ckbMontable = new JCheckBox();
		ckbMontable.addActionListener(ev->{
			System.out.println("checkbox clicked");
			
		});
		fieldsComp.add(ckbMontable);
		//commaire
		labelsMontable.add(new JLabel("Prix montage : "));
		labelsMontable.add(new JLabel("Durée montage : "));
		
		fieldsMontable.add(new JTextField(13));
		fieldsMontable.add(new JTextField(13));
		
	}
	
	private JPanel createDetailListeComposants() {
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		for(JPanel l : listeComposants) {
			pnl.add(l);
		}
		return pnl;
	}
	
	private void refreshFrame(boolean isTypeBase) {
		frmNewPiece.getContentPane().removeAll();
		content = (JPanel) frmNewPiece.getContentPane();
		content.setLayout(new BorderLayout());
		
		content.add(typePiece(isTypeBase), BorderLayout.NORTH);
		
		pnlInfosPiece = new JPanel();
		pnlInfosPiece.add(infosPieceType(isTypeBase));
		content.add(pnlInfosPiece, BorderLayout.CENTER);
		content.add(createPanelValiderAnnuler(labels, fields, isTypeBase), BorderLayout.SOUTH);
	}
	
	private JPanel typePiece(boolean isTypeBase) {
		JPanel typePiece = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("Pièce de base");
		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		b1.addActionListener(ev->{
			System.out.println("base");
			refreshFrame(true);
			frmNewPiece.revalidate();
		});
		JRadioButton b2 = new JRadioButton("Pièce composite");
		b2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
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
	
	private JPanel infosPieceType(Boolean isTypeBase) {
		JPanel infosPiece = new JPanel();
		infosPiece.setLayout(new BoxLayout(infosPiece, BoxLayout.Y_AXIS));
		
		if(isTypeBase) {
			infosPiece.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			infosPiece.add(infosPiece(labelsBase, fieldsBase));
			
		}else {
			infosPiece.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
			infosPiece.add(infosPiece(labelsComp, fieldsComp));
		}
		
		return infosPiece;
	}
	
	private JPanel infosPiece(ArrayList<JLabel> labels, ArrayList<JComponent> fields) {
		if (labels.size() != fields.size()) {
			String s = labels.size() + " labels supplied for " + fields.size() + " fields!";
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
		
		btnAnnuler = createBtnAnnuler();
		pnlValCan.add(btnAnnuler);
		pnlValCan.add(btnValider);
		
		return pnlValCan;
	}
	
	private JButton createBtnValider(boolean isTypeBase) {
		JButton btnValider = new JButton("Valider");
		if(isTypeBase) {
			btnValider.addActionListener(ev->{
				System.out.println("valider base");
				PieceDeBase pb = FormValidation.validerNouvellePieceBase(fieldsBase);
				if(pb != null) {
					Application.quincaillerie.getCatalogue().ajoutePiece(pb);
					Application.quincaillerie.getStocks().nouvellePieceStocks(pb, (Integer) spnExemplairesPiece.getValue());
					MenuQuincailleriePieces.demarrer(frmNewPiece);
				}
			});
		}else {
			btnValider.addActionListener(ev->{
				System.out.println("valider comp");
			});
		}
		return btnValider;
	}
	
	private JButton createBtnAnnuler() {
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(ev->{
			int clickedButton = JOptionPane.showConfirmDialog(frmNewPiece, "Annuler l'ajout d'une nouvelle pièce ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if(clickedButton == JOptionPane.YES_OPTION) {
				MenuQuincailleriePieces.demarrer(frmNewPiece);
			}
		});
		
		return btnAnnuler;
	}
	
}
