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
import javax.swing.JDialog;
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
import pieces.Piece;
import pieces.PieceDeBase;

public class MenuNewPiece implements Runnable {

	JFrame frmNewPiece;
	JButton btnValider;
	JButton btnAnnuler;
	JButton btnBaseConnue;
	JButton btnNvlBase;
	JCheckBox ckbMontable;
	JSpinner spnExemplairesPiece;
	JDialog dlgListePieceBase;
	JDialog dlgNewPieceBaseComp;
	JPanel contentFrmNewPiece;
	JPanel typeNouvellePiece;
	JPanel infosPiece;
	JPanel pnlPiece;
	JPanel pnlInfosPiece;
	JPanel pnlListeComposants = new JPanel();
	JPanel pnlBtnType;
	JPanel pnlBtnComp;
	JPanel pnlInfoPieceBaseComposant;
	JPanel pnlDetailListeComposants;
	JPanel pnlBtnsValAnn;
	JPanel contentDlgNewComposantBase;
	
	ArrayList<JLabel> labelsBase = new ArrayList<>();
	ArrayList<JComponent> fieldsBase = new ArrayList<>();
	ArrayList<JLabel> labelsComp = new ArrayList<>();
	ArrayList<JComponent> fieldsComp = new ArrayList<>();
	ArrayList<JLabel> labelsMontable = new ArrayList<>();
	ArrayList<JComponent> fieldsMontable = new ArrayList<>();
	
	ArrayList<JPanel> listeComposants = new ArrayList<>();
	
	ArrayList<PieceDeBase> listePieceBaseCat = new ArrayList<>();
	
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
		contentFrmNewPiece = (JPanel) frmNewPiece.getContentPane();
		contentFrmNewPiece.setLayout(new BorderLayout());
		ArrayList<Integer> kj = new ArrayList<>();
		kj.add(3);
		kj.add(4);
		System.out.println(kj);
		kj.clear();
		System.out.println(kj);
		contentFrmNewPiece.add(typeNouvellePiece(true), BorderLayout.NORTH);
		
		pnlPiece = new JPanel();
		pnlPiece.add(infosPieceType(true));
		contentFrmNewPiece.add(pnlPiece, BorderLayout.CENTER);

		frmNewPiece.setVisible(true);
		frmNewPiece.setTitle("Nouvelle pièce");

		contentFrmNewPiece.add(createPanelValiderAnnuler(labelsBase, fieldsBase, true, 1), BorderLayout.SOUTH);		
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
		pnlBtnComp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnBaseConnue = new JButton("Known");
		btnBaseConnue.addActionListener(ev->{
			for(Piece p : Application.quincaillerie.getCatalogue().catalogue) {
				if(p instanceof PieceDeBase) listePieceBaseCat.add((PieceDeBase) p);
			}
			System.out.println("liste piece de base");
			/*
			//JDialog detailPanier = detailPanier();
			detailPanier.setSize(600, 400);
			detailPanier.setLocationRelativeTo(null);
			detailPanier.setVisible(true);
			*/
		});
		pnlBtnComp.add(btnBaseConnue, gbc);
		gbc.gridx = 1;
		btnNvlBase = new JButton("New");
		btnNvlBase.addActionListener(ev->{
			dlgNewPieceBaseComp = saisieNewComposantPieceBase();
			dlgNewPieceBaseComp.setSize(new Dimension(300, 300));
			dlgNewPieceBaseComp.setLocationRelativeTo(null);
			dlgNewPieceBaseComp.setVisible(true);
			
			refreshFrame(false);
			
		});
		pnlBtnComp.add(btnNvlBase, gbc);
		fieldsComp.add(pnlBtnComp);
		pnlListeComposants.add(createDetailListeComposants());
		fieldsComp.add(pnlListeComposants);
		fieldsComp.add(new JTextField(13));
		ckbMontable = new JCheckBox();
		ckbMontable.addActionListener(ev->{
			System.out.println("checkbox clicked");
			
		});
		fieldsComp.add(ckbMontable);
		labelsMontable.add(new JLabel("Prix montage : "));
		labelsMontable.add(new JLabel("Durée montage : "));
		
