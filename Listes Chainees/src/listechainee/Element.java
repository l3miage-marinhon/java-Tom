package listechainee;

/**
 * Elément d'une liste chaînée.
 * Un Element a un contenu et une référence vers
 * l'Elément suivant.
 */
public class Element {

    // / Information contenue dans l'Element
    Contenu contenu;

    // / Référence vers l'élément suivant
    Element next;

    // / Un nouvel Element prend un Contenu en paramètre
    Element(int valeur) {
        contenu = new Contenu(valeur);
        this.next = null;
    }

    // / Renvoie le Contenu de l'Element
    Contenu getContenu() {
        return contenu;
    }

    // / Renvoie la valeur de l'information contenue
    // / dans le Contenu de l'Element
    int getValeur() {
        return contenu.getValeur();
    }

    // / Renvoie vrai s'il existe un Element suivant
    boolean hasNext() {
        return (next != null);
    }

    // / Renvoie la référence vers l'Elément suivant
    Element getNext() {
        return this.next;
    }

    // / Donne une référence vers l'Elément suivant
    // / Renvoie la référence de l'ancien Elément suivant.
    Element setNext(Element next) {
        Element ancienNext = this.next;
        this.next = next;
        return ancienNext;
    }

    // / Description de l'Element sous forme  de chaîne de caractères
    String description() {
        String s = "(" + contenu.description() + ")";
        return s;
    }
}
