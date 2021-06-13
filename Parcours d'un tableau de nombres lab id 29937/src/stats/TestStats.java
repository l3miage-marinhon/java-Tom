package stats;
import java.util.Locale;

public class TestStats {

    public static void main(String[] args) {
    	//TODO faire les tous les test pertinent
    	Statistiques tableau1 = new Statistiques(10);
    	tableau1.afficheTableau();
    	System.out.println("max = " + String.format(Locale.US, "%.2f", tableau1.getMax()));
    	System.out.println("min = " + String.format(Locale.US, "%.2f", tableau1.getMin()));
    	System.out.println("moyenne = " + String.format(Locale.US, "%.2f", tableau1.moyenne()));
    	System.out.println("variance = " + String.format(Locale.US, "%.2f", tableau1.variance()));
    	System.out.println("ecart type = " + String.format(Locale.US, "%.2f", tableau1.ecartType()));
    	
    	System.out.println();
    	
    	double[] t = {100.00, 200.00, 250.00, 300.00, 500.00};
    	Statistiques tableau2 = new Statistiques(t);
    	tableau2.afficheTableau();
    	System.out.println("max = " + String.format(Locale.US, "%.2f", tableau2.getMax()));
    	System.out.println("min = " + String.format(Locale.US, "%.2f", tableau2.getMin()));
    	System.out.println("moyenne = " + String.format(Locale.US, "%.2f", tableau2.moyenne()));
    	System.out.println("variance = " + String.format(Locale.US, "%.2f", tableau2.variance()));
    	System.out.println("ecart type = " + String.format(Locale.US, "%.2f", tableau2.ecartType()));
    	
    	System.out.println();
    	
    	Statistiques tableau3 = new Statistiques(0);
    	tableau3.afficheTableau();
    	System.out.println("max = " + String.format(Locale.US, "%.2f", tableau3.getMax()));
    	System.out.println("min = " + String.format(Locale.US, "%.2f", tableau3.getMin()));
    	System.out.println("moyenne = " + String.format(Locale.US, "%.2f", tableau3.moyenne()));
    	System.out.println("variance = " + String.format(Locale.US, "%.2f", tableau3.variance()));
    	System.out.println("ecart type = " + String.format(Locale.US, "%.2f", tableau3.ecartType()));
    	
    	System.out.println();
    	
    	Statistiques tableau4 = new Statistiques(1);
    	tableau4.afficheTableau();
    	System.out.println("max = " + String.format(Locale.US, "%.2f", tableau4.getMax()));
    	System.out.println("min = " + String.format(Locale.US, "%.2f", tableau4.getMin()));
    	System.out.println("moyenne = " + String.format(Locale.US, "%.2f", tableau4.moyenne()));
    	System.out.println("variance = " + String.format(Locale.US, "%.2f", tableau4.variance()));
    	System.out.println("ecart type = " + String.format(Locale.US, "%.2f", tableau4.ecartType()));
    	
    	System.out.println();
    	
    	Statistiques tableau5 = new Statistiques(2);
    	tableau5.afficheTableau();
    	System.out.println("max = " + String.format(Locale.US, "%.2f", tableau5.getMax()));
    	System.out.println("min = " + String.format(Locale.US, "%.2f", tableau5.getMin()));
    	System.out.println("moyenne = " + String.format(Locale.US, "%.2f", tableau5.moyenne()));
    	System.out.println("variance = " + String.format(Locale.US, "%.2f", tableau5.variance()));
    	System.out.println("ecart type = " + String.format(Locale.US, "%.2f", tableau5.ecartType()));
    	
    }
}
