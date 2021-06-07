package garage;

import java.util.ArrayList;

public class GestionGarage {

    public static void main(String[] args) {
  
     //  A decommenté une fois les véhicules terminés (avant les tris)
     // 		// Je vous présente titine.
      		System.out.println("---- Titine ----");
      		// Titine est une citadine, elle consomme 5.2L d'essence tous les 100km
      		Vehicule titine = new Citadine();
      		System.out.println(titine);
      		titine.faireLePlein();
      		// Avec 50L d'essence dans le réservoir, techniquement, je ne peux pas faire 1000km...
      		titine.rouler(1000.0);
      		System.out.println(titine);
      		// Mais si je refais le plein, je dois pouvoir parcourir 602.3km
      		titine.faireLePlein();
      		titine.rouler(602.3);
      		System.out.println(titine);
     
      		// Au garage, il y a 10 véhicules.
      		System.out.println("---- Véhicules ----");
     
      		Vehicule[] aVendre = new Vehicule[10];
      		aVendre[0] = new Citadine();
      		aVendre[1] = new Citadine();
      		aVendre[2] = new Compacte();
      		aVendre[3] = new Compacte();
      		aVendre[4] = new QuatreQuatre();
      		aVendre[5] = new QuatreQuatre();
      		aVendre[6] = new Berline();
      		aVendre[7] = new Compacte();
      		aVendre[8] = new Berline();
      		aVendre[9] = new Citadine();
     
      		// On fait rouler un peu au hasard les voitures...
      		for (Vehicule v: aVendre) {
      			int nbIterations = (int) (Math.random()*100);
      			for (int k = 0; k < nbIterations; k++) {
      				v.faireLePlein();
      				double nbKilometres = Math.random()*1000;
      				v.rouler(nbKilometres);
      			}
      		}
     
      		// On affiche le tout
      		for (Vehicule v: aVendre) {
      			System.out.println(v);
      		}

       //A décommenter une fois le garage implémenté
      		// On met les même voitures dans une arrayList
      		ArrayList<Vehicule> aReparer = new ArrayList<Vehicule>();
      		for (Vehicule v: aVendre) {
      			aReparer.add(v);
      		}
     
      		Garage garage = new Garage(aVendre, aReparer);
      		System.out.println("---- Garage ----");
      		System.out.println(garage);
     
      		garage.trierAVendre();
      		garage.trierAReparerParConsomation();
      		System.out.println("---- Garage Trié par Km ----");
      		System.out.println(garage);
    }
}
