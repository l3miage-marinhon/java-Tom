
public class TestServicechu {
	
	public static void main(String[] args) {
		String noms[] = {"RICOT Léa",
                "HONXA Cécile",
                "ORAQ Anne",
                "KUZBIDON Alex",
                "MUDA Albert"};
			int naissances[] = {1924, 1954, 1943, 1915, 1987};
			serviceChu s = new serviceChu(noms, naissances);
			String p = "HONXA Cécile";
			if (s.estPatient(p)) {
			    System.out.println(p + " est un(e) patient(e) du service.");
			}
			else {
			    System.out.println(p + " n'est pas un(e) patient(e) du service.");
			}
			 
			p = "OINTOUSSA Cécile";
			if (s.estPatient(p)) {
			    System.out.println(p + " est un(e) patient(e) du service.");
			}
			else {
			    System.out.println(p + " n'est pas un(e) patient(e) du service.");
			}
			 
			p = "MUDA Albert";
			if (s.estPatient(p)) {
			    System.out.println(p + " est un(e) patient(e) du service.");
			    System.out.println(p + "est né en "+s.getAnnee(p));
			}
			else {
			    System.out.println(p + " n'est pas un(e) patient(e) du service.");
			}

			p="KUZBIDON Alex";
			if (s.estPatient(p)) {
				System.out.println(p + "est né en "+s.getAnnee(p));
			}
			else {
			    System.out.println(p + " n'est pas un(e) patient(e) du service.");
			}

			
			p="OINTOUSSA Cécile";
			if (s.getAnnee(p)>0) {
			    System.out.println(p + "est né en "+s.getAnnee(p));
			}
			else {
			    System.out.println(p + " n'est pas un(e) patient(e) du service.");
			}
			
			String[] ancienne_g=s.getGeneration(1900,2000);
			System.out.println("la generation entre 1900 et 2000 sont :");
			for(int i=0;i<ancienne_g.length;i++) {
				System.out.println(ancienne_g[i]);
			}

	}

}
