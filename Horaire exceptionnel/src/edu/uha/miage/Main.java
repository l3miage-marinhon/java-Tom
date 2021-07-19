/*
 * Copyright (C) 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage;

import edu.uha.miage.exceptions.*;

import edu.uha.miage.horaire.Horaire;

/**
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Main {

    public static void main(String[] args) throws IncorrectFormat, IncorrectHour, IncorrectMinute, IncorrectSecond{
        // Ecrire ici vos tests personnels
        System.out.println("Ecrire vos tests personnels dans edu.uha.miage.Main");
        Horaire h1 = new Horaire();
        System.out.println("h1 : " + h1.toString());
        
        Horaire h2 = new Horaire(24,10,35);
        System.out.println("h2 : " + h2.toString());
        
        h2.uneHeureDePlus();
        System.out.println("h2 : " + h2.toString());

        
        Horaire h3 = new Horaire(13,9,34,46);
        System.out.println("h3 : " + h3.toString());
        
        Horaire h4 = new Horaire(24,25,12,11);
        System.out.println("h4 : " + h4.toString());
        
        Horaire h5 = new Horaire(24,15,60,11);
        System.out.println("h5 : " + h5.toString());
        
        Horaire h6 = new Horaire(12,11,30,69);
        System.out.println("h6 : " + h6.toString());
        
        Horaire h7 = new Horaire(12,16,30,29);
        System.out.println("h7 : " + h7.toString());  
        
        try {
        	Horaire htest = Horaire.horaireFromString("24:34");
        	System.out.println("htest : " + htest);
        }catch(WrongStringFormatSize e) {
        	e.printStackTrace();
        }
        
        try {
        	Horaire htest2 = Horaire.horaireFromString("24:23:34:02");
        	System.out.println("htest2 : " + htest2);
        }catch(WrongStringFormatSize e) {
        	e.printStackTrace();
        }
        
        /*
         * le seul truc qui me gène est que le constructeur continue d'instancier l'objet meme quand un des setters
         * va throw une exception. La seule manière de gérer ce problème que j'ai trouver est de faire lever une
         * exception au constructeur, ou de faire une methode static qui appelle le constructeur et lève une exception
         * si problème il y a
         */
    	
    }
}
