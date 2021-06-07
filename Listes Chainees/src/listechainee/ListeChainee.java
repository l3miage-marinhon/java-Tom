package listechainee;

/*
 * L'objectif de cet exercice est d'implémenter une liste chaînée d'éléments ayant un contenu donné.
 * Une <a href="https://fr.wikipedia.org/wiki/Liste_cha%C3%AEn%C3%A9e">liste chaînée</a> 
 * est une structure de donnée dans laquelle chaque élément a un contenu et
 * un lien avec l'élément suivant.
 */
public class ListeChainee {

    // / Element du début de la liste chaînée
    Element tete;

    /**
     * Une liste par défaut est vide
     */
    ListeChainee() {
        this.tete = null;
    }

    // --------- ACCES ---------
    Element getTete() {
        return tete;
    // TODO Renvoie la référence vers la tete de la liste chaînée.
    }

    Element getQueue() {
        Element courant=getTete();
        while(courant.hasNext()) {
        	courant=courant.getNext();
        }
        return courant;
    // TODO Renvoie la queue (dernier element) de la liste.
    }

    Element getElement(int position) {
    	Element rechercher=getTete();
    	int i=0;
        while(rechercher.hasNext() && i!=position) {
        	rechercher=rechercher.getNext();
        	i++;
        }
        return (i==position) ? rechercher : null;
    // TODO Renvoie l'element situé à une position donnée. La possition de la tête est 0.
    }

    int getPosition(Element elt) {
        int position=0;
        Element courant=getTete();
        while(courant!=elt && courant.hasNext()) {
        		courant=courant.getNext();
        		position++;
        	}
        return (courant!=elt)?  -1 : position;
    // TODO Renvoie la position de l'Elément elt dans la liste. Renvoie -1 si l'élément n'est pas dans la liste.
    }

    // --------- ETAT ---------
    boolean estVide() {
        return (this.tete==null);
    // TODO Indique si la liste est vide
    }

    int tailleListe() {
    	 int nb_element=0;
         Element courant=getTete();
         if (!estVide()) {
        	 nb_element=1;
        	 while(courant.hasNext()) {
        		 nb_element++;
        	 	 courant=courant.getNext();
        	 }
         }
         return nb_element;
    // TODO Renvoie le nombre d'éléments de la liste.
    }

    boolean estTete(Element elt) {
        return (this.tete==elt);
    // TODO Indique si l'élément passé en paramètre est celui de tête.
    }

    boolean estQueue(Element elt) {
        return (getQueue()==elt);
    // TODO Indique si l'élément passé en paramètre est celui de la queue.
    }

    // --------- COPIE -------
    ListeChainee copie() {
    	ListeChainee new_list= new ListeChainee();
    	Element new_elem=new Element(getTete().getValeur());
    	Element courant=this.getTete();
    	new_list.tete=new_elem;
    	while(courant.hasNext()) {
    		courant=courant.getNext();
    		new_elem.setNext(new Element(courant.getValeur()));
    		new_elem=new_elem.getNext();
    	}
    	
        return new_list;
    /* TODO Renvoie une copie de la liste.
(Chaque élément est une copie des éléments de la liste)*/
    }

    // --------- INSERTION ---------
    void ajouteElementEnTete(Element elt) {
    	if(!estVide()) {
    		elt.setNext(getTete());
    		this.tete=elt;
    	}else {
    		this.tete=elt;
    	}
    // TODO Ajoute un élément à gauche de la tete de liste.
    }

    void ajouteElementEnQueue(Element elt) {
    	if(!estVide()) {
    		getQueue().setNext(elt);
    	}else {
    		this.tete=elt;
    	}
    // TODO Ajoute un element à droite de la queue de la liste.
    }

    boolean insereElementAGauche(int position, Element elt) {
    	if(!estVide() && position <= tailleListe()) {
    		Element ancien_d=getElement(position-1).setNext(elt);
    		elt.setNext(ancien_d);
    		return false;
    	}else {
    		return true;
    	}
    /* TODO Insère l'élément (elt) à gauche de l'élément qui est à la position position.
 Revoie true si l'insertion a pu se faire, c'est-à-dire si l'élément de position position est dans la liste. Renvoie false sinon*/
    }

