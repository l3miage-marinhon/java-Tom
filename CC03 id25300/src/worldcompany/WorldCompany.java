package worldcompany;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class WorldCompany {

    public static void main(String[] args) {
        System.out.println("Test de la World Company !");
    
		Employe[] employes = new Employe[10];
		employes[0] = new Vendeur("Ception", "Alex", 2018, 3225.40);
		employes[1] = new Vendeur("Tichaut", "Bernard", 1996, 6376.50);
		employes[2] = new Vendeur("Evitable", "Céline", 2001, 5324.56);
		employes[3] = new Vendeur("Orial", "Edith", 1977, 7354.50);
		employes[4] = new Manutentionnaire("Dustriel", "Firmin", 1972, 28.0);
		employes[5] = new Manutentionnaire("Manvussa", "Gérard", 2019, 50.0);
		employes[6] = new Manutentionnaire("Parleurnom", "Isabelle", 2000, 40.0);
		employes[7] = new Manutentionnaire("Use", "Jacques", 2010, 35.7);
		employes[8] = new Manutentionnaire("Fer", "Lucie", 2000, 267);
		employes[9] = new Vendeur("Ateur", "Nordine", 2007, 4367.42);
		
		
		Personnel ressourcesHumaines = new Personnel(employes);
		System.out.println("Salaire moyen: " + ressourcesHumaines.salaireMoyen());
		
		ressourcesHumaines.presentations();
		ressourcesHumaines.trierEmployes();
		System.out.println("Après réorganisation...");
		ressourcesHumaines.presentations();
		
		
		/*System.out.println("Parsing du fichier worldcompany.xml");
		String  filename = "src/worldcompany/worldcompany.xml";
		try {
			XMLReader  parser;
			parser = XMLReaderFactory.createXMLReader ();
			ReadPersonnel  reader = new  ReadPersonnel ();
			parser.setContentHandler(reader);
			parser.parse(filename);
			
			Personnel pers = reader.getPersonnel();
			pers.presentations();
			} catch (Exception e) {
				System.out.println(e);
			}*/
    }
}
