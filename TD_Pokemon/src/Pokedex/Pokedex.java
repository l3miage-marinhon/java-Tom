package Pokedex;

import java.util.ArrayList;

import pokedex.Pokemon;
import pokedex.Sportif;
import pokedex.Terrestre;
import pokedex.Aquatique;


public class Pokedex implements CollectionPokemons{
	private ArrayList<Pokemon> listePokemon;
	
	public Pokedex(ArrayList<Pokemon> listePokemon) {
		this.listePokemon = listePokemon;
	}
	
	@Override
	public void inserePokemon(Pokemon p) {
		listePokemon.add(p);
	}
	
	@Override
	public double vitesseMoyennePokemon() {
		double s = 0;
		for(Pokemon p : listePokemon) {
			/*
			if(p instanceof Terrestre || p instanceof Casanier || p instanceof Sportif) {
				s += ((Terrestre)p).getVitesse();
			}else if(p instanceof Mer) {
				s += ((Mer)p).getVitesse();
			}else {
				s += ((Croisiere)p).getVitesse();
			}
			*/
			s += p.getVitesse();
		}
		return listePokemon.size()>0 ? s/listePokemon.size() : 0;
	}

	@Override
	public double vitesseMoyennePokemonSportifs() {
		double s = 0;
		int n = 0;
		for(Pokemon p : listePokemon) {
			if(p instanceof Sportif) {
				s += p.getVitesse();
				n++;
			}
		}
		return n>0 ? s/n : 0;
	}
	
	@Override
	public Pokemon pokemonPlusGrand() {
		double tMax = 0;
		Pokemon pPlusGrand = null;
		for(Pokemon p : listePokemon) {
			if(p instanceof Terrestre && ((Terrestre)p).getTaille() > tMax) {
				pPlusGrand = p;
			}
		}
		return pPlusGrand;
	}
	
	public String pokemonPlusNageoires() {
		int nbN = -1;
		Pokemon pPlusNag = null;
		for(Pokemon p : listePokemon) {
			if(p instanceof Aquatique && ((Aquatique)p).getNbNageoires() > nbN) {
				pPlusNag = p;
			}
		}
		return (pPlusNag!=null) ? pPlusNag.getNom() : "" ;
	}
}
