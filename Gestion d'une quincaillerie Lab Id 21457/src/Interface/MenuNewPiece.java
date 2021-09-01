package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.Application;

public class MenuNewPiece implements Runnable {

	JFrame frmNewPiece;
	JButton btnReturn;
	JPanel pnlNewPiece;

	
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
		frmNewPiece.setSize(new Dimension(1200, 800));
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
		JPanel content = (JPanel) frmNewPiece.getContentPane();
		content.setLayout(new BorderLayout());
		
		//content.add(new JLabel("Chargement"), BorderLayout.CENTER);
		frmNewPiece.setVisible(true);
		frmNewPiece.setTitle("Nouvelle pièce");

		content.add(createBtnReturn(), BorderLayout.NORTH);
		
	}
	
	private JPanel nouvellePiece() {
		pnlNewPiece = new JPanel(new BorderLayout());

		JPanel pnlTypePiece = typePiece();
		pnlInfosPiece = infosPiece(true);
		
		content.add(pnlTypePiece, BorderLayout.NORTH);
		content.add(pnlInfosPiece, BorderLayout.CENTER);
		
		return newPiece;
	}
	
	private JPanel typePiece() {
		JPanel typePiece = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGrp = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("Pièce de base");
		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		b1.setSelected(true);
		b1.addActionListener(ev->{
			System.out.println("aze");
			pnlInfosPiece.removeAll();
			pnlInfosPiece.add(infosPiece(true));
			pnlInfosPiece.revalidate();
		});
		JRadioButton b2 = new JRadioButton("Pièce composite");
		b2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		b2.addActionListener(ev->{
			System.out.println("qsd");
			pnlInfosPiece.removeAll();
			pnlInfosPiece.add(infosPiece(false));
			pnlInfosPiece.revalidate();
		});
		btnGrp.add(b1);
		btnGrp.add(b2);

		typePiece.add(b1);
		typePiece.add(b2);
		
		return typePiece;
	}
	
	private JPanel infosPiece(Boolean base) {
		JPanel infosPiece = new JPanel();
		
		if(base) {
			infosPiece.add(new JLabel("base"));
		}else {
			infosPiece.add(new JLabel("composite"));
		}
		
		return infosPiece;
	}
	
	private JPanel createBtnReturn() {
		JPanel pnlBtnReturn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnReturn = new JButton(new ImageIcon(new ImageIcon(PATH_TO_ICONS + "return_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		btnReturn.addActionListener(ev->{MenuQuincailleriePieces.demarrer(frmNewPiece);});
		pnlBtnReturn.add(btnReturn);
		return pnlBtnReturn;
	}
	
}
