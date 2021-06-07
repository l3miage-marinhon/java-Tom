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

import edu.uha.miage.exceptions.BadHoraireException;
import edu.uha.miage.horaire.Horaire;

/**
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Main {

    public static void main(String[] args) throws BadHoraireException {
        // Ecrire ici vos tests personnels
        System.out.println("Ecrire vos tests personnels dans edu.uha.miage.Main");
        Horaire h1=new Horaire();
        System.out.println(h1.toString());
        Horaire h2=new Horaire(10,35);
        System.out.println(h2.toString());
        Horaire h3=new Horaire(10,45,45);
        System.out.println(h3.toString());
        Horaire hf=new Horaire();
        System.out.println(hf.toString());
        
    }
}
