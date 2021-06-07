package pokemons;

// TODO Ecrire la classe Pokemon
// 
public abstract class Pokemon{
	private String nom;
	private double poids;
	
	public Pokemon(){
		nom="nothing";
		poids=0;
	}
	public Pokemon(String nom,double poids){
		this.nom=nom;
		this.poids=poids;
	}
	
	public double vitesse(){
		return 0;
	}
	
	@Override
	public String toString(){
		String str="";
		str+="Je suis le pokémon ";
		str+=nom;
		str+=" mon poids est de ";
		str+=poids;
		str+=" Kg, ma vitesse est de ";
		str+=vitesse();
		str+=" Km/h j'ai ";
		return str;
	}
	
	protected double getPoids(){
		return poids;
	}
	
} 
