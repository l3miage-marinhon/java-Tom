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
package edu.uha.miage.horaire;

import java.util.ArrayList;

import edu.uha.miage.exceptions.*;
/**
 * Une classe horaire pour gérer des horaires (heures, minutes, secondes).
 *
 * La responsabilité de cette classe est de garantir un horaire cohérent,
 *
 * c'est-à-dire
 *
 * 1. dont les heures sont dans [0, 23]
 *
 * 2. dont les secondes sont dans [0, 59]
 *
 * 3. dont les minutes sont dans [0, 59]
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Horaire {
	//TODO mettre les attribue de la classes
	private int f;
	private int h;
	private int m;
	private int s;
	
	
    /**
     * TODO 1.02 Ecrire le constructeur qui construit un nouvel horaire à partir
     * des arguments h m et s.
     * @param h 
     *
     * @param h les heures de cet horaire. Les heures correctes sont dans [0,
     * 23]
     * @param m 
     *
     * @param m les minutes de cet horaire. Les minutes correctes sont dans [0,
     * 59]
     * @param s 
     *
     * @param s les secondes de cet horaire. Les secondes correctes sont dans
     * [0, 59]
     *
     * @throws edu.uha.miage.exceptions.BadHoraireException si l'horaire donnée
     * est incorrect.
     */
	public Horaire(int f, int h, int m, int s) {
		try {
			setF(f);
			setH(h);
			setM(m);
			setS(s);
		}catch(IncorrectFormat IF) {
			IF.printStackTrace();
		}catch(IncorrectHour IH) {
	    	IH.printStackTrace();
	    }catch(IncorrectMinute IM) {
	    	IM.printStackTrace();
	    }catch(IncorrectSecond IS) {
	    	IS.printStackTrace();
	    }
	}
    /**
     
     * 
     * TODO 1.03. Ecrire le constructeur qui construit un nouvel horaire à
     * partir des arguments h et m (les secondes sont nulles).
     *
     * @param h les heures de cet horaire. Les heures correctes sont dans [0,
     * 23]
     *
     * @param m les minutes de cet horaire. Les minutes correctes sont dans [0,
     * 59]
     *
     * @throws edu.uha.miage.exceptions.BadHoraireException si l'horaire donnée
     * est incorrect.
     */
    public Horaire(int f, int h, int m) {
    	this(f, h, m, 0);
    }
    /**
     * TODO 1.04. Ecrire le constructeur qui construit un nouvel horaire à
     * partir d'un seul argument : l'heure (les secondes et les minutes sont nulles).
     *
     * @param h les heures de cet horaire. Les heures correctes sont dans [0,
     * 23]
     *
     * @throws edu.uha.miage.exceptions.BadHoraireException si l'horaire donnée
     * est incorrect.
    */
    public Horaire(int f, int h) {
    	this(f, h, 0, 0);
    }
    /**
     * TODO 1.05. Ecrire le constructeur par défaut qui construit l'horaire
     * 00:00:00.
     */
    public Horaire() {
    	this(24, 0, 0, 0);
    }
	//TODO faire les getter et setters
    
	public int getF() {
		return f;
	}
	private void setF(int f) throws IncorrectFormat{
		if(f != 12 && f != 24) {
			throw new IncorrectFormat("Mauvais format d'heure (12 ou 24)");
		}else {
			this.f = f;		
		}
	}
	
	public int getH() {
		return h;
	}
	private void setH(int h) throws IncorrectHour{
		if( f == 24 && (h < 0 || h > 23) ){
			throw new IncorrectHour("Erreur sur l'heure renseignée (entre 0 et 23)");
		}else if( f == 12 && (h < 0 || h > 11) ) {
			throw new IncorrectHour("Erreur sur l'heure renseignée (entre 0 et 11)");
		}else {
			this.h = h;
		}
	}
	
	public int getM() {
		return m;
	}
	private void setM(int m) throws IncorrectMinute{
		if( m < 0 || m > 59) {
			throw new IncorrectMinute("Erreur sur les minutes renseignées (entre 0 et 59)");
		}else {
			this.m = m;
		}
	}
	
	public int getS() {
		return s;
	}
	private void setS(int s) throws IncorrectSecond{
		if( s < 0 || s > 59 ) {
			throw new IncorrectSecond("Erreur sur les secondes renseignées (entre 0 et 59)");
		}else {
			this.s = s;
		}
	}

	//TODO 
    /**
     * Retourne un horaire sous la forme d'une chaîne de caractères sous la
     * forme "hh:mm:ss" où hh, mm, ss sont les heures, les minutes et les
     * secondes sur deux chiffres.
     *
     * Par exemple "00:00:00", "02:33:59"
     *
     * @return un horaire de la forme "hh:mm:ss"
     */
	@Override
    public String toString() {
    // TODO 1.18. Écrire toString()
    	return String.format("%02d:%02d:%02d", h, m, s) ;
    }
    /**
     * TODO Augmente cet horaire d'une heure.
     */
	public void uneHeureDePlus() {
		this.h = h+1 > 23 ? 0 : h+1 ;
	}
    /**
     * TODO Augmente cet horaire d'une minute.
     */
	public void uneMinuteDePlus() {
		this.m = m+1 > 59 ? 0 : m+1;
	}
    /**
     * TODO Augmente cet horaire d'une seconde.
     */
	public void uneSecondeDePlus() {
		this.s = s+1 > 59 ? 0 : s+1;
	}
    
    
    
    // 
    /* TODO 1.07 Méthode publique de classe qui retourne un horaire à partir
		d'une chaîne de caractères selon le principe suivant :
		
		Un horaire peut s'écrire sous la forme <hh:mm> ou encore <hh:mm:ss> où
		<hh> sont les heures, <mm> les minutes et <ss> les secondes
		qui valent 0 en cas d'absence.
		
		<hh> doit être une chaîne qui représente un entier dans [0,23]
		<mm> doit être une chaîne qui représente un entier dans [0,59]
		<ss> doit être une chaîne qui représente un entier dans [0,59]
		<hh>, <mm>, <ss> peuvent s'écrire avec 1 ou 2 chiffres, avec 
		un éventuel 0 devant.
		Par exemple,
		sont admis "02:25" "2:25" "2:2" "12:25:02" "15:00:00" "0:0:0"
		Ne sont pas admis : "25:00" "2h2" "13:45:62"
		*/
	public static Horaire horaireFromString(String s) throws WrongStringFormatSize{
		Horaire horaire = null;
		String elemStr[] = s.split(":");
		ArrayList<Integer> elemInt = new ArrayList<>();
		for(String e : elemStr) {
			try {
				elemInt.add(Integer.parseInt(e));
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		if(elemInt.size() < 3 || elemInt.size() > 4) {
			throw new WrongStringFormatSize("Chaine de caractères incorrecte (il faut 3 ou 4 champs séparés par les ':' )");
		}else {
			int format = elemInt.get(0);
			int heure = elemInt.get(1);
			int minute = elemInt.get(2);
			int seconde = 0;
			if(elemInt.size() == 4) {
				seconde = elemInt.get(3);
			}
			horaire = new Horaire(format, heure, minute, seconde);
		}
		return horaire;
	}
}


