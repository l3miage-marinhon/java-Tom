package stats;

public class TestStats {

    public static void main(String[] args) {
        double[] t = { 1.5, 1.0, -5.2, -34.3 };
        Statistiques stats = new Statistiques(t);
        stats.afficher();
		stats = new Statistiques(10);
		stats.afficher();
        System.out.println("Elément minimun du tableau: " + stats.getMin());
        System.out.println("Elément maximum du tableau: " + stats.getMax());
        System.out.println("Moyenne du tableau: " + stats.moyenne());
        System.out.println("Variance du tableau: " + stats.variance());
        System.out.println("Ecart type du tableau: " + stats.ecartType());
    }
}
