package menagerie;

// TODO Question 5: Créez la classe Chat...
// 
public class Chat extends Mammifere{

	public Chat(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String cri() {
		return "Miaou !";
	}
}