package trinome;

/*
 * Classe permentant de coder un nombre complexe.
 * Note: un nombre réel est un nombre complexe dont la partie imaginaire est nulle
 */
class Complexe {

    // Partie réelle
    double reel;

    // Partie imaginaire
    double imaginaire;

    // Constructeur
    Complexe(double reel, double imaginaire) {
        this.reel = reel;
        this.imaginaire = imaginaire;
    }

    // Renvoie vrai si la partie imaginaire est nulle.
    // Pour des raisons de test, doit être écrite sur 1 seule ligne
    boolean isReel() {
        return false;
    // TODO A compléter
    }

    // Renvoie une description sommaire (et non optimale) d'un nombre complexe pour affichage.
    // A ne pas modifier.
    String getString() {
        return "[" + reel + " + i " + imaginaire + "]";
    }

    // Renvoie vraie si le complexe c passé en paramètre et le complexe courant
    // ont la même partie réelle et la même partie imaginaire.
    // Pour des raisons de test, doit être écrite sur 1 seule ligne.
    boolean egal(Complexe c) {
        return false;
    // TODO A compléter
    }
}
