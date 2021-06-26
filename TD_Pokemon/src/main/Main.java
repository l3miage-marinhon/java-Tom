package main;

import java.util.ArrayList;

import Pokedex.*;

public class Main {
	public static void main(String[] args) {
		
		ArrayList<Pokemon> l = new ArrayList<Pokemon>();
		Pokedex pokedex = new Pokedex(l);
		
		Mer carapuce = new Mer("Carapuce", 5.1, 1);
		System.out.println(carapuce);
		pokedex.inserePokemon(carapuce);
		
		Croisiere lokhlass = new Croisiere("Lokhlass", 120, 2);
		System.out.println(lokhlass);
		pokedex.inserePokemon(lokhlass);
		
		Sportif tygnon = new Sportif("Tygnon", 25.6, 2, 1.60, 110);
		System.out.println(tygnon);
		pokedex.inserePokemon(tygnon);
		
		Pokemon ronflex = new Casanier("Ronflex", 260, 4, 1.90, 15);
		System.out.println(ronflex);
		pokedex.inserePokemon(ronflex);
		
		System.out.println( ronflex instanceof Casanier );
		System.out.println( ronflex instanceof Pokemon );
		System.out.println( ronflex instanceof Terrestre );
		
		Sportif mewtwo = new Sportif("Mewtwo", 49.2, 2, 1.90, 90);
		System.out.println(mewtwo);
		pokedex.inserePokemon(mewtwo);
		mewtwo.yo();
		
		System.out.println("vitesse moyenne pokemon : " + pokedex.vitesseMoyennePokemon());
		System.out.println("vitesse moyenne pokemon sportif : " + pokedex.vitesseMoyennePokemonSportifs());
		System.out.println("Pokemon le plus grand : \n" + pokedex.pokemonPlusGrand());
		System.out.println("Nom du Pokemon avec le plus de nageoires : " + pokedex.pokemonPlusNageoires());
		
				
		/* Remarques:
		 * 
		 * - Etant donné que la vitesse des pokemons se calcule en fonction d'autres de leurs attributs, il faudrait appeler setVitesse() 
		 * lorsque l'on change l'un des attributs concernés (ex nbNageoire pour un pokemon Croisiere), mais le setter setVitesse() ne se 
		 * trouve pas dans la classe où sont définis ces autres attributs
		 * 
		 * ----------CORRIGE----------
		 * - On repète un peu les methodes toString() dans les classes Croisiere et Mer, une option serait de redéfinir cette methode dans 
		 * Aquatique, mais il faudrait que l'attribut vitesse soit également dans cette classe (setVitesse() en abstract ?) et redéfini dans 
		 * les classes enfants -> probleme : définir un pokemon aquatique ?? (comment lui définir l'attribut vitesse, faire 1 setter pour 
		 * aquatique et un abstract pour les enfants ? meme si instancier un pokemon de classe Aquatique n'a pas de sens car sa vitesse dépend 
		 * de mer ou croisiere, pas comme pour les pokemons Terrestre) et il faut aussi passer la classe Aquatique en abstract, bof bof
		 * quelques éléments de cette solution en commentaires dans les classes Aquatique et Mer
		 * ---------------------------
		 * 
		 * - j'ai un peu modifié la suite du TD, au lieu d'avoir une classe CollectionPokemon avec les attributs et méthodes demandées, j'en 
		 * ai fait une interface, et j'ai fait en plus une classe Pokedex qui implement l'interface, a l'attribut demandé dans l'énoncé et redéfini les méthodes
		 * 
		 */
	}
}
