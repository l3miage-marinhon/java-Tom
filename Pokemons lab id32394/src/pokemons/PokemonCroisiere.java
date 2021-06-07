package pokemons;

// TODO Ecrire la classe PokemonCroisiere
// 
public class PokemonCroisiere extends PokemonAquatique{

	public PokemonCroisiere(String nom, double poids, int nbNageoires) {
		super(nom, poids, nbNageoires);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double vitesse(){
		return super.vitesse()/2;
	}
}