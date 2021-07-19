package quincaillerie;


public class Quincaillerie {

    public static void main(String[] args) {
    /* TODO Question 3: Afficher les caractéristiques de pneu, chambre à air e, disque de jante et rayon
 Question 6: Afficher les fiches caractéristiques des 2 piièces composites jantes en kit et brouette en kit décrites dans l'énoncé.
 //une jante en kit est constituer d'un disque de jante et de 3 Rayon
 //et une brouette montée et constituer d'un pneu , d'une jante en kit et d'une chambre a air
 Question 14: Afficher les fiches caractéristiques des 2 piièces composites jantes montée et brouette montée décrites dans l'énoncé.
 Question 27: Ajouter un client particulier et une entreprise et afficher le résultat.*/
    	//tu peut ici remplacer avec des Collections si tu trouve ça plus simple 
    	PieceDeBase [] tab= new PieceDeBase[4];
    	tab[0]=new PieceDeBase("pneu", "004741",12.5,60,2);
    	tab[1]=new PieceDeBase("chambre à air", "004565",4.0,20,2);
    	tab[2]=new PieceDeBase("disque de jante", "001214",4.5,60,2);
    	tab[3]=new PieceDeBase("rayon", "004748",1,20,1);
    	
    	
    	
    	PieceCompositeEnKit[] tab2=new PieceCompositeEnKit[2];
    	tab2[0]=new PieceCompositeEnKit("jante en kit","014541", 10);
    	tab2[1]=new PieceCompositeEnKit("route de brouette en kit ","011512", 10);
    	
    	PieceCompositeMontee[] tab3=new PieceCompositeMontee[2];
    	tab3[0]=new PieceCompositeMontee("jante","024541", 1, 7.0);
    	tab3[1]=new PieceCompositeMontee("roue de brouette","021512", 1, 8.0);
    }
}
