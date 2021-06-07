package calculette;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestCalculette {
	public static void main(String[] args) throws IOException {
        String expression;
        String reponse;
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        do {
            System.out.println("Veuillez entrer une expression (pas d'espace, pas de virgule, pas de parenthèse)");
            expression = reader.readLine();
            if (expression != null) {
            	printExpression(expression);
                System.out.println("L'expression : " + expression + " a pour valeur: " + Calculette.evalue(expression.toCharArray()));
            }
            System.out.println("Voulez-vous continuer (o/n) ?");
            reponse = reader.readLine();
        } while(("o".equals(reponse)) || ("O".equals(reponse)) || ("oui".equals(reponse)) || "OUI".equals(reponse));
    }
	
	public static void printExpression(String exp) {
		char[] expression = exp.toCharArray();
        System.out.println("Expression: ");
        System.out.print("-");
        for (int i = 0; i < expression.length; i++) {
        	System.out.print("------");
        }
        System.out.println();
        System.out.print("|");
        for (char c: expression) {
        	System.out.print(" '" + c + "' |");
        }
        System.out.println();
        System.out.print("|");
        for (int i = 0; i < expression.length; i++) {
        	System.out.print("-----|");
        }
        System.out.println();
        System.out.print("|");
        for (int i = 0; i < expression.length; i++) {
        	System.out.print("--" + i + "--|");
        }
        System.out.println();
        System.out.print("-");
        for (int i = 0; i < expression.length; i++) {
        	System.out.print("------");
        }
        System.out.println();
        
        
		
	}
}
