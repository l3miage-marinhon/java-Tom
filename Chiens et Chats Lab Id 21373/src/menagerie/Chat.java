package menagerie;

// TODO Question 5: Créez la classe Chat...
// 
public class Chat extends Mammifere{
// un chat fait miaou
	public Chat(String nom) {
		super(nom);
	}
	
	@Override
	public String cri() {
		return "miaou";
	}
}