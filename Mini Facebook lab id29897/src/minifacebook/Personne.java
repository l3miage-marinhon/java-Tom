package minifacebook;

public class Personne{
	String nom;
	String prenom;
	DateNaissance dateDeNaissance;
	Personne ami;
	Personne meilleurAmi;
	
	Personne(String nom , String prenom ,DateNaissance dateDeNaissance){
		this.nom=nom;
		this.prenom=prenom;
		this.dateDeNaissance=dateDeNaissance;
	}
	
	void setAmi(Personne ami) {this.ami=ami;}
	void setMeilleurAmi(Personne ami) {this.meilleurAmi=ami;}
	
	Personne getAmi() {return this.ami;}
	Personne getMeilleurAmi() {return this.meilleurAmi;}
	
	void ecritInfos(){
		System.out.println("------------------------------------");
		System.out.println(prenom+" "+nom);
		System.out.print("Ne(e) le : ");
		dateDeNaissance.ecritDate();
		System.out.println(" ("+dateDeNaissance.age()+" ans)");
		System.out.println("Meilleur ami : "+getMeilleurAmi().prenom+" "+getMeilleurAmi().nom);
		System.out.println("Ami : "+getAmi().prenom+" "+getAmi().nom);
		System.out.println("------------------------------------");
	}
}

