package fileAttente;

public class Personne {
	private String nom;
	private int age;
	public Personne() {}
	public Personne(String n, int a) {
		setNom(n);
		setAge(a);
	}
	public void setNom(String n) {
		nom=n;
	}
	public void setAge(int a) {
		age=a;
	}
	public int getAge() {
		return age;
	}
	public String getNom() {
		return nom;
	}
	@Override
	public String toString() {
		return "Je m'appelle " + getNom() + " et j'ai " + getAge() + " ans";
	}
}
