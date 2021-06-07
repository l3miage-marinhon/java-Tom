package listechainee;

/**
 * Classe Contenu
 * Permet de stocker une information (ici de type int)
 */
class Contenu {

    // / Information à stocker (ici un entier)
    int valeur;

    // / prend obligatoiremennt une valeur en paramètre
    Contenu(int valeur) {
        this.valeur = valeur;
    }

    // / Renvoie une copie du Contenu courant
    Contenu copie() {
        return new Contenu(valeur);
    }

    // / Donne la valeur de l'information
    int getValeur() {
        return valeur;
    }

    // / Description du contenu sous forme  de chaîne de caractères
    String description() {
        String s = "" + valeur;
        return s;
    }
}
