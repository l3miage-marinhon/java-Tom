/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package main;

import java.io.IOException;

import univ.Personne;


public class Main {

    // Programme pour tester vos classes.
    // 
    // 1. Enlever les lignes commentaires /* 1 et 1 */ quand vous aurez
    // écrit le constructeur de la classe Personne. Bien sûr, ça ne suffit
    // pas. Mais vous pourrez exécuter le programme et voir ce qu'il reste
    // à faire.
    // 
    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Quelque part dans le farwest ---- \n");
    
        Personne lucky = new Personne("Luke", "Lucky", true);
        Personne ma = new Personne("Dalton", "Ma", false);

        lucky.ditBonjourA(ma); // Ce simple salut doit entraîner la discussion suivante :          
         
    /*
        << Bonjour, je suis M. Lucky Luke >> dit Luke Lucky. 
        << Bonjour M. Lucky Luke.
           Moi c'est Mme Dalton Ma >> répond Ma Dalton.
        << Comment allez-vous ? >> continue Ma Dalton.
        << Ca va bien. Merci >> termine Luke Lucky.
        */
    }
}
