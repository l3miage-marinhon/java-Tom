package stats;

import java.util.Locale;

class Statistiques {
	//TODO declarer un tableau de double appartenant qu'a la class
	private double[] tab;
    //TODO faire le constructeur qui prend un tableau
    
	public Statistiques(double[] t){
    	tab = t;
    }
    
	
    //TODO faire un constructeur qui construit un tableau de double de manière random avec i element 
	public Statistiques(int i) {
		tab = new double[i];
		for(int n=0; n<i; n++) {
			tab[n] = Math.random()*2000-1000; //remplit le tableau avec des doubles entre -1000.0 et +1000.
			//tab[n] = Math.random()*2.0*Double.MAX_VALUE - Double.MAX_VALUE; //remplit le tableau avec des doubles entre -Double.MAX_VALUE et +Double.MAX_VALUE.
		}
	}
	
    //TODO afficher le tableau dans la console de cette maniere [1.00,2.00]
	public void afficheTableau() {
		System.out.print("[");
		for(int i=0; i<tab.length; i++) {
			String str = String.format(Locale.US,"%.2f", tab[i]);
			System.out.print(str);
			if(i < tab.length-1) System.out.print(","); 
		}
		System.out.println("]");
	}

    double getMin() {
	    double minAct = Double.MAX_VALUE;
	    for(int i=0; i<tab.length; i++) {
	    	minAct = (tab[i] < minAct) ? tab[i] : minAct;
	    }
	    return (tab.length>0) ? minAct : 0.0;
    	//TODO 
    }

    double getMax() {
	    double maxAct = -Double.MAX_VALUE;
	    for(double n : tab) {
	    	maxAct = (n > maxAct) ? n : maxAct;
	    }
	    return (tab.length>0) ? maxAct : 0.0;
	    // TODO
    }

    double moyenne() {
	    double mean = 0.0;
	    for(int i=0; i<tab.length; i++){
	    	mean += tab[i];
	    }
	    return mean / ((tab.length>0) ? tab.length : 1);
    // TODO
    }

    double variance() {
    	double var = 0.0;
	    double mean = moyenne();
	    for(double n : tab){
	    	var += Math.pow(n-mean, 2);
	    }
	    return var / ((tab.length>0) ? tab.length : 1);
	    // TODO
    }

    double ecartType() {
        return Math.sqrt(variance());
    // TODO
    }
    
    //lorsque tu fait une fonction essaye de n'avoir qu'un seul return a chaque fois !! donc si je reprend la fonction variance par exemple
    //on aurait un truc du genre

   /*double variance() {
	    double var = 0.0;
	    double mean = moyenne();
	    for(double n : tab){
	    	var += Math.pow(n-mean, 2);
	    }
	    return  (tab.length > 0 ) ? var/(double)tab.length : 0;
	    	//pq tu caste ici ? 
    // TODO
    }*/
    
    
    
}
