package garage;

import java.util.ArrayList;

// TODO Ecrire la classe Garage
// 
public class Garage{
	public ArrayList<Vehicule> aReparer;
	public Vehicule[] aVendre;
	
	public Garage(Vehicule[] aVendre, ArrayList<Vehicule> aReparer) {
		this.aVendre=aVendre;
		this.aReparer=aReparer;
	}
	@Override
	public String toString() {
		String str="-----------Voiture a reparer------------\n";
		for(Vehicule v :aReparer) {
			str+=(v.toString()+"\n");
		}
		str+="-----------Voiture a vendre------------\n";
		for(Vehicule v :aVendre) {
			str+=(v.toString()+"\n");
		}
		return str;
	}
	public void trierAVendre() {
	    int i, j;
        for (i = 0; i <= aVendre.length - 2; i++) {
            for (j = aVendre.length - 1; j > i; j--) {
                if (aVendre[j-1].compareTo(aVendre[j])>0) {
                    Vehicule aux = aVendre[j];
                    aVendre[j] = aVendre[j - 1];
                    aVendre[j - 1] = aux;
                }
            }
        }
	}
	
	public void trierAReparerParConsomation(){
		aReparer.sort(new ConsoComparator());
	}
	public void trierAReparerParKm() {
		this.aReparer.sort(new KmComparator());
	}
	
	public void trierAReparerParImmatriculation(){
		aReparer.sort(new ImmatComparator());
	}
	
	
}