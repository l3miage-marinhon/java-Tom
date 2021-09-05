package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
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
	
	ButtonGroup btnGrp = new ButtonGroup();
	
	ArrayList<JLabel> labelsBase = new ArrayList<>();
	ArrayList<JComponent> fieldsBase = new ArrayList<>();
	ArrayList<JLabel> labelsComp = new ArrayList<>();
	ArrayList<JComponent> fieldsComp = new ArrayList<>();
	ArrayList<JLabel> labelsMontable = new ArrayList<>();
	ArrayList<JComponent> fieldsMontable = new ArrayList<>();
	
	Map<PieceDeBase, Integer> listeComposants = new HashMap<>();
	
	Map<PieceDeBase, JPanel> mapPBCatButtons;
	
	PieceDeBase pieceBaseCatalogueToComposant;
		
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
		initUI();
		initLabelsFields();
		initMapPbButtons();
		
		frmNewPiece = new JFrame();
		frmNewPiece.setSize(new Dimension(400, 400));
		frmNewPiece.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmNewPiece.setLocationRelativeTo(null);
		frmNewPiece.setMinimumSize(new Dimension(350, 350));
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
		labelsBase.add(new JLabel("Prix (€): "));
		labelsBase.add(new JLabel("Durée fabrication (heures): "));
		labelsBase.add(new JLabel("Durée garantie (mois) : "));
		labelsBase.add(new JLabel("Exemplaires : "));
		
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(new JTextField(13));
		fieldsBase.add(spnExemplairesPiece);
		
		
		labelsComp.add(new JLabel("Nom : "));
		labelsComp.add(new JLabel("Composants : "));
		labelsComp.add(new JLabel());
		labelsComp.add(new JLabel("Temps assemblage (heures) : "));
		labelsComp.add(new JLabel("Exemplaires : "));
		labelsComp.add(new JLabel("Montable : "));
		
		fieldsComp.add(new JTextField(13));
		pnlBtnComp = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		btnBaseConnue = new JButton("Known");
		btnBaseConnue.addActionListener(ev->{
			listePieceBaseConnuesRecherche();
		});
		pnlBtnComp.add(btnBaseConnue, gbc);
		gbc.gridx = 1;
		btnNvlBase = new JButton("New");
		btnNvlBase.addActionListener(ev->{
			saisieNewComposantPieceBase();
			//refreshFrame(false);
		});
		pnlBtnComp.add(btnNvlBase, gbc);
		fieldsComp.add(pnlBtnComp);
		pnlListeComposants.add(createDetailListeComposants());
		fieldsComp.add(pnlListeComposants);
		fieldsComp.add(new JTextField(13));
		fieldsComp.add(spnExemplairesPiece);
		ckbMontable = new JCheckBox();
		ckbMontable.addActionListener(ev->{
			if(ckbMontable.isSelected()) {
				fieldsComp.addAll(fieldsMontable);
				labelsComp.addAll(labelsMontable);
				refreshFrame(false, true);
			}else {
				labelsComp.removeAll(labelsMontable);
				fieldsComp.removeAll(fieldsMontable);
				refreshFrame(false, true);
			}
			
			
		});
		
		fieldsComp.add(ckbMontable);
		labelsMontable.add(new JLabel("Prix montage (€) : "));
		labelsMontable.add(new JLabel("Durée montage (heures) : "));
		
		fieldsMontable.add(new JTextField(13));
		fieldsMontable.add(new JTextField(13));
	}
	
	private void initMapPbButtons(){
		mapPBCatButtons = new HashMap<>();
		
		for(Piece p : Application.quincaillerie.getCatalogue().catalogue) {
			if(p instanceof PieceDeBase) {
				JPanel pnl = new JPanel(new GridBagLayout());
				GridBagConstraints g = new GridBagConstraints();
				pnl.setToolTipText("<html>"+p.toStringHTML()+"</html>");
				pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				pnl.setSize(new Dimension(200, 30));
				pnl.setPreferredSize(new Dimension(250, 30));
				g.gridx = 0;
				g.gridy = 0;
				JRadioButton rb = new JRadioButton();
				rb.addActionListener(ev->{
					pieceBaseCatalogueToComposant = (PieceDeBase) p;
				});
				btnGrp.add(rb);
				pnl.add(rb, g);

				g.gridx = 1;
				JLabel lb = new JLabel("  " + p.getRef() + " ; " + p.getNom());
				pnl.add(lb, g);
				
				mapPBCatButtons.put(((PieceDeBase) p), pnl);
			}
		}
	}
	
	private void listePieceBaseConnuesRecherche() {
		dlgListePieceBase = new JDialog(frmNewPiece, "Pièces de base du catalogue");
		JPanel content = (JPanel) dlgListePieceBase.getContentPane();
		content.setLayout(new BorderLayout());
		JPanel listeP = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		int n = 0;
		g.gridx = 0;
		g.insets = new Insets(5, 5, 5, 5);
		for(PieceDeBase p : mapPBCatButtons.keySet()) {
			g.gridy = n;
			listeP.add(mapPBCatButtons.get(p), g);
			n++;
		}
		
		JTextField rech = new JTextField("nom pièce");
		rech.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = rech.getText();
					int n = 0;
					g.gridx = 0;
					g.insets = new Insets(5, 5, 5, 5);
					listeP.removeAll();
					for(PieceDeBase p : mapPBCatButtons.keySet()) {
						if(p.getNom().contains(s)) {
							g.gridy = n;
							listeP.add(mapPBCatButtons.get(p), g);
							n++;
						}
					}
					
					listeP.revalidate();
					listeP.repaint();
				
					content.revalidate();
					content.repaint();
				}
			}
		});
		content.add(rech, BorderLayout.NORTH);
		JScrollPane jsp = new JScrollPane(listeP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		content.add(jsp, BorderLayout.CENTER);
		
		JPanel spinnerVal = new JPanel(new FlowLayout(FlowLayout.CENTER));
		spinnerVal.add(spnExemplairesPiece);
		JButton bv = new JButton("Valider");
		bv.addActionListener(ev->{
			if(pieceBaseCatalogueToComposant != null) {
				listeComposants.put(pieceBaseCatalogueToComposant, (Integer) spnExemplairesPiece.getValue());
				dlgListePieceBase.dispose();
				refreshFrame(false, false);
			}else {
				JOptionPane.showMessageDialog(dlgListePieceBase, "Vous devez sélectionner une pièce");
			}
			
		});

		spinnerVal.add(bv);
		content.add(spinnerVal, BorderLayout.SOUTH);
		
		dlgListePieceBase.setSize(new Dimension(350, 400));
		dlgListePieceBase.setLocationRelativeTo(null);
		dlgListePieceBase.setResizable(false);
		dlgListePieceBase.setVisible(true);
	}
	
	private void saisieNewComposantPieceBase() {
		dlgNewPieceBaseComp = new JDialog(frmNewPiece, "Nouvelle pièce de base");

		JPanel content = (JPanel) dlgNewPieceBaseComp.getContentPane();
		content.setLayout(new BorderLayout());
		
		pnlInfoPieceBaseComposant = new JPanel();
		pnlInfoPieceBaseComposant.setLayout(new BoxLayout(pnlInfoPieceBaseComposant, BoxLayout.Y_AXIS));
		pnlInfoPieceBaseComposant.add(infosPiece(labelsBase, fieldsBase));
		
		content.add(pnlInfoPieceBaseComposant, BorderLayout.CENTER);
		content.add(createPanelValiderAnnuler(labelsBase, fieldsBase, true, 2), BorderLayout.SOUTH);
		
		dlgNewPieceBaseComp.setSize(new Dimension(400, 300));
		dlgNewPieceBaseComp.setResizable(false);
		dlgNewPieceBaseComp.setLocationRelativeTo(null);
		dlgNewPieceBaseComp.setVisible(true);
		//return dlgNewPieceBaseComp;
	}	
	
	
	private JPanel createDetailListeComposants() {
		pnlDetailListeComposants = new JPanel();
		pnlDetailListeComposants.setLayout(new BoxLayout(pnlDetailListeComposants, BoxLayout.Y_AXIS));
		for(PieceDeBase p : listeComposants.keySet()) {
			
			JPanel recapComposant = new JPanel(new GridBagLayout());
			GridBagConstraints g = new GridBagConstraints();
			g.gridx = 0;
			g.gridy = 0;
			recapComposant.add(new JLabel(p.getRef() + " : x" + listeComposants.get(p) + " "), g);
			recapComposant.setToolTipText("<html>"+p.toStringHTML()+"</html>");
			g.gridx = 1;
			JButton suppr = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "delete_icon.png").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH)));
			recapComposant.add(suppr, g);
			suppr.addActionListener(ev2->{
				listeComposants.remove(p);
				refreshFrame(false, false);
			});
			pnlDetailListeComposants.add(recapComposant);
		}
		return pnlDetailListeComposants;
	}
	
	private void refreshFrame(boolean isTypeBase, boolean keepValueSpinner) {
		frmNewPiece.getContentPane().removeAll();
		contentFrmNewPiece = (JPanel) frmNewPiece.getContentPane();
		contentFrmNewPiece.setLayout(new BorderLayout());
		if(!keepValueSpinner) spnExemplairesPiece.setValue( ((SpinnerNumberModel) spnExemplairesPiece.getModel()).getMinimum());
		contentFrmNewPiece.add(typeNouvellePiece(isTypeBase), BorderLayout.NORTH);
		
		
		pnlListeComposants.removeAll();
		pnlListeComposants.add(createDetailListeComposants());
		pnlListeComposants.revalidate();
		
		if(pieceBaseCatalogueToComposant != null) {
			btnGrp.clearSelection();
			pieceBaseCatalogueToComposant = null;
		}

		pnlPiece = new JPanel();
		pnlPiece.add(infosPieceType(isTypeBase));
		if(!isTypeBase) {
			JScrollPane jsp = new JScrollPane(pnlPiece, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.getVerticalScrollBar().setUnitIncrement(10);
			contentFrmNewPiece.add(jsp, BorderLayout.CENTER);
		}else {
			contentFrmNewPiece.add(pnlPiece, BorderLayout.CENTER);
		}
		
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
			refreshFrame(true, false);
		});
		JRadioButton b2 = new JRadioButton("Pièce composite");
		b2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		b2.addActionListener(ev->{
			refreshFrame(false, false);
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
	
	private JPanel createPanelValiderAnnuler(ArrayList<JLabel> labels, ArrayList<JComponent> fields, boolean isTypeBase, int toCatOrComp) {
		pnlBtnsValAnn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnValider = createBtnValider(isTypeBase, toCatOrComp);
		
		btnAnnuler = createBtnAnnuler(toCatOrComp);
		pnlBtnsValAnn.add(btnAnnuler);
		pnlBtnsValAnn.add(btnValider);
		
		return pnlBtnsValAnn;
	}
	
	private JButton createBtnValider(boolean isTypeBase, int toCatOrComp) {
		JButton btnValider = new JButton("Valider");
		
		if(isTypeBase) {
			if(toCatOrComp == 1) {
				btnValider.addActionListener(ev->{
					PieceDeBase pb = FormValidation.validerNouvellePieceBase(fieldsBase);
					if(pb != null) {
						int n = ((Integer) ((JSpinner) fieldsBase.get(4)).getValue());
						Application.quincaillerie.getCatalogue().ajoutePiece(pb);
						Application.quincaillerie.getStocks().nouvellePieceStocks(pb, n);
						MenuQuincailleriePieces.demarrer(frmNewPiece);
					}
				});
			}else if(toCatOrComp == 2){
				btnValider.addActionListener(ev->{
					PieceDeBase pb = FormValidation.validerNouvellePieceBase(fieldsBase);
					if(pb != null) {
						int n = ((Integer) ((JSpinner) fieldsBase.get(4)).getValue());
						listeComposants.put(pb, n);
						dlgNewPieceBaseComp.dispose();
						refreshFrame(false, false);
					}
				});
			}
		}else {
			btnValider.addActionListener(ev->{
				if(listeComposants.keySet().size() < 2) {
					JOptionPane.showMessageDialog(frmNewPiece, "La pièce composite doit comporter 2 composants ou plus");
				}else {
					ArrayList<Piece> piecesOk = FormValidation.validerNouvellePieceCompositeKit(fieldsComp, listeComposants);
					if(piecesOk.size() > 0) {
						PieceCompositeEnKit pck = (PieceCompositeEnKit) piecesOk.get(0);
						Integer n = ((Integer) ((JSpinner) fieldsComp.get(4)).getValue());
						Application.quincaillerie.getCatalogue().ajoutePiece(pck);
						Application.quincaillerie.getStocks().nouvellePieceStocks(pck, n);
						
						if(piecesOk.size() == 2) {
							PieceCompositeMontee pcm = (PieceCompositeMontee) piecesOk.get(1);
							n = ((Integer) ((JSpinner) fieldsComp.get(4)).getValue());
							Application.quincaillerie.getCatalogue().ajoutePiece(pcm);
							Application.quincaillerie.getStocks().nouvellePieceStocks(pcm, n);
						}
						MenuQuincailleriePieces.demarrer(frmNewPiece);
					}
				}
			});
		}
		return btnValider;
	}
	
	private JButton createBtnAnnuler(int toCatOrComp) {
		JButton btnAnnuler = new JButton("Annuler");
		if(toCatOrComp == 1) {
			btnAnnuler.addActionListener(ev->{
				int clickedButton = JOptionPane.showConfirmDialog(frmNewPiece, "Annuler l'ajout d'une nouvelle pièce ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					MenuQuincailleriePieces.demarrer(frmNewPiece);
				}
			});
		}else {
			btnAnnuler.addActionListener(ev->{
				int clickedButton = JOptionPane.showConfirmDialog(frmNewPiece, "Annuler l'ajout du nouveau composant ?", "Quitter", JOptionPane.YES_NO_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					dlgNewPieceBaseComp.dispose();
				}
			});
		}
		
		return btnAnnuler;
	}
	
}
