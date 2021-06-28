package menagerie;

// TODO Question 7: Créez la classe Poisson...
// 
public class Poisson extends Animal{
	//un poisson fait glouglou 
	
	public Poisson(String nom) {
		super(nom);
	}
	
	@Override
	public String cri() {
		return "glouglou";
	}

}