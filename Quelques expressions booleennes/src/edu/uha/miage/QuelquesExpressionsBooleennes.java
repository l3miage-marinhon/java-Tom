/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package edu.uha.miage;

/**
 * Écrivez le contenu des méthodes de cette classe pour qu'elles remplissent
 * les contraintes demandées dans leur JavaDoc, en UNE SEULE INSTRUCTION.
 * @author yvan
 */
public class QuelquesExpressionsBooleennes {

    /**
     * Méthode qui retourne vrai si son paramètre c est une lettre de l'alphabet
     * latin sans accent et minuscule. Dans le cas contraire elle retourne faux.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param c un caractère quelconque
     * @return vrai si c est une lettre sans accent minuscule, faux sinon.
     */  
	
	public static boolean estMinuscule(char c) {
        return (c>='a' && c<='z');
    /* TODO 1
		Écrire estMinuscule qui retourne vrai
		si c est une lettre minuscule
		et faux sinon.
		Pour simplifier l'exercice, les caractères accentués
		ne sont pas considérés comme minuscule
	*/
    }

    /**
     * Méthode qui retourne vrai si son paramètre c n'est pas un chiffre
     * décimal, faux sinon.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param c un caractère quelconque
     * @return vrai si c n'est pas un chiffre décimal, faux sinon.
     */
    public static boolean nEstPasUnChiffreDecimal(char c) {
        return c<'0' && c>'9';
    /* TODO 2
		Écrire nEstPasUnChiffreDecimal qui retourne vrai
		si c n'est pas un chiffre décimal
		et faux sinon.
	*/
    }

    /**
     * Méthode qui retourne vrai si son paramètre c est une lettre de l'alphabet
     * latin sans accent.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param c un caractère quelconque
     * @return vrai si c une lettre de l'alphabet latin sans accent, faux sinon.
     */
    public static boolean estUneLettreLatineSansAccent(char c) {
        return ((c>='a' && c<='z') || (c>='A' && c<='Z'));
    /* TODO 3
		Écrire estUneLettreLatineSansAccent qui retourne vrai
		si c est une lettre de l'alphabet latin
		en majuscule ou en minuscule mais sans les 
		mais sans les caractères accentués.
	*/
    }

    /**
     * Méthode qui retourne vrai si son paramètre i est un entier naturel impair
     * strictement inférieur à 5. Faux sinon.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param i un entier quelconque
     * @return vrai si i est un entier naturel impair strictement inférieur à 5.
     */
    public static boolean estUnEntierNaturelImpairStrictementPlusPetitQue5(int i) {
        return (i>0 && i%2==1 && i<5);
    /* TODO 4
		Écrire estUnEntierNaturelImpairStrictementPlusPetitQue5
		qui retourne vrai si son paramètre i
		est un entier naturel impair, tel que i < 5
	*/
    }

    /**
     * Méthode qui retourne vrai si son paramètre i est un entier impair
     * strictement inférieur à 54. Faux sinon.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param i un entier quelconque
     * @return vrai si i est un entier impair strictement inférieur à 54.
     */
    public static boolean estUnEntierImpairStrictementPlusPetitQue54(int i) {
        return ((i<54) && ((i%2)==1));
    /* TODO 5
		Écrire estUnEntierImpairStrictementPlusPetitQue54
		qui retourne vrai si son paramètre i
		est un entier impair, tel que i < 54
	*/
    }

    /**
     * Méthode qui retourne vrai si son paramètre i est un entier impair. Faux sinon.
     *
     * Le "défi" est de le faire en une seule instruction : le "return"
     *
     * @param i un entier quelconque
     * @return vrai si i est impair.
     */
    public static boolean estUnEntierImpair(int i) {
        return ((i%2)==1);
    /* TODO 6
		Écrire estImpair
		qui retourne vrai si son paramètre i
		est un entier impair
	*/
    }
}
