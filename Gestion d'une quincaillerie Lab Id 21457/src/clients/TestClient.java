package clients;

public class TestClient {
	
	public static void main(String[] args) {

		Entreprise e1 = new Entreprise("0456AE94", "30 rue des prés", "08562205190", "entrepriseE1@gmail.com", "Paris", "Carroufino", CategorieEntreprise.PetiteMoyenneEntreprise);
		System.out.println("Entreprise 1 : \n" + e1);
		
		Particulier p1 = new Particulier("9877TY34", "12 rue marcel porte", "0629713873", "tom.fr_a-nces@yahoo.fr", Civilite.MONSIEUR, "Frances", "Tom", true);
		System.out.println("\n\nParticulier 1 : \n" + p1);
		
	}
	
	
}
