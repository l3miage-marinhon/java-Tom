package stats;

import java.util.Locale;

class Statistiques {
	//TODO declarer un tableau de double appartenant qu'a la class
	private double[] tab;
	private double max = 1000.0;
    //TODO faire le constructeur qui prend un tableau
    
	public Statistiques(double[] t){
    	tab = t;
    }
    
	
    //TODO faire un constructeur qui construit un tableau de double de manière random avec i element 
	public Statistiques(int i) {
		tab = new double[i];
		for(int n=0; n<i; n++) {
			tab[n] = Math.random()*200-100; //remplis le tableau avec des doubles entre 0.0 et 1000.
		}
	}
	
    //TODO afficher le tableau dans la console de cette maniere [1.00,2.00]
	public void afficheTableau() {
		System.out.print("[");
		int s = tab.length;
		for(int i=0; i<s; i++) {
			String str = String.format(Locale.US,"%.2f", tab[i]);
			System.out.print(str);
			if(i < s-1) System.out.print(","); 
		}
		System.out.println("]");
	}

    double getMin() {
    	if(tab.length>0){
	    	double minAct = max;
	    	for(int i=0; i<tab.length; i++) {
	    		minAct = (tab[i] < minAct) ? tab[i] : minAct;
	    	}
	    	return minAct;
	    }else{
    		return 0.0;
    	}
    	//TODO 
    }

    double getMax() {
    	if(tab.length>0){
	    	double maxAct = 0.0;
	    	for(double n : tab) {
	    		maxAct = (n > maxAct) ? n : maxAct;
	    	}
	    	return maxAct;
	    }else{
	    	return 0.0;
	    }
    // TODO
    }

    double moyenne() {
    	double moyenne= 0;
    	if(tab.length>0){
	    	double mean = 0.0;
	    	for(int i=0; i<tab.length; i++){
	    		mean += tab[i];
	    	}
	    	return mean/(double)tab.length;
	    }else{
	    	return 0.0;
	    }
    // TODO
    }

    double variance() {
    	if(tab.length>0){
	    	double var = 0.0;
	    	double mean = moyenne();
	    	for(double n : tab){
	    		var += Math.pow(n-mean, 2);
	    	}
	    	return var/tab.length;
	    	//pq tu caste ici ? 
	    }else{
	    	return 0.0;
	    }
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
