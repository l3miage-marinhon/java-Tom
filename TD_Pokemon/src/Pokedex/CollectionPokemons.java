package Pokedex;
import pokedex.Pokemon;

public interface CollectionPokemons{

	public abstract void inserePokemon(Pokemon p);
	public abstract double vitesseMoyennePokemon();
	public abstract double vitesseMoyennePokemonSportifs();
	public abstract Pokemon pokemonPlusGrand();
	public abstract String pokemonPlusNageoires();

}

