package minifacebook;

public class HarryPotterSocial {

    // Exercice 4: Décodage et reverse engeneering (retro-ingénierie)
    public static void main(String[] args) {
    	Personne hermione=new Personne("Granger","Hermione",new DateNaissance(15,4,1990));
    	Personne harry=new Personne("Potter","Harry",new DateNaissance(23,7,1989));
    	Personne ron=new Personne("Weasley","Ron",new DateNaissance(24,8,1988));
    	//cas de hermoine
    	hermione.setAmi(ron);
    	hermione.setMeilleurAmi(harry);
    	//cas de harry
    	harry.setAmi(ron);
    	harry.setMeilleurAmi(hermione);
    	//cas de Ron
    	ron.setAmi(harry);
    	ron.setMeilleurAmi(hermione);
  
    	hermione.ecritInfos();
    	ron.ecritInfos();
    	harry.ecritInfos();
    }
}
