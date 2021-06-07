package menagerie;

// TODO Question 6: Créez la classe Chien...
// 
public class Chien extends Mammifere{

	public Chien(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String cri() {
		return "Wouaf !";
	}
}