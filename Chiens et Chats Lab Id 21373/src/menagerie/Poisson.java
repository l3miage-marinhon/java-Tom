package menagerie;

// TODO Question 7: Créez la classe Poisson...
// 
public class Poisson extends Animal{

	public Poisson(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String cri() {
		return "Gloup !";
	}
}