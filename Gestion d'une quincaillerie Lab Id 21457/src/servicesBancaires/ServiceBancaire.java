package servicesBancaires;

import clients.Client;
import quincaillerie.Quincaillerie;

public abstract class ServiceBancaire {
	
	/**
	 * Effectue un prélèvement d'un certain montant chez un client, uniquement si le client possède suffisement de crédits
	 * @param client {@linkplain Client} le client prélevé
	 * @param montant {@linkplain Double} le montant du prélèvement
	 * @return {@linkplain Boolean} true si le prélèvement s'est correctement passé, false sinon
	 */
	public static boolean prelevementCreditClient(Client client, double montant) {
		boolean pOk = false;
		if(client.getCredit() >= montant) {
			client.setCredit(client.getCredit() - montant);
			pOk = true;
		}else {
			System.out.println("Crédit insuffisant");
		}
		return pOk;
	}
	
	/**
	 * Effectue l'approvisionnement des crédits du client d'un certain montant
	 * @param client {@linkplain Client} le client 
	 * @param montant {@linkplain Double} le montant de l'approvisionnement
	 */
	public static void approvisionneCreditClient(Client client, double montant) {
		client.setCredit(client.getCredit() + montant);
	}
	
	/**
	 * Effectue l'approvisionnement de la trésorerie de la quincaillerie d'un certain montant
	 * @param quincaillerie {@linkplain Quincaillerie} la quincaillerie
	 * @param montant {@linkplain Double} le montant de l'approvisionnement
	 */
	public static void approvisionneTresorerieQuincaillerie(Quincaillerie quincaillerie, double montant) {
		quincaillerie.setTresorerie(quincaillerie.getTresorerie() + montant);
	}
	
	/**
	 * Effectue un prélèvement d'un certain montant chez la quincaillerie, uniquement si cette dernière possède suffisement de trésorerie
	 * @param quincaillerie {@linkplain Quincaillerie} la quincaillerie prélevée
	 * @param montant {@linkplain Double} le montant du prélèvement
	 * @return {@linkplain Boolean} true si le prélèvement s'est correctement passé, false sinon
	 */
	public static boolean prelevementTresorerieQuincaillerie(Quincaillerie quincaillerie, double montant) {
		boolean pOk = false;
		if(quincaillerie.getTresorerie() >= montant) {
			quincaillerie.setTresorerie(quincaillerie.getTresorerie() - montant);
			pOk = true;
		}else {
			System.out.println("Trésorerie insuffisante");
		}
		return pOk;
	}
}
