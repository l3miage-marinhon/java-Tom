package apobanque;

public class TestCompteBancaire {

    public static void main(String[] args) {
        CompteBancaire inconnu = new CompteBancaire();
        CompteBancaire ken = new CompteBancaire(2329, "Ken Bowie");
        CompteBancaire alain = new CompteBancaire(2330, "Alain Die", 500);
        System.out.println("Infos au départ....\n");
        System.out.print(inconnu.infos());
        System.out.print(ken.infos());
        System.out.print(alain.infos());
        double somme = 140.0;
        boolean succes;
        System.out.println("\nRetrait de " + somme + " euros...\n");
        succes = inconnu.retrait(somme);
        System.out.print(inconnu.infos());
        if (!succes) {
            System.out.println(inconnu.getClient() + " n'a pas pu retirer la somme de " + somme + "euros.\n");
        }
        succes = ken.retrait(somme);
        System.out.print(ken.infos());
        if (!succes) {
            System.out.println(ken.getClient() + " n'a pas pu retirer la somme de " + somme + "euros.\n");
        }
        succes = alain.retrait(somme);
        System.out.print(alain.infos());
        if (!succes) {
            System.out.println(alain.getClient() + " n'a pas pu retirer la somme de " + somme + "euros.\n");
        }
        somme = 100.0;
        System.out.println("\nDépôt de " + somme + " euros...\n");
        inconnu.depot(somme);
        System.out.print(inconnu.infos());
        ken.depot(somme);
        System.out.print(ken.infos());
        alain.depot(somme);
        System.out.print(alain.infos());
    }
}
