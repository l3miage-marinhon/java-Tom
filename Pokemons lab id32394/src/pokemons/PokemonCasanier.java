package pokemons;

// TODO Ecrire la classe PokemonTerrestre.
// 
public class PokemonCasanier extends PokemonTerrestre{
	private double nbHTele;
	
	public PokemonCasanier(String nom, double poids, int pattes, double taille,double nbTele) {
		super(nom, poids, pattes, taille);
		this.nbHTele=nbTele;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String str="";
		str+=super.toString();
		str+=" je regarde la télé ";
		str+=nbHTele;
		str+="h par jour";
		return str;
	}


}