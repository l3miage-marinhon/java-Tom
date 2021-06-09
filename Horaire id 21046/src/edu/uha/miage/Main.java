/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package edu.uha.miage;

/**
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        // Ecrire ici vos tests personnels
        System.out.println("Ecrire vos tests personnels dans edu.uha.miage.Main");
        Horaire h112358 = new Horaire(false, 11, 23, 58);
        System.out.println("11:23:58 = " + h112358.toString());
        Horaire h112300 = new Horaire(false, 11, 23);
        System.out.println("11:23:00 = " + h112300.toString());
        Horaire h110000 = new Horaire(false, 11);
        System.out.println("11:00:00 = " + h110000.toString());
        Horaire h000000 = new Horaire();
        System.out.println("00:00:00 = " + h000000.toString());
        
        h000000.uneSecondeDePlus();
        System.out.println("00:00:00 + 1 seconde = " + h000000.toString());
        h000000.uneMinuteDePlus();
        System.out.println("00:00:01 + 1 minute = " + h000000.toString());
        h000000.uneHeureDePlus();
        System.out.println("00:01:01 + 1 heure = " + h000000.toString());
        
        Horaire h231254 = new Horaire(true, 23, 12, 54);
        System.out.println("23:12:54 = " + h231254.toString());
        h231254.uneHeureDePlus();
        System.out.println("23:12:54 + 1 heure = " + h231254.toString());
        h112358.uneHeureDePlus();
        System.out.println("11:23:58 + 1 heure = " + h112358.toString());
        
        Horaire h132358 = new Horaire(false, 13, 23, 58);
        System.out.println("13:23:58 heure sur 12 = " + h112358.toString());
        
        Horaire h246060 = new Horaire(true, 24, 60, 60);
        System.out.println("24:60:60 heure sur 24 = " + h246060.toString());
        
        Horaire h235959 = new Horaire(true, 23, 59, 59);
        h235959.uneSecondeDePlus();
        System.out.println("23:59:59 + 1 seconde = " + h235959.toString());
        
        h235959 = new Horaire(true, 23, 59, 59);
        h235959.uneMinuteDePlus();
        System.out.println("23:59:59 + 1 minute = " + h235959.toString());
        
        h235959 = new Horaire(true, 23, 59, 59);
        h235959.uneHeureDePlus();
        System.out.println("23:59:59 + 1 heure = " + h235959.toString());
        
        Horaire h235859 = new Horaire(true, 23, 58, 59);
        h235859.uneSecondeDePlus();
        System.out.println("23:58:59 + 1 seconde = " + h235859.toString());
    }
}
