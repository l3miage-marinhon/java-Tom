package number;

public class TestTriplet {

    public static void main(String[] args) {
        Triplet triplet = new Triplet(5, 3, 8);
        System.out.print("La somme des 3 nombres: ");
        triplet.affiche();
        System.out.print(" est: " + triplet.sum());
    }
}
