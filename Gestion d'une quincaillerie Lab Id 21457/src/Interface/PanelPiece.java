package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Vector;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.Application;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;

@SuppressWarnings("serial")
public class PanelPiece extends JPanel {
	
	Piece piece;
	JPanel panelImage;
	JPanel pnlAddInfos;
	JPanel pnlComboBox;
	JComboBox<Integer> comboBoxValues;
	
	public PanelPiece(Piece piece, JPanel pnlBtnCart) {
		
		this.piece = piece;
		setComboBox();
		setImage();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setPreferredSize(new Dimension(180, 200));
		this.setMinimumSize(new Dimension(180, 200));
		this.setMaximumSize(new Dimension(180, 200));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(panelImage, gbc);
		
		gbc.gridy = 1;
		this.add(new JLabel(piece.getNom()), gbc);
		
		gbc.gridy = 2;
		this.add(new JLabel(Double.toString(piece.prix()) + " €"), gbc);
		
		gbc.gridy = 3;
		pnlAddInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlComboBox = new JPanel();
		pnlComboBox.add(comboBoxValues);
		
		pnlAddInfos.add(pnlComboBox);
		
		JButton ajoutPanier = new JButton(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "addcart_icon.png").getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH)));
		ajoutPanier.addActionListener(ev->{actionListenerAjoutPanier(pnlBtnCart);});
		
		pnlAddInfos.add(ajoutPanier);
		
		JPanel pnlInfos = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlInfos.setToolTipText("<html>"+ piece.toStringHTML() +"</html>");
		pnlInfos.add(new JLabel(new ImageIcon(new ImageIcon(Application.PATH_TO_ICONS + "detail_icon.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH))));
		
		pnlAddInfos.add(pnlInfos);
		
		this.add(pnlAddInfos, gbc);
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	private void setComboBox() {
		int maxValue = Application.quincaillerie.getStocks().stocksPiece(piece) - Application.panier.getNbPiece(piece);
		Vector<Integer> nbs1 = new Vector<>(){{for(int i : IntStream.range(1, maxValue+1).toArray()) add(i);}};
		comboBoxValues = new JComboBox<>(nbs1);
		if(nbs1.size() > 0) {
			comboBoxValues.setSelectedIndex(0);
			comboBoxValues.setEnabled(true);
		}else {
			comboBoxValues.setEnabled(false);
		}
	}
	
	public void refreshComboBoxAndPanier(JPanel pnlBtnCart) {
		setComboBox();
		pnlComboBox.removeAll();
		pnlComboBox.add(comboBoxValues);
		pnlComboBox.revalidate();
		pnlComboBox.repaint();
		
		String nbArt = Integer.toString(Application.panier.nbArticles());
		String s = "<html><font color=\"red\">"+nbArt+"</font></html>";
		pnlBtnCart.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP));
		pnlBtnCart.revalidate();
	}
	
	private void actionListenerAjoutPanier(JPanel pnlBtnCart) {
		int nbEx = comboBoxValues.getItemAt(comboBoxValues.getSelectedIndex());
		
		PieceCompositeMontee pcm = null;
		if(piece instanceof PieceCompositeEnKit) {
			pcm = Application.quincaillerie.getCatalogue().pieceMonteeFromKit((PieceCompositeEnKit) piece);
			if(pcm != null) {
				int clickedButton = JOptionPane.showConfirmDialog(this, 
									"Voulez-vous la version montée ?\nPrix montage : "+pcm.getPrixMontage()+" €\nDurée montage : "+pcm.getDureeMontage()+" heure(s)", "", 
										JOptionPane.YES_NO_CANCEL_OPTION);
				if(clickedButton == JOptionPane.YES_OPTION) {
					Application.panier.ajoutPiecePanier(pcm, nbEx);
				}else if(clickedButton == JOptionPane.NO_OPTION){
					Application.panier.ajoutPiecePanier(piece, nbEx);
				}
			}else {
				Application.panier.ajoutPiecePanier(piece, nbEx);
			}
		}else {
			Application.panier.ajoutPiecePanier(piece, nbEx);
		}
		refreshComboBoxAndPanier(pnlBtnCart);
	}
	
	private void setImage() {
		panelImage = new JPanel();
		panelImage.add(new JLabel(new ImageIcon( Application.mapImagesPieces.get( Application.mapImagesPieces.containsKey(piece.getNom()) ? piece.getNom() : "no_image"))));
	}
}
