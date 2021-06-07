package menagerie;

// TODO Question 3: Créez la classe Mammifere...
// 
public abstract class Mammifere extends Animal{

	public Mammifere(String nom) {
		super(nom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String str="";
		str+="Je m'appelle ";
		str+=super.getNom();
		str+=" et je peux faire : ";
		str+=cri();
		return str;
	}
	
}