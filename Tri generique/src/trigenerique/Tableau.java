package trigenerique;

public class Tableau<T extends Comparable>{
	private T[] tab;
	
	public Tableau(T[] tab) {
		this.tab=tab;
	}
	
	public void affichage() {
		for(T r : tab) {
			System.out.println(r.toString());
		}
	}
	
	public void triBulles() {
	     int i, j;
	        for (i = 0; i <= tab.length - 2; i++) {
	            for (j = tab.length - 1; j > i; j--) {
	                if (tab[j-1].estInferieur( tab[j])) {
	                    T aux = tab[j];
	                    tab[j] = tab[j - 1];
	                    tab[j - 1] = aux;
	                }
	            }
	        }
	    }
	
}