package evaluable;

public class Etudiant implements Evaluable{
	private String nom;
	private double note;
	
	public Etudiant(String nm, double nt) {
		nom=nm;
		note=nt;
	}
	
	@Override
	public String toString() {
		return "Etudiant => nom : " + nom + " | note : " + note;
	}
	@Override
	public double valeur() {
		return note;
	}
}