    boolean insereElementADroite(int position, Element elt) {
    	if(!estVide() && position <= tailleListe()){
    		Element ancien_d=getElement(position).setNext(elt);
    		elt.setNext(ancien_d);
    		return true;
    	}else {
    		return false;
    }
    // TODO Insère l'élément (elt) à droite de l'élément qui est à la position position.\n Revoie true si l'insertion a pu se faire, c'est-à-dire si l'élément de position position  est dans la liste. Renvoie false sinon
    }

    boolean insereListeGauche(int position, ListeChainee liste) {
    	boolean reussi=false;
    	ListeChainee copie=liste.copie();
    	if(!estVide() && position <= tailleListe()) {
    		if(getElement(position-1)!=null) {
    			Element ancien_d=getElement(position-1).setNext(copie.tete);
    			copie.getQueue().setNext(ancien_d);
    			reussi=true;}
    	}else {
    		copie.getQueue().setNext(this.tete);
    		this.tete=copie.tete;
    		reussi=true;
    	}
    	return reussi;
    /* TODO Insère une copie de la liste liste (c'est-à-dire une copie de chaque éléments de la liste, dans leur ordre original)
 à gauche de l'élément qui est à la position position.
 Revoie true si l'insertion a pu se faire, c'est-à-dire si l'élément de position position  est dans la liste. Renvoie false sinon.
*/
    }

    boolean insereListeDroite(int position, ListeChainee liste) {
    	boolean reussi=false;
    	ListeChainee copie=liste.copie();
    	if(!estVide() && position <= tailleListe()) {
    		if(getElement(position)!=null) {
    			Element ancien_d=getElement(position).setNext(copie.tete);
    			copie.getQueue().setNext(ancien_d);
    			reussi=true;}
    	}else {
    		copie.getQueue().setNext(this.tete);
    		this.tete=copie.tete;
    		reussi=true;
    	}
    	return reussi;
    /* TODO Insère la liste liste (c'est-à-dire tous les éléments de la liste, dans leur ordre original)
 à droite de l'élément qui est à la position position.
 Revoie true si l'insertion a pu se faire, c'est-à-dire si l'élément de position position  est dans la liste. Renvoie false sinon*/
    }

    boolean detruitElement(int position) {
    	boolean reussi=false;
    	
		if(position-1<0) {
			this.tete=this.tete.getNext();
		}else {
    	
			if(getElement(position-1)!=null) {
					getElement(position-1).setNext(getElement(position).getNext());
					reussi=true;
    			}
    	}
    	
    	return reussi;
        
    // TODO Détruit l'élément à une position donnée. Renvoie false si l'élément à la position position n'existe pas.
    }

    int[] converti() {
        int [] tab= new int[tailleListe()];
        Element courant=tete;
        for(int i=0;i<tailleListe();i++){
        	tab[i]=courant.getValeur();
        	courant=courant.getNext();
        }
        return tab;
    // TODO Renvoie un tableau d'entier contenant les entiers contenus dans la liste dans l'ordre (de la tête à la queue).
    }

    void fromTableau(int[] tab) {
    // TODO Initialise la liste chaînée avec les éléments du tableau (dans l'ordre).
    	for(int valeur : tab) {
    		ajouteElementEnQueue(new Element(valeur));
    	}
    }

    // --------- AFFICHAGE ---------
    // / Permet l'affichage de la liste
    // / Ne pas modifier
    String description() {
        String s = "Liste : ";
        Element pointeur = tete;
        int size = tailleListe();
        for (int i = 0; i < size; i++) {
            s += "[" + pointeur.getValeur() + "]";
            if (pointeur.hasNext()) {
                s += "->";
            }
            pointeur = pointeur.getNext();
        }
        return s;
    }
}
