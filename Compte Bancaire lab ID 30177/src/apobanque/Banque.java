package apobanque;

public class Banque {

    // Nom de la banque
    String nom;

    // Liste des comptes clients
    CompteBancaire[] comptes;

    Banque(String nom, CompteBancaire[] comptes) {
    	this.nom=nom;
    	this.comptes=comptes;
    }

    String description() {
    	String description="Banque: "+nom+" [avoir total: "+this.avoirTotal()+"]\n";
        for(CompteBancaire compte:comptes) {
        	description+=compte.infos()+"\n";
        }
        return description;
    }

    double avoirTotal() {
    	double avoirT=0;
        for(CompteBancaire compte:comptes) {
        	avoirT+=compte.getSolde();
        }
        return avoirT;
    }

    void afficheNumeroComptesDebitsPlanchers() {
    	for(CompteBancaire compte:comptes) {
    		if(compte.getSolde()>=0) {
    			System.out.println(compte.getNumero());
    		}
    	}
    }
}
