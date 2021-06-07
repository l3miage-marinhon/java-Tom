package calculette;

/**
 * 
 * Evalue une expression.
 * L'expression est donnée sous forme de tableau de caractères.
 * L'expression ne contieent que des entiers et des opérateurs +, -, * ou /.
 * L'expression ne contient pas de parenthèse ou de nombre à virgule\n" + 
 *  (mais le résultat de l'expression peut être un réel, par exemple 10/3 = 3.33333...)
 * On ne tiendra pas compte de la priorité des opérateurs: 3+2*2 retournera 10 et non 7.
 * 
 * Exemple avec l'expression "15-3*10", on aura le tableau expression:
 *        -------------------------------------------
 * valeur | '1' | '5' | '-' | '3' | '*' | '1' | '0' |
 *        |-----|-----|-----|-----|-----|-----|-----|
 * indice |--0--|--1--|--2--|--3--|--4--|--5--|--6--|
 *        -------------------------------------------
 * 
 * Le résultat de l'appel à la méthode evalue sur le tableau précédant devra être 120
 * (rappel: on ne tien pass compte de la priorité des opérateurs)
 * 
 * Tip: pour obtenir la valeur 5 à partir du charactère '5', on peut utiliser le code suivant:
 * char c = '5';
 * int i = c - '0'; // ici, i vaut le nombre 5
 * 
 * Tip: pour savoir si un charactère est un chiffre, on peut utiliser la méthode Java:
 *    boolean Character.isDigit(char ch)
 *
 */
public class Calculette {
    /**
     * Variables globales: vous pouvez utiliser ces variables dans toutes les
     *  méthodes ci-dessous.
     */
	// Indice courant de l'expression: il gardera la valeur que vous lui donnerez
	//  dans d'une méthode à l'autre.  Il est conseillé de l'utiliser comme marqueur 
	// de lecture l'expression à évaluer (indice courant du tableau).
    public static int indiceCourant;
    
    // Expression sous forme de tableau de caractères. 
    public static char[] expression;

    
    /**
     * Evalue l'expression passée en paramètre et renvoie soin résultat sous forme de nombre réel.\n
     */
    public static double evalue(char[] exp) {
        double resultat = 0.0;
        expression = exp;
        indiceCourant = 0;
        int tailleTableau = expression.length;
        
        double op1;
        double op2;
        char operateur;
        // A compléter en appelant les méthodes ci-doses...
        op1=lireOperande();
        while(indiceCourant<tailleTableau) {
        		operateur=lireOperateur();
        		indiceCourant++;
        		op2=lireOperande();
        		op1=valeur(op1,operateur,op2);
        } 
        return op1;
    }

    /**
     * Lit l'opérande (i.e. le nombre) situé à la case indiceCourant du tableau expression
     * Attention: il peut s'agir d'un nombre à plusieurs chiffres !
     */ 
     public static int lireOperande() {
    	 int result=0;
    	 while(Character.isDigit(expression[indiceCourant])){
    		 result+=expression[indiceCourant]-'0';
    		 System.out.println(expression.length);
    		 if(indiceCourant<expression.length) {
    			 if(Character.isDigit(expression[indiceCourant+1])){
    				 result*=10;
    			 }
    			indiceCourant++;
    		 }
    	
    	 }
    	 return result;
    }


    /**
     * Renvoie l'opérateur sous forme de character, situé à la place indiceCourant du tableau expression
     */
    public static char lireOperateur() {
    	if(!Character.isDigit(expression[indiceCourant])){
   		 return expression[indiceCourant];
   	 }
    	return 0;
    }
    
    /**
     * Calcule la valeur v1 operateur v2
     * Exemples:
     *      v1 = 4, v2 = 6 et operateur = '+', renvoie 10.0
     *      v1 = 4, v2 = 2 et operateur = '-', renvoie 2.0
     *      v1 = 5, v2 = 2 et operateeur = '/', renvoie 2.5
     *      renvoie 0.0 si l'opérateur n'est aucun de ceux autorisé
     */
    public static double valeur(double v1, char operateur, double v2) {
    	switch(operateur){
        case '-':return v1-v2;
        case '+':return v1+v2;
        case '*':return v1*v2;
        case '/':return v1/v2;
        default:return 0.0;
          
    	}
    }
}
