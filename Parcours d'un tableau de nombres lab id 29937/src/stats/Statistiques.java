package stats;

class Statistiques {

    double[] tableau;

    Statistiques(double[] tableau) {
        this.tableau = tableau;
    }

    Statistiques(int n) {
    	this.tableau=new double[n];
    	for(int i=0;i<n;i++) {
    		tableau[i]=(Math.random()*200-100);
    	}
    }

    void afficher() {
        System.out.print("[");
        for (double d : tableau) {
            System.out.print(String.format("%.2f", d) + " ");
        }
        System.out.println("]");
    }

    double getMin() {
    	double min=Double.MAX_VALUE;
    	for(double valeur:tableau) {
    		if(valeur<min) {
    			min=valeur;
    		}
    	}
        return min;
    }

    double getMax() {
    	double max=Double.MIN_VALUE;
    	for(double valeur:tableau) {
    		if(valeur>max) {
    			max=valeur;
    		}
    	}
        return max;
    // TODO
    }

    double moyenne() {
    	double somme=0;
    	for(double valeur:tableau) {
    		somme+=valeur;
    	}
        return somme/tableau.length;
    // TODO
    }

    double variance() {
    	double variance=0;
    	for(double valeur:tableau) {
    			variance+=Math.pow((valeur-this.moyenne()),2);
    	}
        return variance/tableau.length;
    // TODO
    }

    double ecartType() {
        return Math.sqrt(this.variance());
    // TODO
    }
}
