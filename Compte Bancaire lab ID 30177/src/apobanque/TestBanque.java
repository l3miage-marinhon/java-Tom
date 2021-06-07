package apobanque;

public class TestBanque {

    public static void main(String[] args) {
        String nom = "APO Banque";
        CompteBancaire[] comptes = new CompteBancaire[5];
        int numero = 2350;
        String[] clients = { "Alex Ception", "Alphonse Danlmur", "Andre Naline", "Candy Raton", "Colette Sterole"};
        double[] sommes = { 0.0, 254.80, 3612.28, 50.0, 150.0};
        // Remplissage du tableau comptes avec les données ci-dessus...
        for(int i=0;i<comptes.length;i++) {
        	if(sommes[i]==0) {
        		comptes[i]=new CompteBancaire(numero+i,clients[i]);
        	}else{
        		comptes[i]=new CompteBancaire(numero+i,clients[i],sommes[i]);
        	}
        }
        // TODO...
        Banque banque = new Banque(nom, comptes);
        System.out.println(banque.description());
        comptes[0].retrait(150);
        comptes[1].retrait(500.0);
        comptes[3].depot(50.0);
        comptes[4].retrait(25.0);
        comptes[4].depot(25);
        System.out.println(banque.description());
        banque.afficheNumeroComptesDebitsPlanchers();
    }
}
