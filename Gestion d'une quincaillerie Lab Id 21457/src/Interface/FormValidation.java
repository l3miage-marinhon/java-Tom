package Interface;


import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import clients.CategorieEntreprise;
import clients.Civilite;
import clients.Entreprise;
import clients.Particulier;
import main.Application;
import pieces.Piece;
import pieces.PieceCompositeEnKit;
import pieces.PieceCompositeMontee;
import pieces.PieceDeBase;

public final class FormValidation{
	
	protected static Object validerInfosParticulier(JLabel[] labels, JComponent[] fields, Boolean newClient) {
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
					JOptionPane.showMessageDialog(null, "Champ(s) vide(s)");
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
					if(Application.quincaillerie.mailConnu(value)) {
						System.out.println(value);
						if(!newClient && Application.clientCourant.getEmail().equals(value)) {
							email = value;
						}else {
							correct = false;
							JOptionPane.showMessageDialog(null, "Email indisponible");
						}
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
		
		Object e = null;
		if(newClient) {
			if(correct) {
				String id = Application.quincaillerie.idNouveauClient(false);
				e = new Particulier(id, adresse, tel, email, 100, civilite, nom, prenom, fid);
			}
		}else {
			e = false;
			if(correct) {
				((Particulier) Application.clientCourant).modifierInfos(adresse, tel, email, civilite, nom, prenom, fid);
				e = true;
			}
		}
		return e;
	}
	
	protected static Object validerInfosEntreprise(JLabel[] labels, JComponent[] fields, Boolean newClient) {
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
					if(Application.quincaillerie.mailConnu(value)) {
						System.out.println(value);
						if(!newClient && Application.clientCourant.getEmail().equals(value)) {
							email = value;
						}else {
							correct = false;
							JOptionPane.showMessageDialog(null, "Email indisponible");
						}
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
		
		Object e = null;
		if(newClient) {
			if(correct) {
				String id = Application.quincaillerie.idNouveauClient(false);
				e = new Entreprise(id, adresse, tel, email, 500, siegeSocial, nomComm, categorie);
			}
		}else {
			e = false;
			if(correct) {
				((Entreprise) Application.clientCourant).modifierInfos(adresse, tel, email, siegeSocial, nomComm, categorie);
				e = true;
			}
		}
		
		return e;
	}	
	
	protected static PieceDeBase validerNouvellePieceBase(ArrayList<JComponent> fields) {
		PieceDeBase pb = null;
		
		String nom = ((JTextField) fields.get(0)).getText();
		
		Double prix = null;
		try {
			prix = Double.parseDouble(((JTextField) fields.get(1)).getText());
		} catch(NumberFormatException e) {
			System.out.println("Erreur format prix");
		}
		
		Integer dFab = null;
		try {
			dFab = Integer.parseInt(((JTextField) fields.get(2)).getText());
		} catch(NumberFormatException e) {
			System.out.println("Erreur format durée fabrication");
		}
		
		Integer dGar = null;
		try {
			dGar = Integer.parseInt(((JTextField) fields
					
					.get(3)).getText());
		} catch(NumberFormatException e) {
			System.out.println("Erreur format durée garantie");
		}
		
		if(!nom.isBlank() && prix != null && dFab != null && dGar != null) {
			System.out.println( nom + " " + prix + " " + dFab + " " + dGar);
			String ref = Application.quincaillerie.refNewPiece(nom, 0);
			pb = new PieceDeBase(nom, ref, prix, dGar, dFab);
		}
		
		return pb;
	}
	
	protected static ArrayList<Piece> validerNouvellePieceCompositeKit(ArrayList<JComponent> fields, Map<PieceDeBase, Integer> composants) {
		ArrayList<Piece> piecesOk = new ArrayList<>();
		
		PieceCompositeEnKit pck = null;
		PieceCompositeMontee pcm = null;
		
		String nom = ((JTextField) fields.get(0)).getText();		
		Integer tpsAssemblage = null;
		try {
			tpsAssemblage = Integer.parseInt(((JTextField) fields.get(3)).getText());
		} catch(NumberFormatException e) {
			System.out.println("Erreur format temps d'assemblage");
		}
		
		Boolean versionMontable = ((JCheckBox) fields.get(5)).isSelected();
		
		if(!nom.isBlank() && composants != null && tpsAssemblage != null) {
			System.out.println( nom + " " + composants.size() + " " + tpsAssemblage + " ");
			String ref = Application.quincaillerie.refNewPiece(nom, 1);
			pck = new PieceCompositeEnKit(nom, ref, composants, tpsAssemblage);
			piecesOk.add(pck);
			
			if(versionMontable) {
				Double prixMontage = null;
				try {
					prixMontage = Double.parseDouble(((JTextField) fields.get(6)).getText());
				} catch(NumberFormatException e) {
					System.out.println("Erreur format prix montage");
				}
				
				Integer tempsMontage = null;
				try {
					tempsMontage = Integer.parseInt(((JTextField) fields.get(7)).getText());
				} catch(NumberFormatException e) {
					System.out.println("Erreur format temps montage");
				}
				
				if(prixMontage != null && tempsMontage != null) {
					ref = Application.quincaillerie.refNewPiece(nom, 2);
					pcm = new PieceCompositeMontee(nom, ref, composants, tempsMontage, prixMontage);
					piecesOk.add(pcm);
				}
			}
			
		}
		
		return piecesOk;
	}
	
	
}
