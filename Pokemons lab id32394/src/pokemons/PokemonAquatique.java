package pokemons;

// TODO Ecrire la classe PokemonAquatique
// 
public abstract class PokemonAquatique extends Pokemon{
	private int nbNageoires;
	
	public PokemonAquatique(String nom, double poids , int nbNageoires) {
		super(nom,poids);
		this.nbNageoires=nbNageoires;
		
	}
	
	@Override
	public double vitesse() {
		return super.getPoids()/(25*nbNageoires);
	}
	
	@Override
	public String toString(){
		String str="";
		str+=super.toString();
		str+=nbNageoires;
		str+=" nageoires";
		
		return str;
	}
	
}