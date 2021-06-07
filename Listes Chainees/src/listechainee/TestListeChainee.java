package listechainee;

public class TestListeChainee {

    public static void main(String[] args) {
        /* ------------- Création artificielle d'une petite liste. ---------- */
        // /!\ Une liste NE doit PAS être créée comme cela !
        // -> On ne devrait pas pouvoir avoir accès à liste.tete.
        // Cette partie de code est uniquement là pour le test partiel.
        // Note: la liste est celle représentée par le diagramme APO du début du sujet.
        Element el3 = new Element(33);
        Element el2 = new Element(25);
        el2.setNext(el3);
        Element el1 = new Element(12);
        el1.setNext(el2);
        ListeChainee liste = new ListeChainee();
        // Cette ligne est présente uniquement pour le test.
        liste.tete = el1;
        /* ------------- Premier affichage  --------------------------------- */
        System.out.println("--- Une première liste ---");
        System.out.println(liste.description());
        System.out.println();
        /* ------------- Test de getTete()  --------------------------------- */
        System.out.println("Tête de la liste: " + (liste.getTete() != null ? liste.getTete().description() : ""));
        /* ------------- Test de getQueue() --------------------------------- */
        System.out.println("Queue de la liste: " + (liste.getQueue() != null ? liste.getQueue().description() : ""));
        /* ------------- Test de getElement() ------------------------------- */
        System.out.print("Test de getElement():  ");
        for (int n = 0; n < 4; n++) {
            Element elt = liste.getElement(n);
            if (elt == null) {
                System.out.print("pas d'élément à la position " + n + ", ");
            } else {
                System.out.print("élement à la position " + n + ": " + elt.description() + ", ");
            }
        }
        System.out.println();
        System.out.println();
        /* ------------- Test de getPosition() --------------------------------- */
        System.out.print("Test de getPosition(): ");
        Element[] elements = new Element[4];
        elements[0] = el1;
        elements[1] = el2;
        elements[2] = el3;
        elements[3] = new Element(45);
        for (Element elt : elements) {
            int position = liste.getPosition(elt);
            System.out.print("position de l'élément " + elt.description() + ": " + position + ", ");
        }
        System.out.println();
        /* ------------- Test de estVide()    ---------------------------------- */
        ListeChainee listeVide = new ListeChainee();
        System.out.println("Une nouvelle liste est vide ? " + listeVide.estVide());
        System.out.println("Liste à 3 éléments est vide ? " + liste.estVide());
        /* ------------- Test de tailleListe()    ------------------------------ */
        System.out.println("Taille d'une liste vide: " + listeVide.tailleListe());
        System.out.println("Taille d'une liste de 3 éléments: " + liste.tailleListe());
        /* ------------- Test de estTete()        ------------------------------ */
        System.out.println("el1 est tête ?: " + liste.estTete(el1));
        System.out.println("el3 est tête ?: " + liste.estTete(el3));
        /* ------------- Test de estQueue()        ------------------------------ */
        System.out.println("el1 est queue ?: " + liste.estQueue(el1));
        System.out.println("el3 est queue ?: " + liste.estQueue(el3));
        System.out.println();
        /* ------------- Test de copie de liste   ------------------------------ */
        System.out.println("--- Test de copie de liste ---");
       ListeChainee listeCopiee = liste.copie();
        if (listeCopiee != null) {
            if (liste.getTete() != null && listeCopiee.getTete() != null) {
                int valOrg = liste.getTete().getValeur();
                int valCopie = listeCopiee.getTete().getValeur();
                System.out.println("liste.getTete().getValeur() == listeCopiee.getTete().getValeur() ? " + valOrg + " == " + valCopie + " ? ");
                System.out.println("liste.getTete() != listeCopiee.getTete() ? -> " + (liste.getTete() != listeCopiee.getTete()));
                if (listeCopiee.getTete().getNext() != null) {
                    System.out.println("liste.getTete().getNext().getValeur() == listeCopiee.getTete().getNext().getValeur() ? " + liste.getTete().getNext().getValeur() + " == " + listeCopiee.getTete().getNext().getValeur() + " ? ");
                    System.out.println("liste.getTete().getNext() != listeCopiee.getTete().getNext() ? ->" + (liste.getTete().getNext() != listeCopiee.getTete().getNext()));
                }
            }
        }
        System.out.println();
        /* ------------- Test d'ajout d'élément   ------------------------------ */
       System.out.println("--- Test d'ajout d'élément ---");
        Element nouvelleTete = new Element(53);
        liste.ajouteElementEnTete(nouvelleTete);
        System.out.println("Ajout de l'élément " + nouvelleTete.description() + " en tête.");
        System.out.println(liste.description());
        Element nouvelleQueue = new Element(35);
        liste.ajouteElementEnQueue(nouvelleQueue);
        System.out.println("Ajout de l'élément " + nouvelleQueue.description() + " en queue.");
        System.out.println(liste.description());
        Element aGauche = new Element(18);
        System.out.println("Ajout de l'élément " + aGauche.description() + " à gauche de l'élément " + (liste.getElement(2) != null ? liste.getElement(2).description() : ""));
        liste.insereElementAGauche(2, aGauche);
        System.out.println(liste.description());
        Element aDroite = new Element(75);
        System.out.println("Ajout de l'élément " + aDroite.description() + " à droite de l'élément " + (liste.getElement(2) != null ? liste.getElement(2).description() : ""));
        liste.insereElementADroite(2, aDroite);
        System.out.println(liste.description());
        System.out.println();
        /* ------------- Test d'insertion de listes   -------------------------- */
        System.out.println("--- Test d'insertion de liste ---");
        ListeChainee aInsererAGauche = new ListeChainee();
        aInsererAGauche.ajouteElementEnTete(new Element(200));
        aInsererAGauche.ajouteElementEnQueue(new Element(201));
        aInsererAGauche.ajouteElementEnQueue(new Element(202));
        aInsererAGauche.ajouteElementEnQueue(new Element(203));
        if (aInsererAGauche.getTete() != null) {
            System.out.println("Inserstion de la liste " + aInsererAGauche.description() + " à gauche de l'élément " + (liste.getElement(3) != null ? liste.getElement(3).description() : ""));
            liste.insereListeGauche(3, aInsererAGauche);
            System.out.println(liste.description());
            System.out.println("Si on change l'élément de tête de la liste aInsererAGauche, rien ne doit changer dans liste (copie du paramètre)...");
            aInsererAGauche.getTete().setNext(null);
            System.out.println(liste.description());
        }
        ListeChainee aInsererADroite = new ListeChainee();
        aInsererADroite.ajouteElementEnTete(new Element(100));
        aInsererADroite.ajouteElementEnQueue(new Element(101));
        aInsererADroite.ajouteElementEnQueue(new Element(102));
        aInsererADroite.ajouteElementEnQueue(new Element(103));
        if (aInsererADroite.getTete() != null) {
            System.out.println("Inserstion de la liste " + aInsererADroite.description() + " à droite de l'élément " + (liste.getElement(5) != null ? liste.getElement(5).description() : ""));
            liste.insereListeDroite(5, aInsererADroite);
            System.out.println(liste.description());
            System.out.println("Si on change l'élément de tête de la liste aInsererADroite, rien ne doit changer dans liste (copie du paramètre)...");
            aInsererADroite.getTete().setNext(null);
            System.out.println(liste.description());
            System.out.println();
        }
        /* ------------- Test de destruction d'élément   -------------------------- */
        System.out.println("--- Test de destruction d'élément ---");
        System.out.println("Desctruction de l'élément " + (liste.getElement(3) != null ? liste.getElement(3).description() : ""));
        liste.detruitElement(3);
        System.out.println(liste.description());
        System.out.println("Desctruction de la tête " + (liste.getElement(0) != null ? liste.getElement(0).description() : ""));
        liste.detruitElement(0);
        System.out.println(liste.description());
        System.out.println("Desctruction de la queue " + (liste.getElement(liste.tailleListe() - 1) != null ? liste.getElement(liste.tailleListe() - 1).description() : ""));
        liste.detruitElement(liste.tailleListe() - 1);
        System.out.println(liste.description());
        System.out.println();
        /* ------------- Test conversion en tableau   ---------------------------- */
        int[] tab = liste.converti();
        System.out.println("--- Test conversion avec des tableaux ---");
        System.out.print("Tableau correspondant à la liste: {");
        if (tab != null) {
            for (int e : tab) {
                System.out.print(e + " ");
            }
        }
        System.out.println("}");
        int[] tableau = { 10, 20, 30, 40, 50 };
        ListeChainee fromTableau = new ListeChainee();
        fromTableau.fromTableau(tableau);
        System.out.print("Liste correspondant au tableau {10, 20, 30, 40, 50}: " + (fromTableau != null ? fromTableau.description() : ""));
        System.out.println();
    }
}
