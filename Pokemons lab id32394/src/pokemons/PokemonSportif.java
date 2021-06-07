package pokemons;

// TODO Ecrivez la classe PokemonSportif.
// 
public class PokemonSportif extends PokemonTerrestre{
	private double frequence;
	
	public PokemonSportif(String nom, double poids, int pattes, double taille,double freq) {
		super(nom, poids, pattes, taille);
		this.frequence=freq;
		// TODO Auto-generated constructor stub
	}
	@Override
	
	public String toString(){
		String str="";
		str=super.toString();
		str+=" ma fréquence cardiaque est de ";
		str+=frequence;
		str+=" pulsation par minute";
		
		return str;
	}	
}