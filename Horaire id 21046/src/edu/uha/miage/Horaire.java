/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package edu.uha.miage;

/**
 * Une classe horaire pour gérer des horaires (heures, minutes, secondes).
 *
 * Les horaires appartiennent à l'un des deux formats 24h ou 12h
 *
 * Le format 24h signifie que les heures sont dans [0, 23] Le format 12h
 * signifie que les heures sont dans [0, 11]
 *
 * La responsabilité de cette classe est de garantir un horaire cohérent,
 *
 * c'est-à-dire
 *
 * 1. dont les secondes sont dans [0, 59]
 *
 * 2. dont les minutes sont dans [0, 59]
 *
 * 2. dont les heures sont dans [0, 11] au format 12h ou dans [0,23] au format
 * 24h
 *
 * @author yvan
 */
public class Horaire {
	private boolean h24;
	private int h;
	private int m;
	private int s;
    /**
     * Construit un nouvel horaire à partir des arguments.
     *
     * @param h24 vrai => format 24h, faux => format 12h
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     *
     * @param m les minutes de cet horaire. Si m n'est pas dans [0, 59], il est
     * considéré égal à 0.
     *
     * @param s les secondes de cet horaire. Si s n'est pas dans [0, 59], il est
     * considéré égal à 0.
     */
    public Horaire(boolean h24, int h, int m, int s) {
    // TODO 1.02. Écrire ce constructeur
    	setH24(h24) ;
    	setHeures(h) ;
    	setMinutes(m) ;
    	setSecondes(s) ;
    }

    /**
     * Construit un nouvel horaire à partir des arguments (les secondes sont
     * nulles).
     *
     * @param h24 vrai => format 24h, faux => format 12h
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     *
     * @param m les minutes de cet horaire. Si m n'est pas dans [0, 59], il est
     * considéré égal à 0.
     */
    public Horaire(boolean h24, int h, int m) {
    // TODO 1.03. Écrire ce constructeur
    	this(h24, h, m, 0) ;
    }

    /**
     * Construit un nouvel horaire à partir des arguments (les secondes et les
     * minutes sont nulles).
     *
     * @param h24 vrai => format 24h, faux => format 12h
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     */
    public Horaire(boolean h24, int h) {
    // TODO 1.04. Écrire ce constructeur
    	this(h24, h, 0, 0) ;
    }

    /**
     * Construit l'horaire 00:00:00 au format 24h ou 12h.
     *
     * @param h24 vrai => format 24h, faux => format 12h
     */
    public Horaire(boolean h24) {
    // TODO 1.05. Écrire ce constructeur
    	this(h24, 0, 0, 0) ;
    }

    /**
     * Construit un nouvel horaire au format 24h à partir des arguments.
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     *
     * @param m les minutes de cet horaire. Si m n'est pas dans [0, 59], il est
     * considéré égal à 0.
     *
     * @param s les secondes de cet horaire. Si s n'est pas dans [0, 59], il est
     * considéré égal à 0.
     */
    public Horaire(int h, int m, int s) {
    // TODO 1.06. Écrire ce constructeur
    	this(true, h, m, s) ;
    }

    /**
     * Construit un nouvel horaire au format 24h à partir des arguments (les
     * secondes sont nulles).
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     *
     * @param m les minutes de cet horaire. Si m n'est pas dans [0, 59], il est
     * considéré égal à 0.
     */
    public Horaire(int h, int m) {
    // TODO 1.07. Écrire ce constructeur
    	this(true, h, m, 0) ;
    }
    /**
     * Construit un nouvel horaire au format 24h à partir des arguments (les
     * secondes et les minutes sont nulles).
     *
     * @param h les heures de cet horaire. Si h n'est pas dans l'intervalle
     * requis, il est considéré égal à 0.
     */
    public Horaire(int h) {
    // TODO 1.08. Écrire ce constructeur
    	this(true, h, 0, 0) ;
    }

    /**
     * Construit l'horaire 00:00:00 au format 24h .
     */
    public Horaire() {
    // TODO 1.09. Écrire ce constructeur
    	this(true, 0, 0, 0) ;
    }

    /**
     * @return les heures de cet horaire
     */
    public int getHeures() {
    // TODO 1.10. Écrire ce getter
    	return this.h ;
    }

    /**
     * @return les minutes de cet horaire
     */
    public int getMinutes() {
    // TODO 1.11. Écrire ce getter
    	return this.m ;
    }

    /**
     * @return les secondes de cet horaire
     */
    public int getSecondes() {
    // TODO 1.12. Écrire ce getter
    	return this.s ;
    }

    /**
     * @return vrai si format 24h, faux sinon.
     */
    public boolean isH24() {
    // TODO 1.13. Écrire ce getter
    	return this.h24 ;
    }

    /**
     * Met à jour les heures
     * @param heures les heures du nouvel horaire
     */
    public void setHeures(int heures) {
    // TODO 1.14. Écrire ce setter
    	this.h = (heures >= 0 && heures <= (h24 ? 23 : 11 )) ? heures : 0 ;
    }

    /**
     * Met à jour les minutes
     * @param minutes les minutes du nouvel horaire
     */
    public void setMinutes(int minutes) {
    // TODO 1.15. Écrire ce setter
    	this.m = (minutes >= 0 && minutes <= 59) ? minutes : 0 ;
    }

    /**
     * Met à jour les secondes
     * @param secondes les secondes du nouvel horaire
     */
    public void setSecondes(int secondes) {
    // TODO 1.16. Écrire ce setter
    	this.s = (secondes >= 0 && secondes <= 59) ? secondes : 0 ;
    }

    /**
     * Met à jour le format 24h ou 12h
     *
     * @param h24 vrai => format 24h , faux sinon.
     */
    public void setH24(boolean h24) {
    // TODO 1.17. Écrire ce setter
    	this.h24 = h24 ;
    }

    /**
     * Retourne un horaire sous la forme d'une chaîne de caractères sous la
     * forme "hh:mm:ss" où hh, mm, ss sont les heures, les minutes et les
     * secondes sur deux chiffres.
     *
     * Par exemple "00:00:00", "02:33:59"
     *
     * Conseil : lire la javadoc de String.format() :
     * https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html#format(java.lang.String,java.lang.Object...)
     *
     * @return un horaire de la forme "hh:mm:ss"
     */
    @Override
    public String toString() {
    // TODO 1.18. Écrire toString()
    	return String.format("%02d:%02d:%02d", h, m, s) ;
    }

    /**
     * Augmente cet horaire d'une heure.
     */
    public void uneHeureDePlus() {
    // TODO 1.19. Écrire uneHeureDePlus()
    	setHeures( (getHeures() == (h24 ? 23 : 11)) ? 0 : h + 1) ;
    }

    /**
     * Augmente cet horaire d'une minute.
     */
    public void uneMinuteDePlus() {
    // TODO 1.20. Écrire uneMinuteDePlus()
    	if (getMinutes() == 59) {
    		setMinutes(0) ;
    		uneHeureDePlus() ;
    	} else {
    		setMinutes(m+1) ;
    	}
    }

    /**
     * Augmente cet horaire d'une seconde.
     */
    public void uneSecondeDePlus() {
    // TODO 1.21. Écrire uneSecondeDePlus()
    	if (getSecondes() == 59) {
    		setSecondes(0) ;
    		uneMinuteDePlus() ;
    	} else {
    		setSecondes(s+1) ;
    	}
    }
    // TODO 1.01. Déclarer les attributs utiles à votre classe.
    // 
}
