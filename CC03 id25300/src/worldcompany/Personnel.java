package worldcompany;

public class Personnel {

    Employe[] rh;

    public Personnel(Employe[] employes) {
        rh = employes;
    }

    public void presentations() {
        for (Employe e : rh) {
            System.out.println(e.presentation());
        }
    }

    public double salaireMoyen() {
    	double totalSalaire=0;
    	for(Employe e :rh) {
    		totalSalaire+=e.getSalaire();
    	}
    	return totalSalaire/rh.length;
    // TODO Complétez la méthode salaire moyen.
    }

    public void trierEmployes() {
    /* TODO Complétez la méthode trierEmployes.
-- Utiliser le tri à bulles.\n" +
--- Algorithme du tri à bulles (source: Wikipedia)\n" + 
	 tri_à_bulles(Tableau T)\n" + 
	    pour i allant de (taille de T)-1 à 1\n" + 
	        pour j allant de 0 à i-1\n" + 
	           si T[j+1] < T[j]\n" + 
	               échanger(T[j+1], T[j])\n" + 
	     		 fin si\n" + 
			  fin pour\n" + 
	    fin pour
    }*/
	     int i, j;
	        for (i = 0; i <= rh.length - 2; i++) {
	            for (j = rh.length - 1; j > i; j--) {
	                if (rh[j-1].compareTo( rh[j])>0) {
	                    Employe e = rh[j];
	                    rh[j] = rh[j - 1];
	                    rh[j - 1] = e;
	                }
	            }
	        }
	    }
}
