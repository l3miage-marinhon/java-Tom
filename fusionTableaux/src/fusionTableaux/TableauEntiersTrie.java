package fusionTableaux;

import java.util.ArrayList;

public class TableauEntiersTrie {
	private int T[];
	
	public TableauEntiersTrie(int tab1[], int tab2[]) {
		int c1 = 0;
		int c2 = 0;
		T = new int[tab1.length + tab2.length];
	
		for(int i=0; i<T.length; i++) {
			if(c1==tab1.length) { 
				T[i] = tab2[c2];
				c2++;
			}else if(c2==tab2.length) {
				T[i] = tab1[c1];
				c1++;
			}else {
				if(tab1[c1]<tab2[c2]) {
					T[i]=tab1[c1];
					c1++;
				}else {
					T[i]=tab2[c2];
					c2++;
				}
			}
		}
		
		/*
		for(int i=0; i<(tab1.length + tab2.length); i++) {
			if(c1==tab1.length || (c1<tab1.length && tab2[c2] <= tab1[c1])) { 
				T[i] = tab2[c2];
				c2++;
			}else if(c2==tab2.length || (c2<tab2.length && tab1[c1] <= tab2[c2])) {
				T[i] = tab1[c1];
				c1++;
			}
			if(C1)
		}
		 */
	}
	
	public boolean estTrie() {
		boolean trie = true;
		int i = 0;
		while(i<T.length-1 && trie) {
			if(T[i]>T[i+1]) {
				trie = false;
			}
			i++;
		}
		return trie;
	}
	
	public int valMaj() {
		int nbMax = 1;
		int nbAct = 1;
		int valMaj = T[0];
		int valAct = T[0];
		
		for(int i=1; i<T.length; i++) {	
			if(valAct != T[i]) {
				if(nbAct>nbMax) {
					valMaj = valAct;
					nbMax = nbAct;
				}
				valAct = T[i];
				nbAct = 1;
			}else {
				nbAct++;
			}
		}
		return (nbAct<=nbMax) ? valMaj : valAct;
	}
	
	public int nbValMaj() {
		int val = this.valMaj();
		int i=0;
		while ( i<T.length && T[i] != val ) {
			i++;
		}
		int n=1;
		while ( i<T.length-1 && T[i] == T[i+1]) {
			i++;
			n++;
		}
		return n;
	}
	
	public void elimineDupliques() {
		int l = T.length;
		int dec = 1;
	    for (int i = 0; i < l; i++) {
	    	if(T[i] == T[i+dec]) {
		    	while(T[i] == T[i+dec] && (i+dec)<l) {
		    		dec++;
		    	}
		    	dec--;
		        for (int j = i + 1; j < l-dec; j++) {
		        	T[j] = T[j+dec];
		        }
		        l -= dec;
		        dec = 1;
	    	}
	    }
	    int temp[] = new int[l];
	    for(int i = 0; i < l; i++){
	        temp[i] = T[i];
	    }
	    T = temp;
	}
	
	public void affichage() {
		System.out.print("{");
		for(int i=0; i<T.length; i++) {
			System.out.print(T[i] + (i<T.length-1 ? ", " : ""));
		}
		System.out.println("}");
	}
	
	public void affichageLargeur(int n) {
		for(int i=0; i<T.length; i++) {
			System.out.print(T[i] + " ");
			if((i+1)%n == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public void affichageHauteur(int n) {
		int c = (T.length%n==0) ? T.length/n : T.length/n + 1;
		for(int i=0; i<T.length; i++) {
			System.out.print(T[i] + " ");
			if((i+1)%c == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
}
