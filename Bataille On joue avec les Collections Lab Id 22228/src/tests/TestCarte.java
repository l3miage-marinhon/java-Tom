package tests;

import bataille.Carte;
import bataille.Couleur;
import bataille.Valeur;

public class TestCarte {

	public static void main(String[] args) {
		Carte roiDeCoeur = new Carte(Couleur.COEUR, Valeur.ROI);
		Carte dameDePique = new Carte(Couleur.PIQUE, Valeur.DAME);
		Carte dameDeCoeur = new Carte(Couleur.COEUR, Valeur.DAME);
		Carte dixDeCarreau = new Carte(Couleur.CARREAU, Valeur.DIX);
		Carte dixCarreau = new Carte(Couleur.CARREAU, Valeur.DIX);
		
		System.out.println(testCompareTo(roiDeCoeur, dameDePique, 1));
		System.out.println(testCompareTo(dameDePique, dameDeCoeur, 0));
		System.out.println(testCompareTo(dixDeCarreau, dameDeCoeur, -1));
		System.out.println(testCompareTo(dixDeCarreau, dixCarreau, 0));
		
		System.out.println(testEquals(roiDeCoeur, dameDePique, false));
		System.out.println(testEquals(dameDePique, dameDeCoeur, false));
		System.out.println(testEquals(dixDeCarreau, dameDeCoeur, false));
		System.out.println(testEquals(dixDeCarreau, dixCarreau, true));
		

	}

	private static String testCompareTo(Carte carte1, Carte carte2, int expectedValue) {
		int compare;
		String msg;
		// On compare les cartes entre elles
		compare = carte1.compareTo(carte2);
		msg = "La carte " + carte1 + "(fichier: " + carte1.getFileName() + ")";
		if (compare < 0) {
			msg += " est plus petit que la carte " + carte2 +  "(fichier: " + carte2.getFileName() + ").";
		} else if (compare > 0) {
			msg += " est plus grande que la carte " + carte2 +  "(fichier: " + carte2.getFileName() + ").";
		} else {
			msg += " a la mÃªme valeur que la carte " + carte2 +  "(fichier: " + carte2.getFileName() + ").";
		}

		if (((compare < 0)  && (expectedValue < 0)) ||
			((compare > 0)  && (expectedValue > 0)) ||
			((compare == 0) && (expectedValue == 0))) {
			msg += "\n C'est vrai !";
		} else {
			msg += "\n C'est faux ! => Il doit y avoir un problÃ¨me dans votre mÃ©thode compareTo";			
		}
		return msg;
	}

	private static String testEquals(Carte carte1, Carte carte2, boolean expectedValue) {
		boolean compare;
		String msg;
		// On compare les cartes entre elles
		compare = carte1.equals(carte2);
		msg = "La carte " + carte1 + "(fichier: " + carte1.getFileName() + ")";
		if (compare) {
			msg += " est identique Ã  la carte " + carte2 +  "(fichier: " + carte2.getFileName() + ").";
		} else {
			msg += " est diffÃ©rente de la carte " + carte2 +  "(fichier: " + carte2.getFileName() + ").";
		}

		if (compare == expectedValue) {
			msg += "\n C'est vrai !";
		} else {
			msg += "\n C'est faux ! => Il doit y avoir un problÃ¨me dans votre mÃ©thode equals";			
		}
		return msg;
	}
}