		fieldsMontable.add(new JTextField(13));
		fieldsMontable.add(new JTextField(13));
	}
	
	private JDialog saisieNewComposantPieceBase() {
		dlgNewPieceBaseComp = new JDialog(frmNewPiece, "Nouvelle pièce de base");

		contentDlgNewComposantBase = (JPanel) dlgNewPieceBaseComp.getContentPane();
		contentDlgNewComposantBase.setLayout(new BorderLayout());
		
		pnlInfoPieceBaseComposant = new JPanel();
		pnlInfoPieceBaseComposant.setLayout(new BoxLayout(pnlInfoPieceBaseComposant, BoxLayout.Y_AXIS));
		pnlInfoPieceBaseComposant.add(infosPiece(labelsBase, fieldsBase));
		
		contentDlgNewComposantBase.add(pnlInfoPieceBaseComposant, BorderLayout.CENTER);
		contentDlgNewComposantBase.add(createPanelValiderAnnuler(labelsBase, fieldsBase, true, 2), BorderLayout.SOUTH);
		
		return dlgNewPieceBaseComp;
	}	
	
	
	private JPanel createDetailListeComposants() {
		pnlDetailListeComposants = new JPanel();
		pnlDetailListeComposants.setLayout(new BoxLayout(pnlDetailListeComposants, BoxLayout.Y_AXIS));
		for(JPanel l : listeComposants) {
			pnlDetailListeComposants.add(l);
		}
		return pnlDetailListeComposants;
	}
	
	private void refreshFrame(boolean isTypeBase) {
		frmNewPiece.getContentPane().removeAll();
		contentFrmNewPiece = (JPanel) frmNewPiece.getContentPane();
		contentFrmNewPiece.setLayout(new BorderLayout());
		
		contentFrmNewPiece.add(typeNouvellePiece(isTypeBase), BorderLayout.NORTH);
		
		pnlListeComposants.removeAll();
		pnlListeComposants.add(createDetailListeComposants());
		pnlListeComposants.revalidate();
		
		pnlPiece = new JPanel();
		pnlPiece.add(infosPieceType(isTypeBase));
		contentFrmNewPiece.add(pnlPiece, BorderLayout.CENTER);
		if(isTypeBase) {
			contentFrmNewPiece.add(createPanelValiderAnnuler(labelsBase, fieldsBase, isTypeBase, 1), BorderLayout.SOUTH);
		}else {
			contentFrmNewPiece.add(createPanelValiderAnnuler(labelsComp, fieldsComp, isTypeBase, 1), BorderLayout.SOUTH);
		}
		
		frmNewPiece.revalidate();
	}
	
	private JPanel typeNouvellePiece(boolean isTypeBase) {
		typeNouvellePiece = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("Pièce de base");
		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		b1.addActionListener(ev->{
			System.out.println("base");
			refreshFrame(true);
		});
		JRadioButton b2 = new JRadioButton("Pièce composite");
		b2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		b2.addActionListener(ev->{
			System.out.println("composite");
			refreshFrame(false);
		});
		(isTypeBase ? b1 : b2).setSelected(true);
		
		btnGrp.add(b1);
		btnGrp.add(b2);

		typeNouvellePiece.add(b1);
		typeNouvellePiece.add(b2);
		
		return typeNouvellePiece;
	}
	
	private JPanel infosPieceType(Boolean isTypeBase) {
		infosPiece = new JPanel();
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
		pnlInfosPiece = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int n = 0;
		for(JLabel label : labels) {
			gbc.gridx = 0;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.WEST;
			pnlInfosPiece.add(label, gbc);
			n++;
		}
		
		n = 0;
		for(JComponent field : fields) {
			gbc.gridx = 1;
			gbc.gridy = n;
			gbc.anchor = GridBagConstraints.WEST;
			pnlInfosPiece.add(field, gbc);
			n++;
		}
		 
		return pnlInfosPiece;
	}	
	
	private JPanel createPanelValiderAnnuler(ArrayList<JLabel> labels, ArrayList<JComponent> fields, boolean isTypeBase, int catOrComp) {
		pnlBtnsValAnn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnValider = createBtnValider(isTypeBase, catOrComp);
		
		btnAnnuler = createBtnAnnuler();
		pnlBtnsValAnn.add(btnAnnuler);
		pnlBtnsValAnn.add(btnValider);
		
		return pnlBtnsValAnn;
	}
	
	private JButton createBtnValider(boolean isTypeBase, int catOrComp) {
		JButton btnValider = new JButton("Valider");
		if(isTypeBase) {
			if(catOrComp == 1) {
				btnValider.addActionListener(ev->{
					System.out.println("valider base catalogue");
					PieceDeBase pb = FormValidation.validerNouvellePieceBase(fieldsBase);
					if(pb != null) {
						Application.quincaillerie.getCatalogue().ajoutePiece(pb);
						Application.quincaillerie.getStocks().nouvellePieceStocks(pb, (Integer) spnExemplairesPiece.getValue());
						MenuQuincailleriePieces.demarrer(frmNewPiece);
					}
				});
			}else {
				btnValider.addActionListener(ev->{
					System.out.println("valider base composant");
					PieceDeBase pb = FormValidation.validerNouvellePieceBase(fieldsBase);
					if(pb != null) {
						
						JPanel recapComposant = new JPanel(new GridBagLayout());
						GridBagConstraints g = new GridBagConstraints();
						g.gridx = 0;
						g.gridy = 0;
						recapComposant.add(new JLabel(pb.getNom() + " : " + pb.getRef() + " "), g);
						g.gridx = 1;
						JButton suppr = new JButton("suppr");
						recapComposant.add(suppr, g);
						listeComposants.add(recapComposant);
						suppr.addActionListener(ev2->{
							listeComposants.remove(recapComposant);
							refreshFrame(false);
							System.out.println(listeComposants.size());
						});
						
						refreshFrame(false);
					}
				});
			}
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
