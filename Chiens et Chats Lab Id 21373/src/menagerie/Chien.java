package menagerie;

// TODO Question 6: Créez la classe Chien...
// 
public class Chien extends Mammifere{
	// un chien fait wouaf
	public Chien(String nom) {
		super(nom);
	}
	
	@Override
	public String cri() {
		return "wouaf";
	}
	
	@Override
	public String toString() {
		return "Je suis un chien, " + super.toString();
	}
}