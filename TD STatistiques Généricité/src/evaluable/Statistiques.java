package evaluable;

public class Statistiques<T> {
	private T tab[];
	
	public Statistiques(T t[]) {
		tab = t;
	}
	
	public void afficherValeurs() {
		for(T elem : tab) {
			System.out.println(elem);
		}
	}
	
	public T min() {
		double min = Double.MAX_VALUE;
		T e = null;
		for(T elem : tab) {
			if(elem instanceof Etudiant && ((Etudiant)elem).valeur()<min) {
				min = ((Etudiant)elem).valeur();
				e = elem;
			}
		}
		return e;
	}
	
	public T max() {
		double max = Double.MIN_VALUE;
		T e = null;
		for(T elem : tab) {
			if(elem instanceof Etudiant && ((Etudiant)elem).valeur()>max) {
				max = ((Etudiant)elem).valeur();
				e = elem;
			}
		}
		return e;
	}
	
	public double moyenne() {
		double moy = 0;
		if(tab.length != 0) {
			for(T elem : tab) {
				if(elem instanceof Etudiant) {moy += ((Etudiant) elem).valeur();}
			}
			moy /= tab.length;
		}
		return moy;
	}
	
	public double variance() {
		double var = 0;
		if(tab.length != 0) {
			for(T elem : tab) {
				if(elem instanceof Etudiant) {var += ((Etudiant) elem).valeur() * ((Etudiant) elem).valeur() ;}
			} 
			var = var/tab.length - (this.moyenne()*this.moyenne());
		}
		return var;
	}
	
}
